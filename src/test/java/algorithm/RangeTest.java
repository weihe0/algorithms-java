package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class RangeTest {

    @Test
    public void subarraysWithKDistinct() {
        Range r=new Range();
        int[] a={1,2,1,2,3};
        r.subarraysWithKDistinct(a,2);
    }
}