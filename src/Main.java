import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Map<String, ComplexMatrix> matrices = new LinkedHashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1)Создать матрицу");
            System.out.println("2)Показать все матрицы");
            System.out.println("3)Сложить матрицы");
            System.out.println("4)Умножить матрицы");
            System.out.println("5)Разделить матрицы");
            System.out.println("6)Транспонировать матрицу");
            System.out.println("7)Определитель матрицы");
            System.out.println("8)Обратная матрица");
            System.out.println("0)Выход");
            System.out.print("Выберите действие(1-8, 0): ");

            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        createMatrix();
                        break;
                    case 2:
                        showMatrices();
                        break;
                    case 3:
                        calculate(1);
                        break;
                    case 4:
                        calculate(2);
                        break;
                    case 5:
                        calculate(3);
                        break;
                    case 6:
                        transposeMatrix();
                        break;
                    case 7:
                        determinant();
                        break;
                    case 8:
                        inverseMatrix();
                        break;
                    case 0:
                        System.out.println("Программа завершена.");
                        return;
                    default:
                        System.out.println("Нет такого пункта меню.");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    static void createMatrix() {
        System.out.print("Название матрицы: ");
        String name = sc.next();

        if (matrices.containsKey(name)) {
            System.out.println("Матрица с таким именем уже существует.");
            return;
        }

        System.out.print("Количество строк: ");
        int rows = sc.nextInt();

        System.out.print("Количество столбцов: ");
        int cols = sc.nextInt();

        if (rows <= 0 || cols <= 0) {
            System.out.println("Размеры должны быть положительными.");
            return;
        }

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println("Элемент [" + (i + 1) + "][" + (j + 1) + "]");

                System.out.print("Действительная часть: ");
                double re = sc.nextDouble();

                System.out.print("Мнимая часть: ");
                double im = sc.nextDouble();

                matrix.set(i, j, new ComplexNumber(re, im));
            }
        }

        matrices.put(name, matrix);
        System.out.println("Матрица " + name + " создана.");
    }

    static void showMatrices() {
        if (matrices.isEmpty()) {
            System.out.println("Матриц пока нет.");
            return;
        }

        for (String name : matrices.keySet()) {
            System.out.println("\nМатрица " + name + ":");
            System.out.println(matrices.get(name));
        }
    }

    static ComplexMatrix chooseMatrix(String message) {
        System.out.print(message);
        String name = sc.next();

        ComplexMatrix matrix = matrices.get(name);

        if (matrix == null) {
            throw new IllegalArgumentException(
                    "Матрица " + name + " не найдена."
            );
        }

        return matrix;
    }

    static void calculate(int operation) {
        ComplexMatrix a = chooseMatrix("Первая матрица: ");
        ComplexMatrix b = chooseMatrix("Вторая матрица: ");

        ComplexMatrix result;

        if (operation == 1) {
            result = a.add(b);
        } else if (operation == 2) {
            result = a.multiply(b);
        } else {
            result = a.divide(b);
        }

        saveResult(result);
    }

    static void transposeMatrix() {
        ComplexMatrix matrix = chooseMatrix("Название матрицы: ");
        ComplexMatrix result = matrix.transpose();

        saveResult(result);
    }

    static void inverseMatrix() {
        ComplexMatrix matrix = chooseMatrix("Название матрицы: ");
        ComplexMatrix result = matrix.inverse();

        saveResult(result);
    }

    static void determinant() {
        ComplexMatrix matrix = chooseMatrix("Название матрицы: ");

        System.out.println("Определитель: " + matrix.determinant());
    }

    static void saveResult(ComplexMatrix result) {
        System.out.println("\nРезультат:");
        System.out.println(result);

        System.out.print("Под каким именем сохранить результат? ");
        String name = sc.next();

        if (matrices.containsKey(name)) {
            System.out.println("Это имя уже занято. Выберите другое.");
            return;
        }

        matrices.put(name, result);
        System.out.println("Результат сохранён как " + name + ".");
    }
}
