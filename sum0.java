package com.javarush.test.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class sum0
{
    private static int[] arrA;
    private static int[] arrB;

    public static void main(String[] args) throws IOException
    {
        read();
        solution();
    }

    private static void solution()
    {
        int sum = 0, c, from, to;
        for (int i = 0; i < arrB.length; i++) {
            from = Math.min(arrB[2 * i], arrB[2 * i + 1]);
            to = Math.max(arrB[2 * i], arrB[2 * i] + 1);

//            for (int j = 0; j < to-from; j++) {
//                sum+=arrA[from+j];
//            }
            System.out.println(from);
            System.out.println(to);
        }
    }

    private static void read() throws IOException
    {
//        BufferedReader br = new BufferedReader(new FileReader(new File("sum0.in")));
        BufferedReader br = new BufferedReader(new FileReader(new File("c:\\data1.txt")));
        String[] s;
        StringBuilder sb = new StringBuilder();
        sb.append(br.readLine());
        s = sb.toString().split(" ");
        int n = Integer.parseInt(s[0]);
        int x = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);
        int a = Integer.parseInt(s[3]);
        sb.delete(0, sb.length());

        arrA = new int[n];
        arrA[0] = a;
        for (int i = 1; i < n; i++) {
            arrA[i] = (x * arrA[i - 1]) + y;
        }

        sb.append(br.readLine());
        s = sb.toString().split(" ");
        int m = Integer.parseInt(s[0]);
        int z = Integer.parseInt(s[1]);
        int t = Integer.parseInt(s[2]);
        int b = Integer.parseInt(s[3]);
        sb.delete(0, sb.length());
        br.close();

        arrB = new int[n];
        arrB[0] = b;
        for (int i = 1; i < m; i++) {
            arrB[i] = (z * arrB[i - 1]) + t;
        }
        System.out.println();
    }
}
