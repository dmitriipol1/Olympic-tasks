import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class horse
{
    private static int finalx;
    private static int finaly;
    private static int startx;
    private static int starty;

    public static void main(String[] args) throws IOException
    {
        read();
        BFSalgo(startx, starty);
    }

    private static void write(String result) throws FileNotFoundException
    {
        PrintWriter fw = new PrintWriter("knight1.out");
//        PrintWriter fw = new PrintWriter("c:\\data2.txt");
        fw.print(result);
        fw.close();
    }

    private static void BFSalgo(int cellx, int celly) throws FileNotFoundException
    {
        cell t = new cell(cellx, celly);//, 0);

        boolean[][] used = new boolean[8][8]; // массив пометок
        int[][] lenght = new int[8][8]; // массив длин
        int[][] fromq = new int[8][8]; // массив откуда
        lenght[cellx][celly] = 0;
        cell[] queue = new cell[64]; // очередь
        int qH = 0; // голова очереди
        int qT = 0; // хвост
        int z;

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

                if (newcellx>=0&&newcellx<=7&&newcelly>=0&&newcelly<=7)
                    if (!used[newcellx][newcelly]) { // если newcell не помечена и смежна с t
                                   /* <обработка клетки newcell> */
                        nc = new cell(newcellx, newcelly);
                        if (lenght[newcellx][newcelly] > lenght[t.getCellx()][t.getCelly()] + 1 || lenght[newcellx][newcelly] == 0) { //если пустая или больше
                            lenght[newcellx][newcelly] = lenght[t.getCellx()][t.getCelly()] + 1;
                            fromq[newcellx][newcelly] = (qH - 1);// сохраняем откуда
                        }
                        if (newcellx == finalx && newcelly == finaly) {
                            qT = 0;
                            z = lenght[newcellx][newcelly];
                            {
                                String result = "";
                                for (int j = 0; j < z; j++) {

                                    result = (char) (nc.getCellx() + 96 + 1) + "" + (nc.getCelly() + 1) + "\n" + result;
                                    nc = queue[fromq[nc.getCellx()][nc.getCelly()]];
                                }
                                write((char) (startx + 96 + 1) + "" + (starty + 1) + "\n" + result.trim());
                            }
                            break;
                        }
                        used[newcellx][newcelly] = true; // помечаем ее
                        queue[qT++] = nc; // и добавляем в очередь
                    }

            }
        }
    }

    private static void read() throws IOException
    {
        FileReader sc = new FileReader("knight1.in");
//        FileReader sc = new FileReader("c:\\data1.txt");

        startx = sc.read() - 96 - 1;
        starty = sc.read() - 48 - 1;
        sc.read();
        sc.read();
        finalx = sc.read() - 96 - 1;
        finaly = sc.read() - 48 - 1;

        sc.close();
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
