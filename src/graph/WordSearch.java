package graph;

/**
 * Leetcode79: 单词搜索
 * - 一个非常经典的图论四方向深搜问题，是有固定模板的
 *  - nexts数组: 表示下一个可遍历的四个方向
 *  - inArea(): 判断下一个位置是否越界
 *  - visited数组: 判断当前位置是否已遍历
 *  - 使用回溯算法
 */
public class WordSearch {
    private char[][] board;
    private boolean[][] visited;
    private int m, n;
    private String word;
    private final int[][] nexts = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        this.word = word;
        visited = new boolean[m][n];

        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j ++) {
                if(dfs(i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int index) {
        if(board[x][y] != word.charAt(index)) {
            return false;
        }
        if(index == word.length() - 1) return true;

        visited[x][y] = true;
        for(int[] next: nexts) {
            int newX = x + next[0], newY = y + next[1];
            if(inArea(newX, newY) && !visited[newX][newY]) {
                if(dfs(newX, newY, index + 1)) return true;
            }
        }
        visited[x][y] = false;
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
