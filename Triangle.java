package com.javarush.test.test;

import java.util.Locale;
import java.util.Scanner;

/**
 * Created by crazz on 18.02.2016.
 */
public class Triangle
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        sc.close();

        double katetx = (double)-c/a;
        double katety = (double)-c/b;
        double res = Math.abs((katetx*katety)/2);
        System.out.println(String.format(Locale.ENGLISH,"%.15f",res));

    }
}
