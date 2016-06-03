package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DeikstraRMQ
{
    private static int N;
    private static int S;
    private static int F;
    private static int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
    private static MultiList graph; // описание графа
    private static int[][] linkMatrix;

    /* Алгоритм Дейкстры за O(E log V) */
    private static void dijkstraRMQ(int S, int F)
    {
        boolean[] used = new boolean[N]; // массив пометок
        int[] prev = new int[N]; // массив предков
        int[] dist = new int[N]; // массив расстояний
        RMQ rmq = new RMQ(N); // RMQ

             /* Инициализация */
        Arrays.fill(prev, -1);
        Arrays.fill(dist, INF);
        rmq.set(S, dist[S] = 0);

        for (; ; ) {
            int v = rmq.minIndex(); // выбираем ближайшую вершину
            if (v == -1 || v == F) break; // если она не найдена, или является конечной, то выходим

            used[v] = true; // помечаем выбранную вершину
            rmq.set(v, INF); // и сбрасываем ее значение в RMQ

            for (int i = graph.head[v]; i != 0; i = graph.next[i]) { // проходим пр смежным вершинам
                int nv = graph.vert[i];
                int cost = graph.cost[i];
                if (!used[nv] && dist[nv] > dist[v] + cost) { // если можем улучшить оценку расстояния
                    rmq.set(nv, dist[nv] = dist[v] + cost); // улучшаем ее
                    prev[nv] = v; // помечаем предка
                }
            }
        }

//             /* Восстановление пути */
//        Stack stack = new Stack();
//        for (int v = end; v != -1; v = prev[v]) {
//            stack.push(v);
//        }
//        int[] sp = new int[stack.size()];
//        for (int i = 0; i < sp.length; i++)
//            sp[i] = stack.pop() + 1;

            /* Вывод результата */
        System.out.printf("Кратчайшее расстояние между %d и %d = %d%n", S + 1, F + 1, dist[F]);
//        System.out.println("Кратчайший путь: " + Arrays.toString(sp));
    }

    private static void read() throws FileNotFoundException
    {
//        Scanner sc = new Scanner(new FileReader("dijkstra.in"));
        Scanner sc = new Scanner(new FileReader("c:\\data1.txt"));
        N = sc.nextInt();
        S = sc.nextInt() - 1;
        F = sc.nextInt() - 1;
//        dist = new int[N];
        linkMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
//            dist[i] = INF;
            for (int j = 0; j < N; j++)
                linkMatrix[i][j] = sc.nextInt();
        }
        sc.close();
    }

    private static void write(String result) throws FileNotFoundException
    {
//        PrintWriter fw = new PrintWriter("dijkstra.out");
        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(result);
        fw.close();
    }

    public static void main(String[] args) throws IOException
    {
        read();
        dijkstraRMQ(S, F);
    }

    /* Класс списка с несколькими головами */
    class MultiList
    {
        int[] head;
        int[] next;
        int[] vert;
        int[] cost;
        int cnt = 1;

        MultiList(int vNum, int eNum)
        {
            head = new int[vNum];
            next = new int[eNum + 1];
            vert = new int[eNum + 1];
            cost = new int[eNum + 1];
        }

        void add(int u, int v, int w)
        {
            next[cnt] = head[u];
            vert[cnt] = v;
            cost[cnt] = w;
            head[u] = cnt++;
        }
    }

    /* Класс RMQ */
    static class RMQ
    {
        int n;
        int[] val;
        int[] ind;

        RMQ(int size)
        {
            n = size;
            val = new int[2 * n];
            ind = new int[2 * n];
            Arrays.fill(val, INF);
            for (int i = 0; i < n; i++)
                ind[n + i] = i;
        }

        void set(int index, int value)
        {
            val[n + index] = value;
            for (int v = (n + index) / 2; v > 0; v /= 2) {
                int l = 2 * v;
                int r = l + 1;
                if (val[l] <= val[r]) {
                    val[v] = val[l];
                    ind[v] = ind[l];
                } else {
                    val[v] = val[r];
                    ind[v] = ind[r];
                }
            }
        }

        int minIndex()
        {
            return val[1] < INF ? ind[1] : -1;
        }
    }
}
