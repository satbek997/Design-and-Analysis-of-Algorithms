package sort;

import metrics.Counters;
import java.util.Arrays;


public class DeterministicSelect {
    public static int select(int[] arr, int k, Counters c) {
        return selectRec(arr, 0, arr.length - 1, k, c);
    }

    private static int selectRec(int[] arr, int left, int right, int k, Counters c) {
        c.enter();
        if (left == right) { c.exit(); return arr[left]; }

        int pivotIndex = medianOfMedians(arr, left, right, c);
        int pos = partition(arr, left, right, pivotIndex, c);

        if (k == pos) { c.exit(); return arr[pos]; }
        else if (k < pos) { int res = selectRec(arr, left, pos - 1, k, c); c.exit(); return res; }
        else { int res = selectRec(arr, pos + 1, right, k, c); c.exit(); return res; }
    }

    private static int medianOfMedians(int[] arr, int left, int right, Counters c) {
        int n = right - left + 1;
        if (n <= 5) {
            Arrays.sort(arr, left, right + 1);
            return left + n / 2;
        }
        int numMedians = (int) Math.ceil(n / 5.0);
        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            Arrays.sort(arr, subLeft, subRight + 1);
            int median = subLeft + (subRight - subLeft) / 2;
            swap(arr, left + i, median, c);
        }
        return medianOfMedians(arr, left, left + numMedians - 1, c);
    }

    private static int partition(int[] arr, int left, int right, int pivotIndex, Counters c) {
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, right, c);
        int i = left;
        for (int j = left; j < right; j++) {
            c.incComparisons();
            if (arr[j] < pivot) swap(arr, i++, j, c);
        }
        swap(arr, i, right, c);
        return i;
    }

    private static void swap(int[] arr, int i, int j, Counters c) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t; c.incSwaps();
    }
}
