package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreeSumTest {
    @Test
    public void test(){
        ThreeSum ts=new ThreeSum();
        int[] nums={3,0,-2,-1,1,2};
        ts.threeSum(nums);
    }
}