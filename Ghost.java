package ru.loud;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Ghost {
    private static int[] wordsArr;
    private static int rounds;
    private static int words;
    private static Map<Integer, Integer> variants;
    /**
     * variants combinations of words
     */
    private static int[] caseArr;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < t; i++) {
            rounds = sc.nextInt();
            words = sc.nextInt();
            wordsArr = new int[words];
            sc.nextLine();
            for (int j = 0; j < words; j++) {
                if (sc.nextLine().length() % 2 == 0)
                    wordsArr[j] = 1;
                else wordsArr[j] = 2;
            }
            generate();
            caseArr = new int[4];
            play();
            System.out.println("Case #" + i + ":");
            for (int j = 0; j < 4; j++) {
                System.out.println(caseArr[j] >= 0 ? "victory" : "defeat");
            }
            /** show sum of victory/defeat for every case */
//            System.out.println("Scenario 1:" + caseArr[0] + " Scenario 2:" + caseArr[1] + " Scenario 3:" + caseArr[2] + " Scenario 4:" + caseArr[3]);
        }
        sc.close();


    }

    private static void play() {
        boolean case1, case2, case3, case4 = true;
        for (Map.Entry<Integer, Integer> variant : variants.entrySet()) {
            String s1 = variant.getKey().toString();
            String s1Without1 = s1.replaceAll("1", "");
            if (s1Without1.length() == 0) {
                case1 = true;
                case3 = false;
                if (s1.length() % 2 == 0) {
                    case4 = true;
                    case2 = false;
                } else {
                    case2 = true;
                }
            } else if (s1.replaceAll("2", "").length() == 0) {
                case1 = false;
                case2 = false;
                case4 = true;
                case3 = s1.length() % 2 != 0;

            } else if (s1Without1.length() % 2 == 0) {
                case1 = true;
                case2 = true;
                case3 = false;
                case4 = s1.length() % 2 == 0;
            } else {
                case1 = false;
                case3 = true;
                if (s1.length() % 2 == 0) {
                    case2 = true;
                    case4 = false;
                } else {
                    case2 = false;
                    case4 = true;
                }
            }
            int m = variant.getValue();
            caseArr[0] += (case1) ? m : -1 * m;
            caseArr[1] += (case2) ? m : -1 * m;
            caseArr[2] += (case3) ? m : -1 * m;
            caseArr[3] += (case4) ? m : -1 * m;
        }
    }


    private static void generate() {
        int[] a = new int[words * 2 + 1];
        variants = new HashMap<Integer, Integer>();
        StringBuilder sb;
        while (a[0] == 0) {
            sb = new StringBuilder();
            for (int i = rounds; i >= 1; i--) {
                sb.append(wordsArr[a[i]]);
            }
            Integer key = Integer.valueOf(sb.toString());
            if (variants.containsKey(key)) {
                variants.put(key, variants.get(key) + 1);
            } else
                variants.put(key, 1);

            a[rounds]++;
            int j = rounds;
            while (a[j] == words) {
                a[j] = 0;
                a[--j]++;
            }
        }
    }
}