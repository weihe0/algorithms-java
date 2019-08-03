package algorithm;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class WordBreakTest {
    @Test
    public void test(){
        WordBreak wb=new WordBreak();
        String s="catsanddog";
        List<String> wordDict
                =Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> l=wb.wordBreak(s,wordDict);
        for(String w:l){
            System.out.println(w);
        }

    }
}