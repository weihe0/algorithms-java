package algorithm;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class SkylineTest {
    @Test
    public void test(){
        Skyline s=new Skyline();
        //int[][] b={{2,9,10}, {3,7,15}, {5,12,12},{15,20,10}, {19,24,8}};
        //int[][] b={{1,3,2},{2,4,4}};
        int[][] b={{1,2,1},{1,2,2},{1,2,3}};
        List<List<Integer>> l=s.getSkyline(b);
        for(List<Integer> p:l){
            System.out.println("("+p.get(0)+","+p.get(1)+")");
        }
    }
}