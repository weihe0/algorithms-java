package algorithm;

import org.junit.Test;

import java.util.*;

public class Order {
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) {
            int[] C = A;
            A = B;
            B = C;
        }
        int m = A.length, n = B.length;
        int i, j;
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        do {
            i = (iMin + iMax) / 2;
            j = halfLen - i;
            if (i > 0 && A[i - 1] > B[j]) {
                iMax = i - 1;
            } else if (i < m && B[j - 1] > A[i]) {
                iMin = i + 1;
            } else {
                break;
            }
        } while (iMin <= iMax);
        int leftMax;
        if (i == 0) {
            leftMax = B[j - 1];
        } else if (j == 0) {
            leftMax = A[i - 1];
        } else {
            leftMax = A[i - 1] > B[j - 1] ? A[i - 1] : B[j - 1];
        }
        if ((m + n) % 2 == 1) {
            return leftMax;
        } else {
            int rightMin;
            if (i == m) {
                rightMin = B[j];
            } else if (j == n) {
                rightMin = A[i];
            } else {
                rightMin = A[i] < B[j] ? A[i] : B[j];
            }
            return (leftMax + rightMin) / 2.0;
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0){return new int[0];}
        int[] winMax=new int[nums.length-k+1];
        int s=0;
        MonoQueue mq=new MonoQueue(k);
        for(int j=0;j<k;j++){
            mq.push(nums[j]);
        }
        winMax[s++]=mq.max();
        for(int j=k;j<nums.length;j++){
            mq.push(nums[j]);
            mq.pop(nums[j-k]);
            winMax[s++]=mq.max();
        }
        return winMax;
    }

    @Test
    public void testOne(){
        maxSlidingWindow(new int[]{1,2},2);
    }
}

class SmallNumberAfterSelf {
    private ArrayList<Integer> smallerThan;
    private int[] nums;

    public List<Integer> countSmaller(int[] nums) {
        this.nums = nums;
        smallerThan = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            smallerThan.add(0);
        }
        int[] indices = new int[nums.length];
        int[] aux = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indices[i] = i;
        }
        for (int i = 0; i < nums.length; i++) {
            aux[i] = i;
        }
        mergeSort(aux, indices, 0, nums.length);
        return smallerThan;
    }

    private void mergeSort(int[] src, int[] dest, int low, int high) {
        if (high - low > 1) {
            int mid = (low + high) >>> 1;
            mergeSort(dest, src, low, mid);
            mergeSort(dest, src, mid, high);
            if (nums[src[mid - 1]] <= nums[src[mid]]) {
                System.arraycopy(src, low, dest, low, high - low);
            } else {
                int rightSmaller = 0;
                for (int k = low, p = low, q = mid; k < high; k++) {
                    if (q >= high || p < mid && nums[src[p]] <= nums[src[q]]) {
                        int index = src[p];
                        smallerThan.set(index, smallerThan.get(index) + rightSmaller);
                        dest[k] = src[p++];
                    } else {
                        dest[k] = src[q++];
                        rightSmaller++;
                    }
                }
            }
        }
    }

    public void wiggleSort(int[] nums) {

    }




}

class MonoQueue{
    private int a[],f,r;
    MonoQueue(int k){
        a=new int[k+1];
        f=r=0;
    }
    boolean isEmpty(){return f==r;}
    void push(int n){
        while(!isEmpty()&&getBack()<n){
            popBack();
        }
        pushBack(n);
    }
    void pop(int n){
        if(getFront()==n){
            popFront();
        }
    }
    int max(){return getFront();}
    private int getBack(){
        return r==0?a[a.length-1]:a[r-1];
    }
    private void pushBack(int n){
        a[r]=n;
        if(r==a.length-1){
            r=0;
        }else {
            r++;
        }
    }
    private int getFront(){
        return a[f];
    }
    private void popFront(){
        if(f==a.length-1){
            f=0;
        }else {
            f++;
        }
    }
    private void popBack(){
        if(r==0){
            r=a.length-1;
        }else {
            r--;
        }
    }

}

class MedianFinder {

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {

    }

    public double findMedian() {
        return 0.0;
    }
}

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> l=new ArrayList<>();
        int imin=Integer.MIN_VALUE;
        int jmin=Integer.MIN_VALUE;
        for(int i=0;i<nums.length-2&&nums[i]<=0;i++){
            if(nums[i]==imin){
                continue;
            }else {
                imin=nums[i];
            }
            for(int j=i+1;j<nums.length-1&&nums[i]+nums[j]<=0;j++){
                if(nums[j]==jmin){
                    continue;
                }
            }
        }
        return l;
    }

    public boolean increasingTriplet(int[] nums) {
        int ni=Integer.MAX_VALUE,nj=Integer.MAX_VALUE;
        for(int n:nums){
            if(n<ni){
                ni=n;
            }else if(n>ni && n<=nj){
                nj=n;
            }else if(n>nj){
                return true;
            }
        }
        return false;
    }
}

class LargestNumber implements Comparator<String>{
    public String largestNumber(int[] nums){
        String[] numStr=new String[nums.length];
        for(int i=0;i<nums.length;i++){
            numStr[i]=Integer.toString(nums[i]);
        }
        Arrays.sort(numStr,this);
        if(numStr[0].equals("0")){
            return "0";
        }
        StringBuilder sb=new StringBuilder();
        for(String is:numStr){
            sb.append(is);
        }
        return sb.toString();
    }

    @Override
    public int compare(String s, String t) {
        return (t+s).compareTo(s+t);
    }
}