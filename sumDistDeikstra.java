import java.io.*;
import java.util.*;

public class sumdistDeik
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
    private static int[][] linkMatrix;
    private static int[] dist;
    private static ArrayList<nodemx>[] listmxnodes;
    private static int INF = Integer.MAX_VALUE / 2;


    public static void main(String[] args) throws IOException
    {
        read();
        int res = 0;
        for (int i = 0; i < N; i++) {
            res += Deikstr(i);
        }
        PrintWriter fw = new PrintWriter("sumdist.out");
//        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(res);
        fw.close();
    }

    private static int Deikstr(int S) throws FileNotFoundException
    {
        Arrays.fill(dist, INF);
        boolean[] used = new boolean[N];
        Queue<nodemx> queue = new PriorityQueue<>(N * 2, idComparator);
        dist[S] = 0;
        queue.add(new nodemx(S, 0));
        nodemx min;
        int count = 0;
        while (queue.size() > 0) {
            min = queue.poll();
            for (nodemx node : listmxnodes[min.index]) {
                if (!used[node.index] && dist[node.index] > (min.weight + node.weight)) {
                    dist[node.index] = min.weight + node.weight;
                    queue.add(new nodemx(node.index, (min.weight + node.weight)));
                }
            }
            used[min.index] = true;
        }
        for (int i = 0; i < S; i++) {
            if (dist[i] != INF) count += dist[i];
        }
        return count;
    }
    

    private static void read() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(new File("sumdist.in")));
//        BufferedReader br = new BufferedReader(new FileReader(new File("c:\\data1.txt")));

        String[] s;
        StringBuilder sb = new StringBuilder();
        sb.append(br.readLine());
        s = sb.toString().split(" ");
        N = Integer.parseInt(s[0]);
        int nl = Integer.parseInt(s[1]);
        sb.delete(0, sb.length());

        listmxnodes = new ArrayList[N];
        dist = new int[N];

        for (int i = 0; i < N; i++) {

            sb.append(br.readLine()).append(" ");
            listmxnodes[i] = new ArrayList<>();

        }
        for (int i = 0; i < nl - N; i++) {
            sb.append(br.readLine()).append(" ");
        }

        br.close();
        s = sb.toString().split(" ");
        for (int i = 0; i < nl * 2; i += 2) {

            listmxnodes[Integer.parseInt(s[i]) - 1].add(new nodemx(Integer.parseInt(s[i + 1]) - 1, 1));
            listmxnodes[Integer.parseInt(s[i + 1]) - 1].add(new nodemx(Integer.parseInt(s[i]) - 1, 1));

        }
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
