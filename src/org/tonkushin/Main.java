package org.tonkushin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Тест алгоритма Демукрона");

        System.out.println("Введите количество вершин");
        int v = scan.nextInt();             // количество вершин
        List<Integer>[] graph = new List[v];
        for (int i = 0; i < v; i++)
            graph[i] = new ArrayList<>();

        System.out.println("Введите количество рёбер");
        int e = scan.nextInt();             // количество рёбер
        System.out.println("Введите "+ e +" координат x, y");
        for (int i = 0; i < e; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();

            // добавляем ребро
            graph[x].add(y);
        }
        Demukron demukron = new Demukron(graph);

        System.out.println("Результирующий граф");
        System.out.println(Arrays.toString(demukron.sort()));
    }
}
