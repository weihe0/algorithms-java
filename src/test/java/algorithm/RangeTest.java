package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTest {
    Range r=new Range();
    @Test
    public void subarraysWithKDistinct() {
        Range r=new Range();
        int[] a={1,2,1,2,3};
        r.subarraysWithKDistinct(a,2);
    }
    @Test
    public void fractionToDecimal(){
        System.out.println(r.fractionToDecimal(-50,8));
    }
}