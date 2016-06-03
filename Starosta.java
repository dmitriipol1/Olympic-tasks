package com.javarush.test.test;

import java.util.Scanner;

public class Starosta
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        int result = (int) Math.pow(2,n);
        if (result>1000000) System.out.println("TOO HARD");
        else System.out.println(result);
    }
}
