package sort;

import metrics.Counters;
import java.util.Arrays;
import java.util.Comparator;


public class ClosestPair2D {
    public static class Point {
        public double x, y;
        public Point(double x, double y) { this.x = x; this.y = y; }
    }

    public static double closestPair(Point[] pts, Counters c) {
        Point[] sorted = Arrays.copyOf(pts, pts.length);
        Arrays.sort(sorted, Comparator.comparingDouble(p -> p.x));
        return rec(sorted, 0, pts.length - 1, c);
    }

    private static double rec(Point[] pts, int left, int right, Counters c) {
        c.enter();
        if (right - left <= 3) {
            double d = brute(pts, left, right, c);
            c.exit();
            return d;
        }
        int mid = (left + right) / 2;
        double d1 = rec(pts, left, mid, c);
        double d2 = rec(pts, mid + 1, right, c);
        double d = Math.min(d1, d2);


        Point[] strip = new Point[right - left + 1];
        int m = 0;
        double midX = pts[mid].x;
        for (int i = left; i <= right; i++) {
            if (Math.abs(pts[i].x - midX) < d) strip[m++] = pts[i];
        }
        Arrays.sort(strip, 0, m, Comparator.comparingDouble(p -> p.y));


        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m && j <= i + 7; j++) {
                c.incComparisons();
                d = Math.min(d, dist(strip[i], strip[j]));
            }
        }
        c.exit();
        return d;
    }

    private static double brute(Point[] pts, int l, int r, Counters c) {
        double d = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                c.incComparisons();
                d = Math.min(d, dist(pts[i], pts[j]));
            }
        }
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x - b.x, dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
