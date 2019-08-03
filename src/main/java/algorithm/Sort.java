package algorithm;

import java.util.PriorityQueue;

public class Sort {
    private static boolean less(Comparable u, Comparable v) {
        return u.compareTo(v) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean sorted(Comparable[] a) {
        for (int i = 0; i + 1 < a.length; i++) {
            if (less(a[i + 1], a[i])) {
                return false;
            }

        }
        return true;
    }

    public static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length);
    }

    private static void quickSort(Comparable[] a, int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);
            quickSort(a, low, pivot);
            quickSort(a, pivot, high);
        }
    }

    private static int partition(Comparable[] a, int low, int high) {
        Comparable pivotValue = a[high - 1];
        int pivot = low;
        for (int j = low; j < high - 1; j++) {
            if (less(a[j], pivotValue)) {
                swap(a, pivot, j);
                pivot++;
            }
        }
        swap(a, pivot, high - 1);
        return pivot;
    }
}
