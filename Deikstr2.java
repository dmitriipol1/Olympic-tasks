package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Deikstr2
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
        int min;
        int zz;
        int[] queue = new int[N * N];//v3 - теперь со вкусом очереди!
        int qH = 0; // голова очереди
        int qT = 0; // хвост
        queue[qT++] = S; // помещаем в очередь S

        while (qH < qT) {
            min = queue[qH++];
            for (int i = 0; i < N; i++) {
                if (i != min && linkMatrix[min][i] > 0 && !used[i])
                    if (dist[i] > (zz = (dist[min] + linkMatrix[min][i]))) {
                        queue[qT++] = i;
                        dist[i] = zz;
                    }
            }
            used[min] = true;
        }
        if (dist[F] == INF)
            write("-1");
        else
            write(String.valueOf(dist[F]));
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