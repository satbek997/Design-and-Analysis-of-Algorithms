package cli;

import metrics.Counters;
import sort.MergeSort;
import sort.QuickSort;
import sort.DeterministicSelect;
import sort.ClosestPair2D;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class Main {

    public static void main(String[] args) throws Exception {
        Random rnd = new Random(12345);

        int[] sizes = {1000, 2000, 4000, 8000, 16000};
        int trials = 5;
        int warmup = 2;
        String outFile = "results.csv";

        runAllExperiments(sizes, trials, warmup, outFile, rnd);
    }

    private static void runAllExperiments(int[] sizes, int trials, int warmup, String outFile, Random rnd) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(outFile))) {

            w.write("algo,n,trial,timeNs,comparisons,swaps,allocations,maxDepth\n");

            for (int n : sizes) {
                System.out.println("=== n = " + n + " ===");


                System.out.println("Warmup MergeSort...");
                for (int i = 0; i < warmup; i++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    MergeSort.sort(a, new Counters());
                }
                System.out.println("Run MergeSort...");
                for (int t = 0; t < trials; t++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    Counters c = new Counters();
                    int[] input = Arrays.copyOf(a, a.length);
                    long start = System.nanoTime();
                    MergeSort.sort(input, c);
                    long took = System.nanoTime() - start;
                    writeCsvLine(w, "MergeSort", n, t, took, c);
                }


                System.out.println("Warmup QuickSort...");
                for (int i = 0; i < warmup; i++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    QuickSort.sort(a, new Counters());
                }
                System.out.println("Run QuickSort...");
                for (int t = 0; t < trials; t++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    Counters c = new Counters();
                    int[] input = Arrays.copyOf(a, a.length);
                    long start = System.nanoTime();
                    QuickSort.sort(input, c);
                    long took = System.nanoTime() - start;
                    writeCsvLine(w, "QuickSort", n, t, took, c);
                }


                System.out.println("Warmup DeterministicSelect...");
                for (int i = 0; i < warmup; i++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    int k = Math.max(0, a.length / 2);
                    DeterministicSelect.select(Arrays.copyOf(a, a.length), k, new Counters());
                }
                System.out.println("Run DeterministicSelect...");
                for (int t = 0; t < trials; t++) {
                    int[] a = randomIntArray(n, new Random(rnd.nextLong()));
                    int k = Math.abs(new Random(rnd.nextLong()).nextInt()) % n;
                    Counters c = new Counters();
                    int[] input = Arrays.copyOf(a, a.length);
                    long start = System.nanoTime();
                    int val = DeterministicSelect.select(input, k, c);
                    long took = System.nanoTime() - start;

                    writeCsvLine(w, "DeterministicSelect", n, t, took, c);
                }


                System.out.println("Warmup ClosestPair...");
                for (int i = 0; i < warmup; i++) {
                    ClosestPair2D.Point[] pts = randomPoints(n, new Random(rnd.nextLong()));
                    ClosestPair2D.closestPair(pts, new Counters());
                }
                System.out.println("Run ClosestPair...");
                for (int t = 0; t < trials; t++) {
                    ClosestPair2D.Point[] pts = randomPoints(n, new Random(rnd.nextLong()));
                    Counters c = new Counters();
                    long start = System.nanoTime();
                    double d = ClosestPair2D.closestPair(pts, c);
                    long took = System.nanoTime() - start;
                    writeCsvLine(w, "ClosestPair2D", n, t, took, c);
                }


                w.flush();
            }

            System.out.println("Эксперименты завершены. Результаты в " + outFile);
        }
    }

    private static void writeCsvLine(BufferedWriter w, String algo, int n, int trial, long timeNs, Counters c) throws IOException {

        String line = String.format("%s,%d,%d,%d,%d,%d,%d,%d\n",
                algo, n, trial, timeNs,
                c.comparisons, c.swaps, c.allocations, c.maxDepth);
        w.write(line);
    }

    private static int[] randomIntArray(int n, Random rnd) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = rnd.nextInt(1_000_000); // 0..999999
        }
        return a;
    }

    private static ClosestPair2D.Point[] randomPoints(int n, Random rnd) {
        ClosestPair2D.Point[] pts = new ClosestPair2D.Point[n];
        for (int i = 0; i < n; i++) {
            double x = rnd.nextDouble() * 10000.0;
            double y = rnd.nextDouble() * 10000.0;
            pts[i] = new ClosestPair2D.Point(x, y);
        }
        return pts;
    }
}
