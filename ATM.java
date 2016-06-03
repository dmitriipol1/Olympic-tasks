package com.javarush.test.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {
    private static final int[] money = new int[]{1, 3, 5, 10, 25, 50, 100, 500, 1000, 5000};
    private static final int INIT_CASH_VALUE = 0;
    private static Map<Integer, Integer> cash = new HashMap<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        initCash();

        while (!input.equals("quit")) {
            System.out.println(">" + input);
            if (input.startsWith("put")) {
                String[] tmp = input.replaceAll("put ", "").split(" ");
                try {
                    if (tmp.length == 2 && Integer.parseInt(tmp[0]) > 0 && Integer.parseInt(tmp[1]) > 0)
                        deposit(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                    else System.out.println("Ошибка ввода");
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода");
                }
            } else if (input.startsWith("get")) {
                try {
                    withdraw(Integer.parseInt(input.replace("get ", "")));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка ввода");
                }
            } else if (input.equals("dump")) {
                dump();
            } else if (input.equals("state")) {
                state();
            }
            input = sc.nextLine();
        }
    }

    private static void initCash() {
        for (int nominal : money) {
            cash.put(nominal, INIT_CASH_VALUE);
        }
    }

    private static void deposit(Integer nominal, Integer count) {
        if (cash.containsKey(nominal)) {
            cash.put(nominal, count);
            state();
        } else System.out.println("Ошибка. Купюры такого номинала не принимаются.");
    }

    private static void withdraw(Integer amount) {
        String res = "";
        int total = amount;
        int enough, count;
        for (int i = money.length - 1; i > 0; i--) {
            count = cash.get(money[i]);
            enough = amount / money[i];
            if (money[i] <= amount && count > 0)
                if (amount / money[i] >= count) {
                    amount -= money[i] * count;
                    res += money[i] + "=" + count + ",";
                    cash.put(money[i], 0);
                } else {
                    amount -= money[i] * enough;
                    res += money[i] + "=" + enough + ",";
                    cash.put(money[i], count - enough);
                }
        }
        System.out.println(res + " всего " + (total - amount));
        if (amount > 0)
            System.out.println("без " + amount);
    }

    private static void dump() {
        for (int i = money.length - 1; i > 0; i--) {
            if (cash.get(money[i]) > 0)
                System.out.println("" + money[i] + " " + cash.get(money[i]));
        }
    }

    private static void state() {
        int sumInAtm = 0;
        for (int nominal : money) {
            sumInAtm += nominal * cash.get(nominal);
        }
        System.out.println("Всего " + sumInAtm);
    }

}