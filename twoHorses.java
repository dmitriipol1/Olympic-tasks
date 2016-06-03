package com.javarush.test.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class twoHorses
{

    private static int final1x;
    private static int final1y;
    private static int start1x;
    private static int start1y;
    private static int start2x;
    private static int start2y;
    private static int final2x;
    private static int final2y;

    public static void main(String[] args) throws IOException
    {
        read();
        BFSalgo();
    }

    private static void write(String result) throws FileNotFoundException
    {
//        PrintWriter fw = new PrintWriter("knight1.out");
        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(result);
        fw.close();
    }

    private static void BFSalgo() throws FileNotFoundException
    {
        cell t1 = new cell(start1x, start1y);//, 0);
        cell t2 = new cell(start2x, start2y);//, 0);

        boolean[][] used = new boolean[8][8]; // массив пометок
        int[][] lenght = new int[8][8]; // массив длин
        int[][] fromq = new int[8][8]; // массив откуда
        lenght[start1x][start1y] = 0;
        lenght[start2x][start2y] = 0;
        cell[] queue1 = new cell[64]; // очередь
        cell[] queue2 = new cell[64]; // очередь
        int qH = 0; // голова очереди
        int qT = 0; // хвост
        int z;

        used[start1x][start1y] = true; // помечаем исходную клетку
        used[start2x][start2y] = true; // помечаем исходную клетку
        queue1[qT++] = t1; // помещаем ее в очередь
//        queue2[qT++] = t2; // помещаем ее в очередь
        cell nc1, nc2;
        int newcellx1, newcelly1, newcellx2, newcelly2;
        int[] dx = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dy = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        while (qH < qT) { // пока очередь не пуста
            t1 = queue1[qH++]; // извлекаем текущую cell
//            t2 = queue2[qH++]; // извлекаем текущую cell

            for (int i = 0; i < 8; i++) {
                newcellx1 = t1.getCellx() + dx[i];
                newcelly1 = t1.getCelly() + dy[i];
                newcellx2 = t1.getCellx() + dx[i];
                newcelly2 = t1.getCelly() + dy[i];

                if (newcellx1 >= 0 && newcellx1 <= 7 && newcelly1 >= 0 && newcelly1 <= 7 && newcellx2 >= 0 && newcellx2 <= 7 && newcelly2 >= 0 && newcelly2 <= 7)
                    if (!used[newcellx1][newcelly1]) { // если newcell не помечена и смежна с t
                                   /* <обработка клетки newcell> */
                        used[newcellx1][newcelly1] = true;
                        nc1 = new cell(newcellx1, newcelly1);
                        if (lenght[newcellx1][newcelly1] > lenght[t1.getCellx()][t1.getCelly()] + 1 || lenght[newcellx1][newcelly1] == 0) { //если пустая или больше
                            lenght[newcellx1][newcelly1] = lenght[t1.getCellx()][t1.getCelly()] + 1;
                            fromq[newcellx1][newcelly1] = (qH - 1);// сохраняем откуда
                        }
                        if (!used[newcellx2][newcelly2]) {
                            nc2 = new cell(newcellx2, newcelly2);
                            if (lenght[newcellx2][newcelly2] > lenght[t2.getCellx()][t2.getCelly()] + 1 || lenght[newcellx2][newcelly2] == 0) { //если пустая или больше
                                lenght[newcellx2][newcelly2] = lenght[t2.getCellx()][t2.getCelly()] + 1;
                                fromq[newcellx2][newcelly2] = (qH - 1);// сохраняем откуда
                            }
                        }
                        if (newcellx1 == final1x && newcelly1 == final1y) {
                            qT = 0;
                            z = lenght[newcellx1][newcelly1];
                            {
                                String result = "";
                                for (int j = 0; j < z; j++) {
                                    System.out.println("1 " + (char) (nc1.getCellx() + 96 + 1) + "" + (nc1.getCelly() + 1) + "\n");
//                                    System.out.println("2 " + (char) (nc2.getCellx() + 96 + 1) + "" + (nc2.getCelly() + 1) + "\n");
//                                    result = (char) (nc1.getCellx() + 96 + 1) + "" + (nc1.getCelly() + 1) + "\n" + result;
//                                    nc1 = queue1[fromq[nc1.getCellx()][nc1.getCelly()]];
                                }
                                write((char) (start1x + 96 + 1) + "" + (start1y + 1) + "\n" + result.trim());
                            }
                            break;
                        }
                        used[newcellx1][newcelly1] = true; // помечаем ее
                        used[t1.getCellx()][t1.getCelly()] = false; // помечаем ее
                        queue1[qT++] = nc1; // и добавляем в очередь

//                        used[newcellx2][newcelly2] = true; // помечаем ее
//                        used[t2.getCellx()][t2.getCelly()] = false; // помечаем ее
//                        queue2[qT++] = nc2; // и добавляем в очередь
                    }
            }
        }
    }

    private static void read() throws IOException
    {
//        FileReader sc = new FileReader("knight1.in");
        FileReader sc = new FileReader("c:\\data1.txt");
        try {
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