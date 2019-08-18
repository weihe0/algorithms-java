package algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class CombinationalTest {
    Combinational c=new Combinational();
    @Test
    public void knightDialer(){
        assertEquals(c.knightDialer(1),10);
        assertEquals(c.knightDialer(2),20);
        assertEquals(c.knightDialer(3),46);
    }
}