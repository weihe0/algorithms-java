package algorithm;

import org.junit.Test;

import java.util.*;

public class DynamicProgramming {
    public int coinChange(int[] coins, int amount) {
        int[] nums = new int[amount + 1];
        nums[0] = 0;
        for (int a = 1; a <= amount; a++) {
            boolean changable = false;
            nums[a] = a;
            for (int c : coins) {
                if (a - c >= 0 && nums[a - c] >= 0) {
                    changable = true;
                    nums[a] = Integer.min(nums[a], 1 + nums[a - c]);
                }
            }
            if (!changable) {
                nums[a] = -1;
            }
        }
        return nums[amount];
    }

    public int maxProfit(int k, int[] prices) {
        if (k == 0) {
            return 0;
        } else if (k<=prices.length/2){
            int[] buy=new int[k];
            for(int j=0;j<k;j++){buy[j]=Integer.MIN_VALUE;}
            int[] sell=new int[k];
            for(int i=0;i<prices.length;i++){
                int p=prices[i];
                buy[0]=Math.max(buy[0],-p);
                sell[0]=Math.max(sell[0],p+buy[0]);
                for(int j=1;j<k;j++){
                    buy[j]=Math.max(buy[j],sell[j-1]-p);
                    sell[j]=Math.max(sell[j],buy[j]+p);
                }
            }
            return sell[k-1];
        }else {
            int profit=0;
            for(int i=0;i+1<prices.length;i++){
                if(prices[i+1]>prices[i]){
                    profit+=prices[i+1]-prices[i];
                }
            }
            return profit;
        }
    }
    public int maxProduct(int[] nums) {
        int imin=nums[0],imax=nums[0],max=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]<0){
                int t=imin;
                imin=imax;
                imax=t;
            }
            imax=Integer.max(imax*nums[i],nums[i]);
            imin=Integer.min(imin*nums[i],nums[i]);
            max=Integer.max(max,imax);
        }
        return max;
    }

}
