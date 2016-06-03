package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by crazz on 14.02.2016.
 */
public class deikstr
{
    private static int INF = Integer.MAX_VALUE / 2;
    private static int N;
    private static int S;
    private static int F;

    public static void main(String[] args) throws IOException
    {
        Deikstr(read());
    }

    private static void Deikstr(int[][] linkMatrix) throws FileNotFoundException
    {

       // boolean[] used = new boolean[N];
        int[] dist = new int[N];
        int[] queue = new int[N*2];
        int qH = 0; // голова очереди
        int qT = 0; // хвост
        int t, nv;

        Arrays.fill(dist, INF);
        dist[S] = 0;
        //used[S] = true; // помечаем исходную клетку
        queue[qT++] = S; // помещаем ее в очередь

        while (qH < qT) {
            t = queue[qH++];
            for (int j = 0; j < N; j++) {
                if (t != j && linkMatrix[t][j] != -1)
                    if ( dist[j] > (dist[t])) { // + linkMatrix[t][j]
                        //used[t] = true;
                        nv = j;
                        if (dist[nv] > dist[t] || linkMatrix[t][nv] == 0) {
                            dist[nv] = dist[t] + linkMatrix[t][nv];
                            queue[qT++] = nv;
                        }
                    }
            }
        }

        String out;
        if (dist[F] == INF)
            out = "-1";
        else out = String.valueOf(dist[F]);
        write(out);
    }

    private static int[][] read() throws FileNotFoundException
    {
//        Scanner sc = new Scanner(new FileReader("dijkstra.in"));
        Scanner sc = new Scanner(new FileReader("c:\\data1.txt"));
        N = sc.nextInt();
        S = sc.nextInt() - 1;
        F = sc.nextInt() - 1;

        int[][] linkMatrix = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                linkMatrix[i][j] = sc.nextInt();

        sc.close();
        return linkMatrix;
    }

    private static void write(String result) throws FileNotFoundException
    {
//        PrintWriter fw = new PrintWriter("dijkstra.out");
        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(result);
        fw.close();
    }
}
