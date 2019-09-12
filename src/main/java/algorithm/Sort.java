package algorithm;

import org.junit.Test;

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

    @Test
    public void testKth(){
        KthLargest k=new KthLargest();
        k.findKthLargest(new int[]{3,2,1,5,6,4},2);
    }
}

class KthLargest{
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums,0,nums.length-1,k-1);
    }

    private int findKthLargest(int[] a,int left,int right,int k){
        if(left==right){
            return a[left];
        }
        int p=left;
        for(int i=left;i<right;i++){
            if(a[i]>a[right]){
                swap(a,p,i);
                p++;
            }
        }
        swap(a,p,right);
        if(k<p){
            return findKthLargest(a,left,p-1,k);
        }else if(k==p){
            return a[p];
        }else {
            return findKthLargest(a,p+1,right,k);
        }
    }

    private void swap(int[] a,int i,int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }
}
