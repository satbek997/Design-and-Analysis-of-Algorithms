package tests;

import metrics.Counters;
import org.junit.jupiter.api.Test;
import sort.ClosestPair2D;

import static org.junit.jupiter.api.Assertions.*;

class ClosestPair2DTest {

    @Test
    void testSimplePoints() {
        ClosestPair2D.Point[] pts = {
                new ClosestPair2D.Point(0, 0),
                new ClosestPair2D.Point(3, 4),
                new ClosestPair2D.Point(1, 1),
                new ClosestPair2D.Point(7, 7)
        };
        Counters c = new Counters();
        double d = ClosestPair2D.closestPair(pts, c);
        assertEquals(Math.sqrt(2), d, 1e-6);
    }

    @Test
    void testTwoPoints() {
        ClosestPair2D.Point[] pts = {
                new ClosestPair2D.Point(0, 0),
                new ClosestPair2D.Point(1, 0)
        };
        Counters c = new Counters();
        double d = ClosestPair2D.closestPair(pts, c);
        assertEquals(1.0, d, 1e-6);
    }

    @Test
    void testSamePoints() {
        ClosestPair2D.Point[] pts = {
                new ClosestPair2D.Point(5, 5),
                new ClosestPair2D.Point(5, 5),
                new ClosestPair2D.Point(1, 1)
        };
        Counters c = new Counters();
        double d = ClosestPair2D.closestPair(pts, c);
        assertEquals(0.0, d, 1e-6);
    }
}
