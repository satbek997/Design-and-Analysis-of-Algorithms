package tests;

import metrics.Counters;
import org.junit.jupiter.api.Test;
import sort.QuickSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testCorrectnessSmall() {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int[] copy = Arrays.copyOf(arr, arr.length);

        Counters c = new Counters();
        QuickSort.sort(arr, c);

        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random();
        for (int t = 0; t < 20; t++) {
            int[] arr = rnd.ints(50, -100, 100).toArray();
            int[] copy = Arrays.copyOf(arr, arr.length);

            Counters c = new Counters();
            QuickSort.sort(arr, c);

            Arrays.sort(copy);
            assertArrayEquals(copy, arr);
        }
    }

    @Test
    void testAllEqualElements() {
        int[] arr = {7, 7, 7, 7, 7};
        int[] copy = Arrays.copyOf(arr, arr.length);

        Counters c = new Counters();
        QuickSort.sort(arr, c);

        Arrays.sort(copy);
        assertArrayEquals(copy, arr);
    }
}
