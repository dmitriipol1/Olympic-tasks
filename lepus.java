
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rabbit
{
    public static void main(String[] args) throws IOException
    {

        Scanner sc = new Scanner(new FileReader("lepus.in"));
        PrintWriter fw = new PrintWriter("lepus.out");
        int z = Integer.parseInt(sc.nextLine());
        String[] l = sc.nextLine().toLowerCase().split("");
        int[] intlist = new int[z];
        sc.close();

        for (int i = 0; i < l.length; i++) {
            switch (l[i]) {
                case ".":
                    l[i] = l[i].replaceAll(".", "0");
                    break;
                case "\"":
                    l[i] = l[i].replaceAll("\"", "1");
                    break;
                case "w":
                    l[i] = l[i].replaceAll("w", "-1");
                    break;
            }
            intlist[i] = Integer.valueOf(l[i]);
        }

        int[] sum = new int[z];
        sum[0] = 0;
        sum[1] = intlist[1];
        if (z >= 3)
            if (intlist[2] == -1)
                sum[2] = -1;
            else
                sum[2] = intlist[2] + sum[1];

        for (int i = 3; i < z; i++) {
            if (intlist[i] == -1)
                sum[i] = -1;
            else if (i < 5)
                if (Math.max(sum[i - 1], sum[i - 3]) != -1)
                    sum[i] = intlist[i] + Math.max(sum[i - 1], sum[i - 3]);
                else sum[i] = -1;
            else if (Math.max(sum[i - 5], Math.max(sum[i - 1], sum[i - 3])) != -1)
                sum[i] = intlist[i] + Math.max(sum[i - 5], Math.max(sum[i - 1], sum[i - 3]));
            else sum[i] = -1;
        }
        fw.print(sum[z - 1]);
        fw.close();
    }
}
close
