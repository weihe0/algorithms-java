package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class LargestNumberTest {
    LargestNumber l=new LargestNumber();
    @Test
    public void test(){
        int[] nums={10,2};
        System.out.println(l.largestNumber(nums));
    }
}