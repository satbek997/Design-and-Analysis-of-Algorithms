package tests;

import metrics.Counters;
import org.junit.jupiter.api.Test;
import sort.DeterministicSelect;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DeterministicSelectTest {

    @Test
    void testSelectSmall() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        Counters c = new Counters();
        int result = DeterministicSelect.select(arr, k, c);

        assertEquals(copy[k], result);
    }

    @Test
    void testRandomArrays() {
        Random rnd = new Random();
        for (int t = 0; t < 30; t++) {
            int[] arr = rnd.ints(40, 0, 1000).toArray();
            int[] copy = Arrays.copyOf(arr, arr.length);
            Arrays.sort(copy);

            int k = rnd.nextInt(arr.length);

            Counters c = new Counters();
            int result = DeterministicSelect.select(arr, k, c);

            assertEquals(copy[k], result);
        }
    }
}
