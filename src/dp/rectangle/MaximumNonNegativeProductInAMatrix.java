package dp.rectangle;

/**
 * Leetcode1594: 矩阵的最大非负积
 * - 由于矩阵中存在负数，最小值有可能再下一瞬间就变成最大值，所以要同时维护最小值和最大值
 */
public class MaximumNonNegativeProductInAMatrix {
    public int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        final int MOD = 1_000_000_007;

        long[][] max = new long[m][n];
        long[][] min = new long[m][n];
        max[0][0] = min[0][0] = grid[0][0];
        for(int i = 1; i < m; i ++) {
            max[i][0] = max[i - 1][0]*grid[i][0];
            min[i][0] = min[i - 1][0]*grid[i][0];
        }
        for(int j = 1; j < n; j ++) {
            max[0][j] = max[0][j - 1]*grid[0][j];
            min[0][j] = min[0][j - 1]*grid[0][j];
        }
        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j ++) {
                max[i][j] = Math.max(
                        Math.max(max[i - 1][j]*grid[i][j], max[i][j - 1]*grid[i][j]),
                        Math.max(min[i - 1][j]*grid[i][j], min[i][j - 1]*grid[i][j])
                );
                min[i][j] = Math.min(
                        Math.min(max[i - 1][j]*grid[i][j], max[i][j - 1]*grid[i][j]),
                        Math.min(min[i - 1][j]*grid[i][j], min[i][j - 1]*grid[i][j])
                );
            }
        }

        return max[m - 1][n - 1] < 0 ? -1 : (int) (max[m - 1][n - 1] % MOD);
    }
}
