/**
 * Created by 15 on 28.01.2018.
 */




import java.io.*;
import java.util.*;

/** Поток-вычислитель группы ячеек матрицы. */
class MultiplierThread extends Thread
{
    /** Первая (левая) матрица. */
    private final int[][] firstMatrix;
    /** Вторая (правая) матрица. */
    private final int[][] secondMatrix;
    /** Результирующая матрица. */
    private final int[][] resultMatrix;
    /** Начальный индекс. */
    private final int firstIndex;
    /** Конечный индекс. */
    private final int lastIndex;
    /** Число членов суммы при вычислении значения ячейки. */
    private final int sumLength;

    private final int p;
    private final int a;

    /**
     * @param firstMatrix  Первая (левая) матрица.
     * @param secondMatrix Вторая (правая) матрица.
     * @param resultMatrix Результирующая матрица.
     * @param firstIndex   Начальный индекс (ячейка с этим индексом вычисляется).
     * @param lastIndex    Конечный индекс (ячейка с этим индексом не вычисляется).
     */
    public MultiplierThread(final int[][] firstMatrix,
                            final int[][] secondMatrix,
                            final int[][] resultMatrix,
                            final int firstIndex,
                            final int lastIndex,
                            final int p,
                            final int a)
    {
        this.firstMatrix  = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resultMatrix = resultMatrix;
        this.firstIndex   = firstIndex;
        this.lastIndex    = lastIndex;
        this.p = p;
        this.a = a;
        sumLength = secondMatrix.length;
    }

    /**Вычисление значения в одной ячейке.
     *
     * @param row Номер строки ячейки.
     * @param col Номер столбца ячейки.
     */
    private void calcValue(final int row, final int col, final int p, final int a)
    {
        int sum = 0;
        for (int i = 0; i < sumLength; ++i)
            sum += firstMatrix[a-1][i] * secondMatrix[i][col];
        if (sum >= p) {
            sum = sum % p;
        }
        resultMatrix[row][col] = sum;
    }

    /** Рабочая функция потока. */
    @Override
    public void run()
    {


        final int colCount = secondMatrix[0].length;  // Число столбцов результирующей матрицы.
        for (int index = firstIndex; index < lastIndex; ++index)
            calcValue(a-1, index % colCount, p, a);


    }
}

public class Task_557 {


    private static int readInt(InputStream in) throws IOException {
        int ret = 0;
        boolean dig = false;

        for (int c = 0; (c = in.read()) != -1; ) {
            if (c >= '0' && c <= '9') {
                dig = true;
                ret = ret * 10 + c - '0';
            } else if (dig) break;
        }

        return ret;
    }

    public static void main(String[] argv) throws IOException {
        PrintWriter pw;
//        Scanner sc;
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("input.txt"));
//        sc = new Scanner(new File("F:/S/input.txt"));

//        int m = sc.nextInt(), n = sc.nextInt();
//        int a = sc.nextInt(), b = sc.nextInt();
//        int p = sc.nextInt();

        int m =readInt(bis);
        int n = readInt(bis);
        int a = readInt(bis), b = readInt(bis);
        int p = readInt(bis);
        int[][] matrix = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                int q = readInt(bis);
                if (j == a - 1) {
                    matrix[j][k] = q;
                }
            }
        }

        if (m > 1) {
            int[][] matrix2 = new int[n][n];
            if (m > 2) {
                for (int q = 1; q < m ; q++) {

                    for (int j = 0; j < n; j++) {
                        for (int k = 0; k < n; k++) {
                            matrix2[j][k] = readInt(bis);
                        }
                    }
                    matrix = multiplyMatrixMT(matrix, matrix2, Runtime.getRuntime().availableProcessors(), p, a);

                }
            }
        }
        pw = new PrintWriter(new File("output.txt"));

        pw.println(matrix[a - 1][b - 1]);
        pw.close();

    }





    /** Многопоточное умножение матриц.
     *
     * @param firstMatrix  Первая (левая) матрица.
     * @param secondMatrix Вторая (правая) матрица.
     * @param threadCount Число потоков.
     * @return Результирующая матрица.
     */
    private static int[][] multiplyMatrixMT (final int[][] firstMatrix,
                                             final int[][] secondMatrix,
                                             int threadCount, int p, int a)
    {
        assert threadCount > 0;

        final int rowCount = firstMatrix.length;             // Число строк результирующей матрицы.
        final int colCount = secondMatrix[0].length;         // Число столбцов результирующей матрицы.
        final int[][] result = new int[rowCount][colCount];  // Результирующая матрица.

        final int cellsForThread = (rowCount * colCount) / threadCount;  // Число вычисляемых ячеек на поток.
        int firstIndex = a-1;  // Индекс первой вычисляемой ячейки.
        final MultiplierThread[] multiplierThreads = new MultiplierThread[threadCount];  // Массив потоков.

        // Создание и запуск потоков.
        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int lastIndex = firstIndex +1;  // Индекс последней вычисляемой ячейки.
            if (threadIndex == 0) {
                /* Один из потоков должен будет вычислить не только свой блок ячеек,
                   но и остаток, если число ячеек не делится нацело на число потоков. */
                lastIndex = rowCount * colCount;
            }
            multiplierThreads[threadIndex] = new MultiplierThread(firstMatrix, secondMatrix, result, firstIndex, lastIndex, p, a);
            multiplierThreads[threadIndex].start();
            firstIndex = lastIndex;
        }

        // Ожидание завершения потоков.
        try {
            for (final MultiplierThread multiplierThread : multiplierThreads)
                multiplierThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }



}

