package sort;

import metrics.Counters;


public class MergeSort {
    private static final int CUTOFF = 16; 

    public static void sort(int[] arr, Counters c) {
        int[] buffer = new int[arr.length];
        c.incAllocations();
        mergeSort(arr, buffer, 0, arr.length - 1, c);
    }

    private static void mergeSort(int[] arr, int[] buf, int left, int right, Counters c) {
        c.enter();
        if (right - left <= CUTOFF) {
            insertionSort(arr, left, right, c);
            c.exit();
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, buf, left, mid, c);
        mergeSort(arr, buf, mid + 1, right, c);
        merge(arr, buf, left, mid, right, c);
        c.exit();
    }

    private static void merge(int[] arr, int[] buf, int left, int mid, int right, Counters c) {
        for (int i = left; i <= right; i++) buf[i] = arr[i];
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            c.incComparisons();
            if (buf[i] <= buf[j]) arr[k++] = buf[i++];
            else arr[k++] = buf[j++];
        }
        while (i <= mid) arr[k++] = buf[i++];
        while (j <= right) arr[k++] = buf[j++];
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
