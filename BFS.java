package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by crazz on 12.02.2016.
 */
public class BFS
{
    public static final int INF = Integer.MAX_VALUE / 2;
    private static int[][] linkMatrix;
    private static int N;


    public static void main(String[] args) throws FileNotFoundException
    {
//        Date start = new Date();
        read();
        BFSalgo();
//        Date stop = new Date();
//        System.out.println(stop.getTime() - start.getTime());
    }

    private static void write(int result) throws FileNotFoundException
    {
//        PrintWriter fw = new PrintWriter("floyd.out");
        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.println(result);
        fw.close();
    }

    private static void BFSalgo()
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    linkMatrix[j][k] = Math.min(linkMatrix[i][j],linkMatrix[j][i]);
                }
            }
        }
    }


    private static void read() throws FileNotFoundException
    {
//        Scanner sc = new Scanner(new FileReader("floyd.in"));
        Scanner sc = new Scanner(new FileReader("c:\\data1.txt"));

        N = sc.nextInt();

        linkMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                linkMatrix[i][j] = sc.nextInt();
            }
        }
        sc.close();
    }
}