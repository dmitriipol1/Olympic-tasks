package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Slalom
{
    private static int N, col;

    public static void main(String[] args) throws FileNotFoundException
    {
        int[] list = read();
        int result = count(list);
        write(result);
    }

    private static int count(int[] list)
    {

        int[] sum = new int[col];
        int result = 0;
        for (int j = 1; j <= N; j++) {
            sum[col - j] = list[col - j];
        }
        int i = col - N - 1;
        for (int z = N - 1; z >= 0; z--) {
            for (int j = 0; j < z; j++) {
                sum[i] = list[i] + Math.max(sum[i + z], sum[i + z + 1]);
                i--;
            }
        }
        return sum[0];
    }

    private static int[] read() throws FileNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine());
        col = 0;
        for (int i = 1; i <= N; i++) {
            col += i;
        }
        int[] list = new int[col];
        for (int i1 = 0; i1 < list.length; i1++) {
            list[i1] = sc.nextInt();
        }
        sc.close();
        return list;
    }


    private static void write(int result) throws FileNotFoundException
    {
        System.out.println(result);
//        PrintWriter fw = new PrintWriter("c:\\data2.txt");
//        fw.println(result);
//        fw.close();
    }
}

