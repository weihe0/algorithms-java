package algorithm;

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
}
