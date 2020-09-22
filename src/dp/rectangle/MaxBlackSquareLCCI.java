package dp.rectangle;

/**
 * Leetcode面试题17.23: 最大黑方阵
 * - 求正方形的最大边长
 * - 维护left，top数组
 */
public class MaxBlackSquareLCCI {
    public int[] findSquare(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int[][] dp = new int[m][n];
        int[][] left = new int[m][n];
        int[][] top = new int[m][n];
        dp[0][0] = left[0][0] = top[0][0] = 1 - matrix[0][0];
        for(int i = 1; i < m; i ++) {
            left[i][0] = 1 - matrix[i][0];
            top[i][0] = matrix[i][0] == 0 ? top[i - 1][0] + 1 : 0;
            dp[i][0] = matrix[i][0];
        }
        for(int j = 1; j < n; j ++) {
            left[0][j] = matrix[0][j] == 0 ? left[0][j - 1] + 1 : 0;
            top[0][j] = 1 - matrix[0][j];
            dp[0][j] = matrix[0][j];
        }
        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j ++) {
                if(matrix[i][j] == 0) {
                    left[i][j] = left[i][j - 1] + 1;
                    top[i][j] = top[i - 1][j] + 1;
                    for(int edge = 1; edge <= Math.min(left[i][j], top[i][j]); edge ++) {
                        int newI = i - edge + 1;
                        int newJ = j - edge + 1;
                        if(Math.min(top[i][newJ], left[newI][j]) >= edge) {
                            dp[i][j] = edge;
                        }
                    }
                }
            }
        }

        int[] res = new int[3];
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j ++) {
                if(dp[i][j] > res[2]) {
                    res[0] = i - dp[i][j] + 1;
                    res[1] = j - dp[i][j] + 1;
                    res[2] = dp[i][j];
                }
            }
        }
        if(res[2] == 0) return new int[0];
        return res;
    }
}
