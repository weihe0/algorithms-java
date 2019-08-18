package algorithm;

import java.util.*;

public class Range {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        long[] S = new long[n + 1];
        S[0] = 0;
        //S[i] is the sum of the first i element(s) of A, i.e., A[0..i-1];
        //Sum of A[i..j-1] is S[j]-S[i];
        for (int i = 1; i <= n; i++) {
            S[i] += S[i - 1] + A[i - 1];
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.addLast(0);
        int ans = n + 1;
        for (int j = 1; j <= n; j++) {
            while (!q.isEmpty() && S[q.getFirst()] + K <= S[j]) {
                if (j - q.getFirst() < ans) {
                    ans = j - q.getFirst();
                }
                q.removeFirst();
            }
            while (!q.isEmpty() && S[q.getLast()] >= S[j]) {
                q.removeLast();
            }
            q.addLast(j);
        }
        if (ans == n + 1) {
            return -1;
        } else {
            return ans;
        }
    }

    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                int j = nums[i] - 1;
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        int first;
        for (first = 1; first <= nums.length; first++) {
            if (nums[first - 1] != first) {
                return first;
            }
        }
        return first;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //dp[i] means the answer for subarray [0..i] of length i
        int[] dp = new int[nums.length];
        // the answer to single subarray is 1
        dp[0] = 1;
        int longest = 1;
        // compute dp[1] to dp[nums.length] consequently
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            // update dp[i] by checking the sub-arrays [0..j] with j<i
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = dp[j] + 1 > dp[i] ? dp[j] + 1 : dp[i];
                }
            }
            // after computing dp[i], update the longest subsequence ever seen
            if (dp[i] > longest) {
                longest = dp[i];
            }
        }
        return longest;
    }

    public int lengthOfLISBinary(int[] nums) {
        int[] top = new int[nums.length];
        int piles = 0;
        for (int poker : nums) {
            int lower = 0, upper = piles;
            while (lower < upper) {
                int mid = (lower + upper) / 2;
                if (poker < top[mid]) {
                    upper = mid;
                } else if (poker == top[mid]) {
                    upper = mid;
                } else { //poker > midVal
                    lower = mid + 1;
                }
            }
            if (lower == piles) {
                piles++;
            }
            top[lower] = poker;
        }
        return piles;
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int i = 0, j = 0, n = 0;
        while (j < A.length) {
            if(count.size()<=K){
                if(count.size()==K){
                    n++;
                }
                int c=count.getOrDefault(A[j],0);
                count.put(A[j],c+1);
                j++;
            }else{
                int c=count.get(A[i]);
                c--;
                if(c==0){
                    count.remove(A[i]);
                }else {
                    count.remove(A[i],c);
                }
                i++;
            }
        }
        return n;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] r={searchLow(nums,target),searchHigh(nums,target)};
        return r;
    }
    private int searchLow(int[] nums,int target){
        int low=0,high=nums.length;
        while(low<high){
            int mid=(low+high)>>>1;
            if(target<=nums[mid]){
                high=mid;
            }else {
                low=mid+1;
            }
        }
        if(low<nums.length&&nums[low]==target){
            return low;
        }else {
            return -1;
        }
    }
    private int searchHigh(int[] nums,int target){
        int low=0,high=nums.length;
        while(low<high){
            int mid=(low+high)>>>1;
            if(target<nums[mid]){
                high=mid;
            }else {
                low=mid+1;
            }
        }
        if(high>0&&high<=nums.length&&nums[high-1]==target){
            return high-1;
        }else {
            return -1;
        }
    }

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        for(int n:nums){
            set.add(n);
        }
        int longest=0;
        for(int n:nums){
            if(!set.contains(n-1)){
                int p;
                for(p=n;set.contains(p);p++);
                if(p-n>longest){longest=p-n;}
            }
        }
        return longest;
    }

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder intPart=new StringBuilder();
        if((numerator>0&&denominator<0)||(numerator<0&&denominator>0)){
            intPart.append('-');
        }
        long n=numerator>0?(long)numerator:-(long)numerator;
        long d=denominator>0?(long)denominator:-(long)denominator;
        long q=n/d; //quotient
        intPart.append(q);
        long r=n%d; //remaining
        if(r==0){
            return intPart.toString();
        }
        char[] digit="0123456789".toCharArray();
        intPart.append('.');
        StringBuilder dec=new StringBuilder();
        int i=0;
        HashMap<Long,Integer> map=new HashMap<>();
        while(r>0&&!map.containsKey(r)){
            map.put(r,i++);
            r*=10;
            dec.append(digit[(int)(r/d)]);
            r%=d;
        }
        if(r>0){
            int start=map.get(r);
            return intPart+dec.substring(0,start)
                    +"("+dec.substring(start)+")";
        }else {
            return intPart.toString()+dec;
        }
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff=new int[n];
        for(int[] b:bookings){
            int l=b[0]-1,r=b[1]-1,s=b[2];
            diff[l]+=s;
            if(r+1<n){
                diff[r+1]-=s;
            }
        }
        for(int i=1;i<n;i++){
            diff[i]+=diff[i-1];
        }
        return diff;
    }

    public int largestRectangleArea(int[] heights) {
        int len=heights.length;
        int[] s=new int[len];
        int top=0;
        int maxarea=0;
        int i=0;
        while(i<len){
            if(top==0||heights[i]>=heights[s[top-1]]){
                s[top++]=i++;
            }else {
                int rec=s[--top];
                int left=top==0?-1:s[top-1];
                int area=(i-left-1)*heights[rec];
                if(area>maxarea){
                    maxarea=area;
                }
            }
        }
        while(top>0){
            int rec=s[--top];
            int left=top==0?-1:s[top-1];
            int area=(i-left-1)*heights[rec];
            if(area>maxarea){
                maxarea=area;
            }
        }
        return maxarea;
    }

    public int trap(int[] height) {
        int left=0,leftMax=0,right=height.length-1,rightMax=0;
        int rain=0;
        while(left<right){
            if(height[left]<height[right]){
                if(height[left]>=leftMax){
                    leftMax=height[left];
                }else {
                    rain+=leftMax-height[left];
                }
                left++;
            }else {
                if(height[right]>=rightMax){
                    rightMax=height[right];
                }else {
                    rain+=rightMax-height[right];
                }
                right--;
            }
        }
        return rain;
    }
}

class BinaryIndexedTree {
    int[] a;

    BinaryIndexedTree(int[] a) {
        this.a = a;
        for (int sz = 2; sz < a.length; sz *= 2) {
            for (int i = sz; i < a.length; i += sz) {
                a[i] += a[i - sz / 2];
                System.out.printf("a[%d]=a[%d]+a[%d]\n", i, i - sz / 2, i);
            }
        }
    }

    int partialSum(int k) {
        int s = 0;
        System.out.printf("S(%d)=", k);
        while (k > 0) {
            s += a[k];
            System.out.printf("+a[%d]", k);
            k -= lowbit(k);
        }
        System.out.println();
        return s;
    }

    int lowbit(int k) {
        return k & (-k);
    }
}

class Skyline{
    public List<List<Integer>> getSkyline(int[][] buildings) {
        return getSkyline(buildings,0,buildings.length);
    }
    private LinkedList<List<Integer>> getSkyline(int[][] buildings,int low,int high) {
        if(high-low==0){
            return new LinkedList<>();
        }else if(high-low==1){
            int x1=buildings[low][0],x2=buildings[low][1],y=buildings[low][2];
            return new LinkedList<>
                    (Arrays.asList(Arrays.asList(x1,y),Arrays.asList(x2,0)));
        }else {
            int mid=(low+high)>>>1;
            LinkedList<List<Integer>> l,r;
            l=getSkyline(buildings,low,mid);
            r=getSkyline(buildings,mid,high);
            return merge(l,r);
        }
    }
    private LinkedList<List<Integer>>
        merge(LinkedList<List<Integer>> l,LinkedList<List<Integer>> r){
        int ly=0,ry=0,x=0,y=0;
        LinkedList<List<Integer>> m=new LinkedList<>();
        while(!l.isEmpty()||!r.isEmpty()){
            if(l.isEmpty()){
                List<Integer> p=r.removeFirst();
                x=p.get(0);
                ry=p.get(1);
                if(y!=ry){
                    y=ry;
                    addPoint(m,x,y);
                }
            }else if(r.isEmpty()){
                List<Integer> p=l.removeFirst();
                x=p.get(0);
                ly=p.get(1);
                if(y!=ly){
                    y=ly;
                    addPoint(m,x,y);
                }
            }else {
                List<Integer> lp=l.getFirst();
                List<Integer> rp=r.getFirst();
                if(lp.get(0)<rp.get(0)){
                    x=lp.get(0);
                    ly=lp.get(1);
                    l.removeFirst();
                }else {
                    x=rp.get(0);
                    ry=rp.get(1);
                    r.removeFirst();
                }
                int ymax=Integer.max(ly,ry);
                if(y!=ymax){
                    y=ymax;
                    addPoint(m,x,y);
                }
            }
        }
        return m;
    }
    private void addPoint(LinkedList<List<Integer>> m,int x,int y){
        if(!m.isEmpty()&&m.getLast().get(0)==x){
            m.getLast().set(1,y);
        }else {
            m.addLast(Arrays.asList(x,y));
        }
    }
}