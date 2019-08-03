package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringManipulationTest {

    @Test
    public void KMP() {
        StringManipulation sm=new StringManipulation();
        System.out.println(sm.shortestPalindrome("acaa"));
    }
}