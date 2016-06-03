package com.javarush.test.test;

/**
 * Created by crazz on 16.04.2016.
 */
public class countcode {
    public static void main(String[] args) {

        String str = "cozcop";
        int c = 0, x = str.indexOf("co");
        while (x >= 0) {
            str = str.substring(x+3, str.length());
            if (str.length()>0 && str.charAt(0) == 'e')
                c++;
            x = str.indexOf("co");
        }
        System.out.println(c);
    }
}
