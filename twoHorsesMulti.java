package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


public class twoHorsesMulti extends Thread
{
    private static int final1x;
    private static int final1y;
    private static int start1x;
    private static int start1y;
    private static int start2x;
    private static int start2y;
    private static int final2x;
    private static int final2y;
    volatile private static boolean h1working;
    volatile private static boolean[][] mark = new boolean[8][8];
    volatile private static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws InterruptedException, FileNotFoundException
    {
        Date start = new Date();
        read();
        mark[start2x][start2y] = true;
        mark[start1x][start1y] = true;
        Thread horse1 = new twoHorsesMulti();
        Thread horse2 = new twoHorsesMulti();
        horse1.start();
        horse1.join();
        StringBuilder res1 = new StringBuilder(result);
        result.delete(0, result.length());
        horse2.start();
        horse2.join();
        StringBuilder res2 = new StringBuilder(result);
        result.delete(0, result.length());
        result.append(res1, 0, res1.length() - 5).append(res2).append(res1, res1.length() - 5, res1.length());
        write(result.toString());
        Date stop = new Date();
        System.out.println(stop.getTime()-start.getTime());

    }

    private static void write(String result) throws FileNotFoundException
    {
//        PrintWriter fw = new PrintWriter("knight2.out");
        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(result);
        fw.close();
    }

    private static String BFSalgo(int cellx, int celly, int z) throws InterruptedException
    {
        int finx, finy;
        cell t = new cell(cellx, celly);
        if (z == 1) {
            finx = final1x;
            finy = final1y;
        } else {
            finx = final2x;
            finy = final2y;
        }

        boolean[][] used = new boolean[8][8]; // массив пометок
        int[][] lenght = new int[8][8]; // массив длин
        int[][] fromq = new int[8][8]; // массив откуда
        lenght[cellx][celly] = 0;
        cell[] queue = new cell[64]; // очередь
        int qH = 0; // голова очереди
        int qT = 0; // хвост

        used[cellx][celly] = true; // помечаем исходную клетку
        queue[qT++] = t; // помещаем ее в очередь
        cell nc;
        int newcellx, newcelly;
        int[] dx = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dy = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        while (qH < qT) { // пока очередь не пуста
            t = queue[qH++]; // извлекаем текущую cell

            for (int i = 0; i < 8; i++) {
                newcellx = t.getCellx() + dx[i];
                newcelly = t.getCelly() + dy[i];

                if (newcellx >= 0 && newcellx <= 7 && newcelly >= 0 && newcelly <= 7)
                    if (!used[newcellx][newcelly] && !mark[newcellx][newcelly]) { // если newcell не помечена и смежна с t
                        //а так же не используется другой нитью
                        used[newcellx][newcelly] = true;
                                   /* <обработка клетки newcell> */
                        nc = new cell(newcellx, newcelly);
                        if (lenght[newcellx][newcelly] > lenght[t.getCellx()][t.getCelly()] + 1 || lenght[newcellx][newcelly] == 0) { //если пустая или больше
                            lenght[newcellx][newcelly] = lenght[t.getCellx()][t.getCelly()] + 1;
                            fromq[newcellx][newcelly] = (qH - 1);// сохраняем откуда
                        }
                        if (newcellx == finx && newcelly == finy) {
                            qT = 0;
                            if (z == 1) {
                                mark[t.getCellx()][t.getCelly()] = true;
                                mark[cellx][celly] = false;
                            }
                            for (int j = 0; j < lenght[newcellx][newcelly]; j++) {
                                result.insert(0, z + " " + (char) (nc.getCellx() + 96 + 1) + "" + (nc.getCelly() + 1) + "\n");
                                nc = queue[fromq[nc.getCellx()][nc.getCelly()]];
                            }
                        }
                        queue[qT++] = nc; // и добавляем в очередь
                    } else if (qH > 1 && newcellx == finx && newcelly == finy) {
                        mark[newcellx][newcelly] = false;
                        i--;
                    }
            }
        }
        return null;
    }

        private static void read()
    {
        FileReader sc = null;
        try {
//         sc = new FileReader("knight2.in");
            sc = new FileReader("c:\\data1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            start1x = sc.read() - 96 - 1;
            start1y = sc.read() - 48 - 1;
            sc.read();
            sc.read();
            start2x = sc.read() - 96 - 1;
            start2y = sc.read() - 48 - 1;
            sc.read();
            sc.read();
            final1x = sc.read() - 96 - 1;
            final1y = sc.read() - 48 - 1;
            sc.read();
            sc.read();
            final2x = sc.read() - 96 - 1;
            final2y = sc.read() - 48 - 1;
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run()
    {
        int x, y, z;
        if (!h1working) {
            h1working = true;
            x = start1x;
            y = start1y;
            z = 1;
        } else {
            x = start2x;
            y = start2y;
            z = 2;
        }

        try {
            BFSalgo(x, y, z);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class cell
    {
        int cellx;
        int celly;

        public cell(int cellx, int celly)
        {
            this.cellx = cellx;
            this.celly = celly;
        }

        public int getCellx()
        {
            return cellx;
        }

        public int getCelly()
        {
            return celly;
        }
    }
}