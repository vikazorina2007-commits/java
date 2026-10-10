public class ComplexMatrix {
    private Complex[][] matrix;
    private int rows;
    private int columns;
    public ComplexMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        matrix = new Complex[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new Complex(0, 0);
            }
        }
    }
    public void set(int i, int j, Complex value) {
        matrix[i][j] = value;
    }
    public Complex get(int i, int j) {
        return matrix[i][j];
    }
    public ComplexMatrix add(ComplexMatrix b) {

        if (rows != b.rows || columns != b.columns) {
            throw new IllegalArgumentException(
                    "Матрицы должны быть одного размера"
            );
        }
        ComplexMatrix result =
                new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.matrix[i][j] =
                        matrix[i][j].add(b.matrix[i][j]);
            }
        }
        return result;
    }
    public ComplexMatrix multiply(ComplexMatrix b) {
        if (columns != b.rows) {
            throw new IllegalArgumentException(
                    "Матрицы нельзя перемножить"
            );
        }
        ComplexMatrix result =
                new ComplexMatrix(rows, b.columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < b.columns; j++) {
                Complex sum = new Complex(0, 0);
                for (int k = 0; k < columns; k++) {
                    Complex temp =
                            matrix[i][k].multiply(b.matrix[k][j]);
                    sum = sum.add(temp);
                }
                result.matrix[i][j] = sum;
            }
        }
        return result;
    }
    public ComplexMatrix transpose() {
        ComplexMatrix result =
                new ComplexMatrix(columns, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result.matrix[j][i] =
                        matrix[i][j];
            }
        }
        return result;
    }
    public Complex determinant() {
        if (rows != columns) {
            throw new IllegalArgumentException(
                    "Матрица должна быть квадратной"
            );
        }
        if (rows == 1) {
            return matrix[0][0];
        }
        if (rows == 2) {
            Complex first =
                    matrix[0][0].multiply(matrix[1][1]);
            Complex second =
                    matrix[0][1].multiply(matrix[1][0]);
            return first.subtract(second);
        }
        Complex result = new Complex(0, 0);
        for (int j = 0; j < columns; j++) {
            ComplexMatrix minor =
                    getMinor(0, j);
            Complex element =
                    matrix[0][j].multiply(
                            minor.determinant()
                    );
            if (j % 2 == 0) {
                result = result.add(element);
            } else {
                result = result.subtract(element);
            }
        }
        return result;
    }
    private ComplexMatrix getMinor(int row, int column) {
        ComplexMatrix minor =
                new ComplexMatrix(rows - 1, columns - 1);
        int newRow = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) {
                continue;
            }
            int newColumn = 0;
            for (int j = 0; j < columns; j++) {
                if (j == column) {
                    continue;
                }
                minor.matrix[newRow][newColumn] =
                        matrix[i][j];
                newColumn++;
            }
            newRow++;
        }
        return minor;
    }
    public
    ComplexMatrix inverse() {
        if (rows != columns) {
            throw new IllegalArgumentException(
                    "Матрица должна быть квадратной"
            );
        }
        Complex det = determinant();
        if (det.module() < 0.000001) {
            throw new ArithmeticException(
                    "Обратной матрицы не существует"
            );
        }
        ComplexMatrix result =
                new ComplexMatrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Complex minorDet =
                        getMinor(i, j).determinant();
                if ((i + j) % 2 != 0) {
                    minorDet =
                            new Complex(0, 0).subtract(minorDet);
                }
                result.matrix[j][i] =
                        minorDet.divide(det);
            }
        }
        return result;
    }
    public ComplexMatrix divide(ComplexMatrix b) {
        if (b.rows != b.columns) {
            throw new IllegalArgumentException(
                    "Вторая матрица должна быть квадратной"
            );
        }
        return multiply(b.inverse());
    }
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}