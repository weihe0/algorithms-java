package algorithm;

import java.util.*;

public class Search {

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