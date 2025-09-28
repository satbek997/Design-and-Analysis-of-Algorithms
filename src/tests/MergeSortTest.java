package tests;

import metrics.Counters;
import org.junit.jupiter.api.Test;
import sort.MergeSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void testCorrectnessRandomArray() {
        int[] arr = {5, 2, 9, 1, 5, 6};
        int[] copy = Arrays.copyOf(arr, arr.length);

        Counters c = new Counters();
        MergeSort.sort(arr, c);

        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(arr, arr.length);

        Counters c = new Counters();
        MergeSort.sort(arr, c);

        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {42};
        Counters c = new Counters();
        MergeSort.sort(arr, c);
        assertArrayEquals(new int[]{42}, arr);
    }
}
