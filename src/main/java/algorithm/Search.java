package algorithm;

import org.junit.Test;

import java.util.*;

public class Search {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return false;
        }
        int r=matrix.length;
        int c=matrix[0].length;
        int i=0,j=c-1;
        while(i>=0&&i<r&&j>=0&&j<c){
            if(matrix[i][j]==target){
                return true;
            }else if(matrix[i][j]>target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    public boolean search(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while(l<=r){
            int m=l+r>>>1;
            if(target==nums[m]){
                return true;
            }else if(nums[l]==nums[m]&&nums[m]==nums[r]){
                r--;
            }else if(nums[l]<=nums[m]){
                if(target>=nums[l]&&target<=nums[m]){
                    r=m-1;
                }else {
                    l=m+1;
                }
            }else if(target>=nums[m]&&target<=nums[r]){
                l=m+1;
            }else {
                r=m-1;
            }
        }
        return false;
    }
    @Test
    public void testSearch(){
        int[] nums={1,3,1,1,1};
        System.out.println(search(nums,3));
    }
}

class WordSearch2 {
    class Node {
        boolean visited = false;
        String word = null;
        Node[] nexts = new Node[26];
    }

    int rows, columns;
    char[][] board;
    boolean[][] visited;
    LinkedList<String> found;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        // create Trie tree
        Node root = new Node();
        for (String word : words) {
            Node node = root;
            for (char c : word.toCharArray()) {
                int ord = c - 'a';
                if (node.nexts[ord] == null) {
                    node.nexts[ord] = new Node();
                }
                node = node.nexts[ord];
            }
            node.word = word;
        }
        // initialise parameters
        rows = board.length;
        columns = board[0].length;
        visited = new boolean[rows][columns];
        found = new LinkedList<>();
        // perform DFS on each cell of the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                dfs(i, j, root);
            }
        }
        return found;
    }

    private void dfs(int i, int j, Node root) {
        char c = board[i][j];
        int ord = c - 'a';
        Node next = root.nexts[ord];
        if (!visited[i][j] && next != null) {
            visited[i][j] = true;
            if (next.word != null) {
                if (!next.visited) {
                    found.addLast(next.word);
                    next.visited = true;
                }
            }
            if (i - 1 >= 0) {
                dfs(i - 1, j, next);
            }
            if (i + 1 < rows) {
                dfs(i + 1, j, next);
            }
            if (j - 1 >= 0) {
                dfs(i, j - 1, next);
            }
            if (j + 1 < columns) {
                dfs(i, j + 1, next);
            }
            visited[i][j] = false;
        }
    }
}