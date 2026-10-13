public class ComplexMatrix {
    private int rows;
    private int cols;
    private ComplexNumber[][] data;

    public ComplexMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Неверный размер матрицы");
        }

        this.rows = rows;
        this.cols = cols;
        data = new ComplexNumber[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    public void set(int i, int j, ComplexNumber value) {
        data[i][j] = value;
    }

    public ComplexMatrix add(ComplexMatrix b) {
        if (rows != b.rows || cols != b.cols) {
            throw new IllegalArgumentException("Размеры матриц не совпадают");
        }

        ComplexMatrix c = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                c.data[i][j] = data[i][j].add(b.data[i][j]);
            }
        }

        return c;
    }

    public ComplexMatrix multiply(ComplexMatrix b) {
        if (cols != b.rows) {
            throw new IllegalArgumentException("Нельзя умножить эти матрицы");
        }

        ComplexMatrix c = new ComplexMatrix(rows, b.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < b.cols; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);

                for (int k = 0; k < cols; k++) {
                    sum = sum.add(data[i][k].multiply(b.data[k][j]));
                }

                c.data[i][j] = sum;
            }
        }

        return c;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix c = new ComplexMatrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                c.data[j][i] = data[i][j];
            }
        }

        return c;
    }

    public ComplexMatrix minor(int row, int col) {
        ComplexMatrix c = new ComplexMatrix(rows - 1, cols - 1);
        int x = 0;

        for (int i = 0; i < rows; i++) {
            if (i == row) {
                continue;
            }

            int y = 0;

            for (int j = 0; j < cols; j++) {
                if (j == col) {
                    continue;
                }

                c.data[x][y] = data[i][j];
                y++;
            }

            x++;
        }

        return c;
    }

    public ComplexNumber determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Определитель есть только у квадратной матрицы");
        }

        if (rows == 1) {
            return data[0][0];
        }

        ComplexNumber det = new ComplexNumber(0, 0);

        for (int j = 0; j < cols; j++) {
            ComplexNumber term = data[0][j]
                    .multiply(minor(0, j).determinant());

            if (j % 2 == 0) {
                det = det.add(term);
            } else {
                det = det.subtract(term);
            }
        }

        return det;
    }

    public ComplexMatrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Матрица должна быть квадратной");
        }

        ComplexNumber det = determinant();

        if (det.getRe() * det.getRe() + det.getIm() * det.getIm()
                < 1e-12) {
            throw new ArithmeticException("Обратной матрицы нет");
        }

        ComplexMatrix c = new ComplexMatrix(rows, cols);

        if (rows == 1) {
            c.data[0][0] = new ComplexNumber(1, 0).divide(det);
            return c;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ComplexNumber value = minor(i, j).determinant();

                if ((i + j) % 2 != 0) {
                    value = new ComplexNumber(0, 0).subtract(value);
                }

                c.data[j][i] = value.divide(det);
            }
        }

        return c;
    }

    public ComplexMatrix divide(ComplexMatrix b) {
        if (b.rows != b.cols) {
            throw new IllegalArgumentException(
                    "Вторая матрица должна быть квадратной");
        }

        return multiply(b.inverse());
    }

    public String toString() {
        String s = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                s += data[i][j] + "\t";
            }

            s += "\n";
        }

        return s;
    }
}