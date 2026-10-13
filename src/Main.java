import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ComplexMatrix a = null;
        ComplexMatrix b = null;

        while (true) {
            System.out.println("\n1. Создать матрицу A");
            System.out.println("2. Создать матрицу B");
            System.out.println("3. Показать матрицы");
            System.out.println("4. Сложить матрицы");
            System.out.println("5. Умножить матрицы");
            System.out.println("6. Разделить A на B");
            System.out.println("7. Транспонировать матрицу");
            System.out.println("8. Найти определитель");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = sc.nextInt();

            try {
                if (choice == 0) {
                    break;
                }

                if (choice == 1 || choice == 2) {
                    System.out.print("Количество строк: ");
                    int rows = sc.nextInt();

                    System.out.print("Количество столбцов: ");
                    int cols = sc.nextInt();

                    ComplexMatrix c = new ComplexMatrix(rows, cols);

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            System.out.println("Элемент [" + (i + 1)
                                    + "][" + (j + 1) + "]");

                            System.out.print("Действительная часть: ");
                            double re = sc.nextDouble();

                            System.out.print("Мнимая часть: ");
                            double im = sc.nextDouble();

                            c.set(i, j, new ComplexNumber(re, im));
                        }
                    }

                    if (choice == 1) {
                        a = c;
                    } else {
                        b = c;
                    }
                } else if (choice == 3) {
                    System.out.println("Матрица A:");
                    System.out.println(a == null ? "Не создана" : a);

                    System.out.println("Матрица B:");
                    System.out.println(b == null ? "Не создана" : b);
                } else if (choice >= 4 && choice <= 6) {
                    if (a == null || b == null) {
                        System.out.println("Сначала создайте обе матрицы");
                        continue;
                    }

                    if (choice == 4) {
                        System.out.println(a.add(b));
                    } else if (choice == 5) {
                        System.out.println(a.multiply(b));
                    } else {
                        System.out.println(a.divide(b));
                    }
                } else if (choice == 7 || choice == 8) {
                    System.out.print("Выберите матрицу (1 - A, 2 - B): ");
                    int number = sc.nextInt();

                    ComplexMatrix c;

                    if (number == 1) {
                        c = a;
                    } else if (number == 2) {
                        c = b;
                    } else {
                        System.out.println("Неверный номер матрицы");
                        continue;
                    }

                    if (c == null) {
                        System.out.println("Матрица не создана");
                        continue;
                    }

                    if (choice == 7) {
                        System.out.println(c.transpose());
                    } else {
                        System.out.println(c.determinant());
                    }
                } else {
                    System.out.println("Нет такого пункта меню");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("Программа завершена");
    }
}