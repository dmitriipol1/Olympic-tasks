package com.javarush.test.test;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DeikstrMinPQ

{
    public static Comparator<nodemx> idComparator = new Comparator<nodemx>()
    {
        @Override
        public int compare(nodemx c1, nodemx c2)
        {
            return c1.getWeight() - c2.getWeight();
        }
    };
    private static int N;
    private static int S;
    private static int F;
    private static int INF = Integer.MAX_VALUE / 2;
    private static int[] dist;
    private static ArrayList<nodemx>[] listmxnodes;

    public static void main(String[] args) throws IOException
    {
//        Date start = new Date();
        read();
//        System.out.println(new Date().getTime() - start.getTime());
//        System.out.println("end reading");
        Deikstr();
    }

    private static void Deikstr() throws FileNotFoundException
    {
        boolean[] used = new boolean[N];
        Queue<nodemx> queue = new PriorityQueue<>(N * 2, idComparator);
        dist[S] = 0;
        queue.add(new nodemx(S, 0));
        nodemx min;

        while (queue.size() > 0) {
            min = queue.poll();
            if (min.index == F) {
                write(dist[F]);
                break;
            }
            for (nodemx node : listmxnodes[min.index]) {
                if (!used[node.index] && dist[node.index] > (min.weight + node.weight)) {
                    dist[node.index] = min.weight + node.weight;
                    queue.add(new nodemx(node.index, (min.weight + node.weight)));
                }
            }
            used[min.index] = true;
        }
        if (dist[F] == INF || dist[F] == 0) write(-1);

    }

    private static void read() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(new File("dijkstra.in")));
//        BufferedReader br = new BufferedReader(new FileReader(new File("c:\\data1.txt")));
        String[] s;
        StringBuilder sb = new StringBuilder();
        sb.append(br.readLine());
        s = sb.toString().split(" ");
        N = Integer.parseInt(s[0]);
        S = Integer.parseInt(s[1]) - 1;
        F = Integer.parseInt(s[2]) - 1;
        sb.delete(0, sb.length());

        dist = new int[N];
        listmxnodes = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            listmxnodes[i] = new ArrayList<>();
            dist[i] = INF;
            sb.append(br.readLine());
            s = sb.toString().split(" ");
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(s[j]) > 0)
                    listmxnodes[i].add(new nodemx(j, Integer.parseInt(s[j])));
            }
            sb.delete(0, sb.length());
        }
        br.close();
    }

    private static void write(int result) throws FileNotFoundException
    {
        PrintWriter fw = new PrintWriter("dijkstra.out");
//        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(result);
        fw.close();
    }

    private static class nodemx
    {
        int index;
        int weight;

        public nodemx(int index, int weight)
        {
            this.index = index;
            this.weight = weight;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            this.index = index;
        }

        public int getWeight()
        {
            return weight;
        }
    }
}

