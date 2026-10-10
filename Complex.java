public class Complex {

    private double re;
    private double im;
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    public Complex add(Complex b) {
        return new Complex(re + b.re, im + b.im);
    }
    public Complex subtract(Complex b) {
        return new Complex(re - b.re, im - b.im);
    }
    public Complex multiply(Complex b) {
        double real = re * b.re - im * b.im;
        double imaginary = re * b.im + im * b.re;
        return new Complex(real, imaginary);
    }
    public Complex divide(Complex b) {
        double d = b.re * b.re + b.im * b.im;
        if (d == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        double real = (re * b.re + im * b.im) / d;
        double imaginary = (im * b.re - re * b.im) / d;
        return new Complex(real, imaginary);
    }
    public double module() {
        return Math.sqrt(re * re + im * im);
    }
    @Override
    public String toString() {
        if (im == 0) {
            return String.format("%.2f", re);
        }
        if (re == 0) {
            return String.format("%.2fi", im);
        }
        if (im > 0) {
            return String.format("%.2f + %.2fi", re, im);
        }
        return String.format("%.2f - %.2fi", re, -im);
    }
}
