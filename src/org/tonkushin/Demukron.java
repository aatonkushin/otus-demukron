package org.tonkushin;

import java.util.ArrayList;
import java.util.List;

public class Demukron {
    int[][] matrix;               // матрица смежности
    int[] sum;                    // сумма полустепеней захода
    List<Integer>[] graph;        // граф

    List<Integer> order = new ArrayList<>();   // список с нулевыми узлами

    public Demukron(List<Integer>[] graph) {
        this.graph = graph;
        createMatrix();
    }

    private void createMatrix() {
        matrix = new int[graph.length][graph.length];
        sum = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                matrix[i][graph[i].get(j)] = 1;
                sum[graph[i].get(j)] += 1;
            }
        }

        printMatrix();
    }

    public void printMatrix() {
        System.out.println("Матрица смежности: ");
        System.out.print(" | ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---" + "-".repeat(2 * matrix.length));

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(i + "| ");

            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("---" + "-".repeat(2 * matrix.length));

        System.out.print(" | ");
        for (int j : sum) {
            System.out.print(j + " ");
        }

        System.out.println();
    }

    public void sort() {
        order = new ArrayList<>(matrix.length);

        List<Integer> subtract = new ArrayList<>(); // список строк для вычитания из матрицы

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sum = calcSum();

                // Ищем нулевые колонки и добавляем соответствующие элементы в упорядоченный граф
                if (sum[j] == 0 && !order.contains(j)) {
                    order.add(j);
                    subtract.add(j);
                }
            }

            // Вычитаем вектор j из матрицы
            for (Integer s : subtract) {
                subtract(s);
                sum = calcSum();
                printMatrix();
            }

            subtract.clear();
        }
    }

    private int[] calcSum() {
        int[] retVal = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                retVal[j] += matrix[i][j];
            }
        }

        return retVal;
    }

    private void subtract(int rowNum) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] -= matrix[rowNum][j];

                // Сумма не может быть отрицательной
                if (matrix[i][j] < 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
