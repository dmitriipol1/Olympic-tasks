package com.javarush.test.test;

/**
 * Created by crazz on 11.04.2016.
 */
public class countSubstring {
    private static int countHi(String str) {
        String[] res = (str + "\0").split("hi");

        int count = (res.length - 1);
        return count;

    }

    public static void main(String[] args) {
        System.out.println(countHi("вариыаhiыивhiыивлоаиывлоаиывлоаиHIksdjfksdjfksdjfklsjdfklhi"));
    }
}
