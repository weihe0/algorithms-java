package algorithm;

import org.junit.Test;

import static java.lang.StrictMath.*;
import static java.lang.Character.*;

import java.util.*;

public class StringManipulation {
    public String minWindow(String s, String t) {

        int[] count = new int[0x80];
        for (char c : t.toCharArray()) {
            count[c]++;
        }
        int rem = t.length();
        int minBegin = 0, minEnd = s.length() + 1;
        int begin, end;
        for (begin = 0, end = 1; end <= s.length(); end++) {
            // ending char
            char ec = s.charAt(end - 1);
            count[ec]--;
            if (count[ec] >= 0) {
                rem--;
            }
            while (rem == 0) {
                if (end - begin < minEnd - minBegin) {
                    minBegin = begin;
                    minEnd = end;
                }
                // beginning char
                char bc = s.charAt(begin);
                count[bc]++;
                if (count[bc] > 0) {
                    rem++;
                }
                begin++;
            }
        }

        if (minEnd == s.length() + 1) {
            return "";
        } else {
            return s.substring(minBegin, minEnd);
        }
    }

    public int myAtoi(String str) {
        // ignore the leading whitespaces
        int i;
        for (i = 0; i < str.length() && str.charAt(i) == ' '; i++) {
        }
        if (i == str.length()) {
            return 0;
        }
        boolean positive;
        // positive/negative sign
        switch (str.charAt(i)) {
            case '+':
                positive = true;
                i++;
                break;
            case '-':
                positive = false;
                i++;
                break;
            default:
                positive = true;
        }
        if (i == str.length()) {
            return 0;
        }
        // digits
        int value = 0;
        try {
            while (i < str.length() && isDigit(str.charAt(i))) {
                int d = str.charAt(i) - '0';
                if (positive) {
                    value = addExact(multiplyExact(value, 10), d);
                } else {
                    value = addExact(multiplyExact(value, 10), -d);
                }
                i++;
            }
        } catch (ArithmeticException e) {
            if (positive) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
        return value;
    }

    public int numDecodings(String s) {
        // a[i] is the number of decoding ways of i-length substring [0..i)
        int[] a = new int[s.length() + 1];
        a[0] = 1;
        if (s.charAt(0) >= '1' && s.charAt(0) <= '9') {
            a[1] = 1;
        } else {
            a[1] = 0;
        }
        // get the decoding ways from length of 2 to s.length()
        for (int len = 2; len <= s.length(); len++) {
            if (s.charAt(len - 1) >= '1' && s.charAt(len - 1) <= '9') {
                a[len] += a[len - 1];
            }
            int twoDigit = (s.charAt(len - 2) - '0') * 10 + s.charAt(len - 1) - '0';
            if (twoDigit >= 10 && twoDigit <= 26) {
                a[len] += a[len - 2];
            }
        }
        return a[s.length()];
    }

    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        boolean[][] a = new boolean[s.length()][s.length()];

        // odd
        for (int i = 0; i < s.length(); i++) {
            a[i][i] = true;
        }
        int first = 0, last = 0;
        // even
        for (int i = 0; i + 1 < s.length(); i++) {
            a[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            if (a[i][i + 1] && last - first < 1) {
                first = i;
                last = i + 1;
            }
        }

        for (int step = 2; step < s.length(); step++) {
            for (int i = 0; i + step < s.length(); i++) {
                a[i][i + step] = a[i + 1][i + step - 1] && s.charAt(i) == s.charAt(i + step);
                if (a[i][i + step] && step > last - first) {
                    first = i;
                    last = i + step;
                }
            }
        }
        return s.substring(first, last + 1);
    }

    public String Manacher(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            str.append('#');
            str.append(s.charAt(i));
        }
        str.append('#');
        int lower = 0, upper = 0, len = 0;// lower and upper bound of palindrome
        int right = 0, centre = 0;// the rightmost point of rightmost palindrome and the centre
        int[] radius = new int[str.length()];
        // #a#b#c# first and last char are placeholders
        for (int j = 1; j < str.length(); j++) {
            // if j is within the palindrome, find the symmetry point
            if (right > j) {
                radius[j] = Integer.min(radius[2 * centre - j], right - j);
            } else {
                radius[j] = 1;
            }
            // expand the palindrome centred at j
            while (j - radius[j] >= 0 && j + radius[j] < str.length()
                    && str.charAt(j - radius[j]) == str.charAt(j + radius[j])) {
                radius[j]++;
            }
            // update rightmost palindrome
            if (j + radius[j] > right) {
                right = j + radius[j];
                centre = j;
            }
            // update longest palindrome
            if (radius[j] > len) {
                len = radius[j] - 1;
                lower = (j - radius[j] + 1) / 2;
                upper = (j + radius[j] - 1) / 2;
            }
        }
        return s.substring(lower, upper);
    }

    public int KMP(String s, String p) {
        int[] next = getNext(p);
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == p.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public int[] getNext(String p) {
        int[] next = new int[p.length()];
        next[0] = -1;
        int j = 0, k = -1;
        while (j + 1 < p.length()) {
            if (k == -1 || p.charAt(j) == p.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public int[] getNext2(String p) {
        int[] next = new int[p.length()];
        next[0] = 0;
        for (int j = 1; j < p.length(); j++) {
            int k = next[j - 1];
            while (k > 0 && p.charAt(j) != p.charAt(k)) {
                k = next[k - 1];
            }
            if (p.charAt(j) == p.charAt(k)) {
                k++;
            }
            next[j] = k;
        }
        return next;
    }

    public String shortestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        StringBuilder ori = new StringBuilder(s);
        StringBuilder rev = new StringBuilder(s);
        rev.reverse();
        StringBuilder str = ori.append('#').append(rev);
        int len = str.length();
        int[] next = new int[len + 1];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < len) {
            if (j == -1 || str.charAt(i) == str.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return rev.substring(0, s.length() - next[len]) + s;
    }

    public int calculate(String s) throws ArithmeticException {
        int[] nums = new int[3];
        int nTop = 0;
        char[] ops = new char[2];
        int oTop = 0;
        int i = 0;
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') i++;
            if (i < s.length()) {
                if (isDigit(s.charAt(i))) {
                    int n = s.charAt(i++) - '0';
                    while (i < s.length() && isDigit(s.charAt(i))) {
                        n *= 10;
                        n += s.charAt(i++) - '0';
                    }
                    nums[nTop++] = n;
                } else if (isOp(s.charAt(i))) {
                    while (oTop > 0 && !prefRight(ops[oTop - 1], s.charAt(i))) {
                        int bin = calcBin(nums[nTop - 2], ops[--oTop], nums[nTop - 1]);
                        nums[nTop - 2] = bin;
                        nTop--;
                    }
                    ops[oTop++] = s.charAt(i++);
                } else {
                    throw new ArithmeticException();
                }
            }
        }
        while (oTop > 0) {
            int bin = calcBin(nums[nTop - 2], ops[--oTop], nums[nTop - 1]);
            nums[nTop - 2] = bin;
            nTop--;
        }
        return nums[0];
    }
    public int calculate2(String s) throws ArithmeticException{
        Stack<Integer> nums=new Stack<>();
        Stack<Character> ops=new Stack<>();
        int i=0;
        while(i<s.length()){
            while(i<s.length()&&s.charAt(i)==' ') i++;
            if(i<s.length()){
                if(isDigit(s.charAt(i))){
                    int n=s.charAt(i++)-'0';
                    while(i<s.length()&&isDigit(s.charAt(i))){
                        n*=10;
                        n+=s.charAt(i++)-'0';
                    }
                    nums.push(n);
                }else if(s.charAt(i)=='('){
                    ops.push(s.charAt(i++));
                }else if(s.charAt(i)==')'){
                    while(ops.peek()!='('){
                        calcBin(nums,ops);
                    }
                    ops.pop();
                    i++;
                }else {
                    while(!ops.isEmpty()&&ops.peek()!='('
                            &&!prefRight(ops.peek(),s.charAt(i))){
                        calcBin(nums,ops);
                    }
                    ops.push(s.charAt(i++));
                }
            }
        }
        while(!ops.isEmpty()){
            calcBin(nums,ops);
        }
        return nums.pop();
    }

    @Test
    public void calc(){
        calculate2("(1+(4+5+2)-3)+(6+8)");
    }
    private void calcBin(Stack<Integer> nums,Stack<Character> ops){
        int r=nums.pop(),l=nums.pop();
        char o=ops.pop();
        switch (o){
            case '+':nums.push(l+r);break;
            case '-':nums.push(l-r);break;
            case '*':nums.push(l*r);break;
            case '/':nums.push(l/r);break;
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean prefRight(char l, char r) {
        return (l == '+' || l == '-') && (r == '*' || r == '/');
    }

    private int calcBin(int l, char o, int r) throws ArithmeticException {
        switch (o) {
            case '+':
                return l + r;
            case '-':
                return l - r;
            case '*':
                return l * r;
            case '/':
                return l / r;
            default:
                throw new ArithmeticException();
        }
    }
}


class Regex {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean singleMatch = i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || singleMatch && dp[i + 1][j];
                } else {
                    dp[i][j] = singleMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}

class WildCard {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int j = p.length() - 1; j >= 0; j--) {
            if (p.charAt(j) == '*') {
                dp[s.length()][j] = dp[s.length()][j + 1];
            } else {
                dp[s.length()][j] = false;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                } else if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[0][0];
    }
}

class WordBreak {
    LinkedList<String> l = new LinkedList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] isSent = new boolean[len + 1];
        ArrayList<LinkedList<Integer>> next = new ArrayList<>(len);
        for (int i = 0; i < len; i++) {
            next.add(new LinkedList<>());
        }
        isSent[0] = true;
        for (int j = 1; j <= len; j++) {
            for (int i = 0; i < j; i++) {
                for (String word : wordDict) {
                    if (s.substring(i, j).compareTo(word) == 0 && isSent[i]) {
                        isSent[j] = true;
                        next.get(i).add(j);
                    }
                }
            }
        }
        if (isSent[len]) {
            dfs(0, "", s, len, next);
        }
        return l;
    }

    private void dfs(int i, String sent, String s, int len, ArrayList<LinkedList<Integer>> next) {
        if (i == len) {
            l.add(sent);
            return;
        }
        for (int j : next.get(i)) {
            String word = s.substring(i, j);
            if (i == 0) {
                dfs(j, word, s, len, next);
            } else {
                dfs(j, sent + ' ' + word, s, len, next);
            }
        }
    }
}