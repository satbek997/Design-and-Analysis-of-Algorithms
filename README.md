# Assignment 1 — Divide & Conquer Algorithms

## Architecture
The project implements four divide-and-conquer algorithms:
- **MergeSort**: classic divide into halves, linear merge, buffer reuse, and small-n cutoff (switch to insertion sort).
- **QuickSort**: randomized pivot; always recurse on the smaller partition to keep recursion depth `O(log n)`.
- **Deterministic Select (Median-of-Medians)**: group elements by 5, choose median of medians as pivot, recurse only into the required side.
- **Closest Pair of Points (2D)**: sort points by X, recursive split, check in a vertical strip sorted by Y (7–8 neighbors).

Recursion depth and operation counts are tracked using a `Metrics` class, which measures:
- execution time,
- number of comparisons, swaps, allocations,
- maximum recursion depth.

---

## Recurrence relations and analysis

### MergeSort
Recurrence:  
`T(n) = 2T(n/2) + Θ(n)`  
Master theorem, Case 2 → `T(n) = Θ(n log n)`.

### QuickSort
Average case:  
`T(n) = T(αn) + T((1-α)n) + Θ(n)` with randomized pivot.  
Akra–Bazzi intuition → `T(n) = Θ(n log n)`.  
Worst case: `Θ(n^2)`, but rare.

### Deterministic Select (Median-of-Medians)
Recurrence:  
`T(n) = T(n/5) + T(7n/10) + Θ(n)`  
Akra–Bazzi → `T(n) = Θ(n)`  
Guaranteed linear time.

### Closest Pair of Points (2D)
Recurrence:  
`T(n) = 2T(n/2) + Θ(n)` (sorting + strip check).  
Master theorem, Case 2 → `T(n) = Θ(n log n)`.

---

## Experimental results

### Running time (time vs n)
The plot shows MergeSort and QuickSort growing as `n log n`,  
Select close to linear, and Closest Pair also `n log n` but with larger constants.
<img width="1697" height="1102" alt="график11" src="https://github.com/user-attachments/assets/045465ae-eeb0-4edc-9ab5-2386e0aa8243" />





---

### Recursion depth (depth vs n)
MergeSort depth grows logarithmically, QuickSort also stays `O(log n)`  
with some variation due to random pivots.

<img width="1687" height="1102" alt="график22" src="https://github.com/user-attachments/assets/a62e824e-a4b5-4ad5-a9ee-b631a5a85131" />



---

## Discussion
- **Constant factors** differ due to cache effects, JVM optimizations, and garbage collection.  
- **QuickSort** often outperforms MergeSort in practice despite both being `Θ(n log n)`.  
- **Deterministic Select** is slower than randomized selection in practice, but guarantees linear time.  
- **Closest Pair** is more expensive in practice due to sorting and strip management, though still `Θ(n log n)`.

---

## Conclusion
Theoretical bounds (Master theorem, Akra–Bazzi) match experimental results:  
- Sorting algorithms run in `Θ(n log n)`.  
- Deterministic Select is linear.  
- Closest Pair is `Θ(n log n)` with larger constants.  

Differences come mainly from constant factors and JVM runtime behavior.
