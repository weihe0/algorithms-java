package algorithm;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class Combinational {
    public int knightDialer(int N) {
        long[][] numsWindow=new long[2][10];
        for(int i=0;i<10;i++){
            numsWindow[0][i]=1;
        }
        long mod=(int)(1e9+7);
        long[] curr=numsWindow[0],next=numsWindow[1];
        for(int hop=0;hop<N-1;hop++){
            next[0]=(curr[4]+curr[6])%mod;
            next[1]=(curr[6]+curr[8])%mod;
            next[2]=(curr[9]+curr[7])%mod;
            next[3]=(curr[8]+curr[4])%mod;
            next[4]=(curr[3]+curr[9]+curr[0])%mod;
            next[5]=0;
            next[6]=(curr[0]+curr[7]+curr[1])%mod;
            next[7]=(curr[2]+curr[6])%mod;
            next[8]=(curr[1]+curr[3])%mod;
            next[9]=(curr[4]+curr[2])%mod;
            long[] prev=curr;
            curr=next;
            next=prev;
        }
        long sum=0;
        for(long n:curr){
            sum+=n;
            sum%=mod;
        }
        return (int)sum;
    }

    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        int[] c=new int[target+1];
        c[0]=1;
        for(int sum=1;sum<=target;sum++){
            for(int i=0;i<nums.length&&nums[i]<=sum;i++){
                c[sum]+=c[sum-nums[i]];
            }
        }
        return c[target];
    }

    @Test
    public void a(){
        Permutation p=new Permutation();
        int a[]={0,0,1,2};
        p.permuteUnique(a);
    }
}

class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int[][] g=new int[target+1][];
        g[0]=new int[]{0};
        LinkedList<Integer> stepList=new LinkedList<>();
        for(int sum=1;sum<=target;sum++){
            for(int i=0;i<candidates.length&&candidates[i]<=sum;i++){
                int[] pSteps=g[sum-candidates[i]];
                if(pSteps!=null){
                    stepList.addLast(candidates[i]);
                }
            }
            if(!stepList.isEmpty()){
                g[sum]=new int[stepList.size()];
                int i=0;
                while(!stepList.isEmpty()){
                    g[sum][i++]=stepList.removeFirst();
                }
            }
        }
        if(g[target]!=null){
            dfs(g, target, target);
            return c;
        }else {
            return new LinkedList<>();
        }
    }

    private LinkedList<Integer> s=new LinkedList<>();
    private LinkedList<List<Integer>> c=new LinkedList<>();

    private void dfs(int[][] g, int target, int maxStep){
        if(target==0){
            c.addLast(new LinkedList<>(s));
            return;
        }
        int[] steps=g[target];
        for(int i=0;i<steps.length&&steps[i]<=maxStep;i++){
            s.push(steps[i]);
            dfs(g,target-steps[i],steps[i]);
            s.pop();
        }
    }
}

class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int[][] g=new int[target+1][];
        g[0]=new int[]{0};
        LinkedList<Integer> l=new LinkedList<>();
        for(int sum=1;sum<=target;sum++){
            for(int i=0;i<candidates.length&&candidates[i]<=sum;i++){
                if(g[sum-candidates[i]]!=null){
                    l.addLast(candidates[i]);
                }
            }
            if(!l.isEmpty()){
                g[sum]=new int[l.size()];
                int i=0;
                while(!l.isEmpty()){
                    g[sum][i++]=l.removeFirst();
                }
            }
        }
        if(g[target]!=null){
            dfs(g, target,target+1);
            return c;
        }else {
            return new LinkedList<>();
        }
    }

    private LinkedList<Integer> s=new LinkedList<>();
    private LinkedList<List<Integer>> c=new LinkedList<>();

    private void dfs(int[][] g, int target,int maxStep){
        if(target==0){
            c.addLast(new LinkedList<>(s));
            return;
        }
        int[] steps=g[target];
        for(int i=0;i<steps.length&&steps[i]<maxStep;i++){
            s.push(steps[i]);
            dfs(g,target-steps[i],steps[i]);
            s.pop();
        }
    }
}

class Permutation{
    List<List<Integer>> p=new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        permute(nums,0);
        return p;
    }
    private void permute(int[] a,int i){
        if(i==a.length){
            p.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
            return;
        }
        for(int j=i;j<a.length;j++){
            swap(a,i,j);
            permute(a,i+1);
            swap(a,j,i);
        }
    }
    private void swap(int[] a,int i,int j){
        int t=a[i];a[i]=a[j];a[j]=t;
    }

    ListNode dummy;
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        dummy=new ListNode(0);
        ListNode tail=dummy;
        for(int n:nums){
            tail.next=new ListNode(n);
            tail=tail.next;
        }
        permuteUnique(nums,0);
        return p;
    }

    private void permuteUnique(int[] a,int i){
        if(i==a.length){
            p.add(Arrays.stream(a).boxed().collect(Collectors.toList()));
            return;
        }
        ListNode prev;
        for(prev=dummy;prev.next!=null;prev=prev.next){
            ListNode curr=prev.next;
            if(curr.next==null||curr.val!=curr.next.val){
                prev.next=curr.next;
                a[i]=curr.val;
                permuteUnique(a,i+1);
                prev.next=curr;
            }
        }
    }
}