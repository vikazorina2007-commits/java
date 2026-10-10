import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);
    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (in.hasNextInt()) {
                int number = in.nextInt();

                if (number > 0) {
                    return number;
                }
                System.out.println("Введите число больше 0");
            } else {
                System.out.println("Введите целое число");
                in.next();
            }
        }
    }
    public static double readDouble() {
        while (true) {
            if (in.hasNextDouble()) {
                return in.nextDouble();
            }
            System.out.println("Введите число");
            in.next();
        }
    }
    public static int readChoice() {
        while (true) {
            System.out.print("Выберите действие: ");

            if (in.hasNextInt()) {
                int choice = in.nextInt();

                if (choice >= 0 && choice <= 5) {
                    return choice;
                }
                System.out.println("Введите число от 0 до 5");
            } else {
                System.out.println("Введите целое число");
                in.next();
            }
        }
    }
    public static ComplexMatrix readMatrix() {
        int rows = readInt("Количество строк: ");
        int columns = readInt("Количество столбцов: ");
        ComplexMatrix matrix =
                new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println(
                        "Элемент [" + i + "][" + j + "]"
                );
                System.out.print("Действительная часть: ");
                double re = readDouble();
                System.out.print("Мнимая часть: ");
                double im = readDouble();
                matrix.set(
                        i,
                        j,
                        new Complex(re, im)
                );
            }
        }
        return matrix;
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("1 - Сложение");
            System.out.println("2 - Умножение");
            System.out.println("3 - Деление");
            System.out.println("4 - Транспонирование");
            System.out.println("5 - Определитель");
            System.out.println("0 - Выход");
            int choice = readChoice();
            if (choice == 0) {
                break;
            }
            try {
                if (choice == 1) {
                    System.out.println("Первая матрица:");
                    ComplexMatrix a = readMatrix();
                    System.out.println("Вторая матрица:");
                    ComplexMatrix b = readMatrix();
                    ComplexMatrix result = a.add(b);
                    System.out.println("Результат:");
                    result.print();
                } else if (choice == 2) {
                    System.out.println("Первая матрица:");
                    ComplexMatrix a = readMatrix();
                    System.out.println("Вторая матрица:");
                    ComplexMatrix b = readMatrix();
                    ComplexMatrix result = a.multiply(b);
                    System.out.println("Результат:");
                    result.print();
                } else if (choice == 3) {
                    System.out.println("Первая матрица:");
                    ComplexMatrix a = readMatrix();
                    System.out.println("Вторая матрица:");
                    ComplexMatrix b = readMatrix();
                    ComplexMatrix result = a.divide(b);
                    System.out.println("Результат:");
                    result.print();
                } else if (choice == 4) {
                    ComplexMatrix a = readMatrix();
                    ComplexMatrix result =
                            a.transpose();
                    System.out.println("Результат:");
                    result.print();
                } else if (choice == 5) {
                    ComplexMatrix a = readMatrix();
                    Complex result =
                            a.determinant();
                    System.out.println(
                            "Определитель: " + result
                    );
                } else {
                    System.out.println(
                            "Неверный выбор"
                    );
                }
            } catch (IllegalArgumentException |
                     ArithmeticException e) {
                System.out.println(
                        "Ошибка: " + e.getMessage()
                );
            }
        }
        in.close();
    }
}
