package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by crazz on 12.02.2016.
 */
public class Floyd

{
    public static final int INF = Integer.MAX_VALUE / 2;
    private static int[][] linkMatrix;
    private static int N;


    private static void Floydalgo() throws FileNotFoundException
    {
                PrintWriter fw = new PrintWriter("floyd.out");
//        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (j == k)
                        linkMatrix[j][k] = 0;
                    else
                        linkMatrix[j][k] = Math.min(linkMatrix[j][k], linkMatrix[j][i] + linkMatrix[i][k]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (linkMatrix[i][j] == INF)
                    fw.print(0 + " ");
                else fw.print(linkMatrix[i][j] + " ");
            }
            fw.println();
        }
        fw.close();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        read();
        Floydalgo();
    }

    private static void read() throws FileNotFoundException
    {
//        Scanner sc = new Scanner(new FileReader("floyd.in"));
        Scanner sc = new Scanner(new FileReader("c:\\data1.txt"));

        N = sc.nextInt();

        linkMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int t = sc.nextInt();
                if (t == 0)
                    linkMatrix[i][j] = INF;
                else
                    linkMatrix[i][j] = t;
            }
        }
        sc.close();
    }
}