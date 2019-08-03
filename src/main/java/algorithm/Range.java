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