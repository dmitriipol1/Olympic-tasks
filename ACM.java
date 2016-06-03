package com.javarush.test.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACM
{
    static private List<String> Abb = new ArrayList<>();

    public static void main(String[] args)
    {
        List<List<String>> numberlist = optimize(read());
        for (int i1 = 0; i1 < numberlist.size(); i1++) {
            List<String> aList = numberlist.get(i1);
            for (int i = 0; i < aList.get(0).length(); i++) {
                Abb.set(i1, Abb.get(i1).replaceAll(String.valueOf(aList.get(0).charAt(i)), String.valueOf(i)));
            }
            numberlist.get(i1).remove(0);
        }
        System.out.println(Abb);
        System.out.println(numberlist);
    }

    private static List<List<String>> read()
    {
        Scanner sc = new Scanner(System.in);
        String input;
        StringBuffer unusedWords = new StringBuffer();
        List<List<String>> stringsAndwords = new ArrayList<>();

        while (!(input = sc.nextLine()).equals("0")) {
            int nUnW = Integer.parseInt(input);
            for (int i = 0; i < nUnW; i++) {
                unusedWords.append(sc.nextLine()).append(" ");
            }
            String s;
            while (!(s = sc.nextLine()).equals("LAST CASE")) {
                List<String> tt = new ArrayList<>();
                String[] t = s.split(" ");
                tt.add(t[0].toLowerCase());
                Abb.add(t[0].toLowerCase());
                for (int i = 1; i < t.length; i++) {
                    String s1 = t[i];
                    if (!unusedWords.toString().contains(s1))
                        tt.add(s1);
                }
                stringsAndwords.add(tt);
            }
        }
        return stringsAndwords;
    }

    private static List<List<String>> optimize(List<List<String>> list)
    {

        for (List<String> aList : list) {
            for (int w = 1; w < aList.size(); w++) {
                String[] abbrev = aList.get(0).split("");
                for (int i = 0; i < abbrev.length; i++) {
                    aList.set(w, aList.get(w).replaceAll(abbrev[i], String.valueOf(i)));
                }
                aList.set(w, aList.get(w).replaceAll("\\D", ""));
            }
        }

        System.out.println(list);
        return list;
    }

    private static int count(List<List<String>> list)
    {

        return 0;
    }
}