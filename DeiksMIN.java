package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DeiksMIN
{
    private static int N;
    private static int S;
    private static int F;
    private static int INF = Integer.MAX_VALUE / 2;
    private static int[] dist;
    private static int[][] linkMatrix;

    public static void main(String[] args) throws IOException
    {
        read();
        Deikstr();
    }

    private static void Deikstr() throws FileNotFoundException
    {
        boolean[] used = new boolean[N];
        dist[S] = 0;
        int min = S;
        int zz;

        while (true) {
            for (int i = 0; i < N; i++)
                if (i != min && !used[i])
                    if (linkMatrix[min][i] > 0 && dist[i] > (zz = (dist[min] + linkMatrix[min][i])))
                        dist[i] = zz;


            used[min] = true;
            int tmp = INF, tmpi = -1;
            for (int i = 0; i < N; i++) {
                if (!used[i] && dist[i] < tmp) {
                    tmp = dist[i];
                    tmpi = i;
                }
            }
            if (tmpi == -1) break;
            else if (tmpi == F) {
                if (dist[F] == INF) write("-1");
                else
                    write(String.valueOf(dist[F]));
                break;
            } else
                min = tmpi;
        }
        if (dist[F] == INF) write("-1");

    }

    private static void read() throws FileNotFoundException
    {
//        Scanner sc = new Scanner(new FileReader("dijkstra.in"));
        Scanner sc = new Scanner(new FileReader("c:\\data1.txt"));
        N = sc.nextInt();
        S = sc.nextInt() - 1;
        F = sc.nextInt() - 1;
        dist = new int[N];
        linkMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            dist[i] = INF;
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
}