package org.tonkushin;

import java.util.ArrayList;
import java.util.List;

public class Demukron {
    int[][] matrix;               // матрица смежности
    int[] sum;                    // сумма полустепеней захода
    List<Integer>[] graph;        // граф
    List<Integer>[] resultGraph;  // результирующий граф

    public Demukron(List<Integer>[] graph) {
        this.graph = graph;
        this.resultGraph = new List[graph.length];
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

    public List<Integer>[] sort() {
        List<Integer> zeroes = new ArrayList<>();
        List<Integer> subtract = new ArrayList<>();
        for (int i = 0; i < sum.length; i++) {
            calcSum();

            // Ищем нулевые колонки и добавляем соответствующие элементы в упорядоченный граф
            if (sum[i] == 0 && !zeroes.contains(i)) {
                zeroes.add(i);
                subtract.add(i);
                resultGraph[i] = graph[i];
            }

            // Вычитаем вектор i из матрицы
            for (Integer s : subtract) {
                subtract(s);
                printMatrix();
            }

            subtract.clear();
        }

        return resultGraph;
    }

    private void calcSum(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sum[j] += matrix[i][j];
            }
        }
    }

    private void subtract(int rowNum) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] -= matrix[rowNum][j];

                if (matrix[i][j] < 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
