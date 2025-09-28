package sort;

import metrics.Counters;
import java.util.Random;


public class QuickSort {
    private static final int CUTOFF = 16;
    private static final Random rnd = new Random();

    public static void sort(int[] arr, Counters c) {
        quickSort(arr, 0, arr.length - 1, c);
    }

    private static void quickSort(int[] arr, int left, int right, Counters c) {
        c.enter();
        while (left < right) {
            if (right - left <= CUTOFF) {
                insertionSort(arr, left, right, c);
                c.exit();
                return;
            }
            int pivotIndex = left + rnd.nextInt(right - left + 1);
            int p = partition(arr, left, right, pivotIndex, c);


            if (p - left < right - p) {
                quickSort(arr, left, p - 1, c);
                left = p + 1;
            } else {
                quickSort(arr, p + 1, right, c);
                right = p - 1;
            }
        }
        c.exit();
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Counters c) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right, c);
        int i = left;
        for (int j = left; j < right; j++) {
            c.incComparisons();
            if (arr[j] < pivot) {
                swap(arr, i, j, c);
                i++;
            }
        }
        swap(arr, i, right, c);
        return i;
    }

    private static void swap(int[] arr, int i, int j, Counters c) {
        int tmp = arr[i]; arr[i] = arr[j]; arr[j] = tmp;
        c.incSwaps();
    }

    private static void insertionSort(int[] arr, int left, int right, Counters c) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                c.incComparisons();
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
