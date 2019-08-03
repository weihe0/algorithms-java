package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryIndexedTreeTest {

    @Test
    public void partialSum() {
        int[] a={0,1,1,1,1,1,1,1,1};
        BinaryIndexedTree t=new BinaryIndexedTree(a);
        t.partialSum(7);
    }
}