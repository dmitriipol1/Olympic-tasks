package com.javarush.test.test;

import java.io.*;
import java.util.Arrays;

public class sumdist
{
    private static int N;
    private static int[][] linkMatrix;

    private static void Floydalgo() throws FileNotFoundException
    {
//        Date start = new Date();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    if (j != k && linkMatrix[j][i] != -1 && linkMatrix[i][k] != -1)
                        if (linkMatrix[j][k] == -1 || linkMatrix[j][k] > linkMatrix[j][i] + linkMatrix[i][k])
                            linkMatrix[j][k] = linkMatrix[j][i] + linkMatrix[i][k];

        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < i; j++)
                if (linkMatrix[i][j] != -1)
                    count += linkMatrix[i][j];

//        PrintWriter fw = new PrintWriter("sumdist.out");
        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(count);
        fw.close();
//        System.out.println(new Date().getTime() - start.getTime());
    }

    public static void main(String[] args) throws IOException
    {
        read();
        Floydalgo();
    }

    private static void read() throws IOException
    {
//        Date start = new Date();
//        BufferedReader br = new BufferedReader(new FileReader(new File("sumdist.in")));
        BufferedReader br = new BufferedReader(new FileReader(new File("c:\\data1.txt")));

        String[] s;
        StringBuilder sb = new StringBuilder();
        sb.append(br.readLine());
        s = sb.toString().split(" ");
        N = Integer.parseInt(s[0]);
        int nl = Integer.parseInt(s[1]);
        sb.delete(0, sb.length());

        linkMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(linkMatrix[i], -1);
//            linkMatrix[i][i] = 0;
            sb.append(br.readLine()).append(" ");
        }
        for (int i = 0; i < nl - N; i++) {
            sb.append(br.readLine()).append(" ");
        }

        br.close();
        s = sb.toString().split(" ");
        for (int i = 0; i < nl * 2; i += 2) {
            linkMatrix[Integer.parseInt(s[i]) - 1][Integer.parseInt(s[i + 1]) - 1] = 1;
            linkMatrix[Integer.parseInt(s[i + 1]) - 1][Integer.parseInt(s[i]) - 1] = 1;
        }
//        System.out.println(new Date().getTime() - start.getTime());
    }
}