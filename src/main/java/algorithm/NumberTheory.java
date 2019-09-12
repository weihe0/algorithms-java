package algorithm;

import org.junit.Test;
import static java.lang.StrictMath.*;

public class NumberTheory {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        boolean[] isComp = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isComp[i]) {
                count++;
                for (int j = i * 2; j < n; j += i) {
                    isComp[j] = true;
                }
            }
        }
        return count;
    }

    public double myPow(double x, int n) {
        long N;
        if (n < 0) {
            x = 1 / x;
            N = -(long) n;
        } else {
            N = n;
        }
        double acc = 1.0, base = x;
        for (long i = N; i > 0; i >>= 1) {
            if ((i & 1) == 1) {
                acc *= base;
            }
            base *= base;
        }
        return acc;
    }

    public int reverse(int x){
        int y=0;
        try {
            while(x!=0){
                y=addExact(multiplyExact(y,10),x%10);
                x/=10;
            }
        }catch (ArithmeticException e){
            return 0;
        }
        return y;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sentinel = new ListNode(0);
        int carry=0,val=0;
        ListNode pred=sentinel;
        while(l1!=null||l2!=null){
            if(l1==null){
                val=(l2.val+carry)%10;
                carry=(l2.val+carry)/10;
                pred.next=new ListNode(val);
                pred=pred.next;
                l2=l2.next;
            }else if(l2==null){
                val=(l1.val+carry)%10;
                carry=(l1.val+carry)/10;
                pred.next=new ListNode(val);
                pred=pred.next;
                l1=l1.next;
            }else {
                val=(l1.val+l2.val+carry)%10;
                carry=(l1.val+l2.val+carry)/10;
                pred.next=new ListNode(val);
                pred=pred.next;
                l1=l1.next;
                l2=l2.next;
            }
        }
        if(carry>0){
            pred.next=new ListNode(carry);
        }
        return sentinel.next;
    }

    public String multiply(String num1, String num2) {
        if(num1.compareTo("0")==0||num2.compareTo("0")==0){return "0";}
        char[] p=new char[num1.length()+num2.length()];
        for(int i=0;i<p.length;i++) p[i]='0';
        for(int i=0;i<num1.length();i++){
            int tens=0;
            for(int j=0;j<num2.length();j++){
                int ni=num1.charAt(num1.length()-1-i)-'0';
                int nj=num2.charAt(num2.length()-1-j)-'0';
                int offset=p.length-1-i-j;
                int bij=p[offset]-'0';
                int pij=ni*nj+bij+tens;
                int units=pij%10;
                p[offset]=(char)(units+'0');
                tens=pij/10;
            }
            int offset=p.length-1-i-num2.length();
            p[offset]=(char)(p[offset]+tens);
        }
        if(p[0]=='0'){
            return new String(p,1,p.length-1);
        }else {
            return new String(p);
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x){val=x;}
}