package metrics;


public class Counters {
    public long comparisons = 0;
    public long swaps = 0;
    public long allocations = 0;
    public int maxDepth = 0;
    private int curDepth = 0;

    public void enter() { 
        curDepth++;
        if (curDepth > maxDepth) maxDepth = curDepth;
    }

    public void exit() { 
        curDepth--;
    }

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incAllocations() { allocations++; }

    public void reset() {
        comparisons = swaps = allocations = 0;
        curDepth = maxDepth = 0;
    }
}
