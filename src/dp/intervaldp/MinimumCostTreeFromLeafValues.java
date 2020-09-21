package dp.intervaldp;

import java.util.Arrays;

/**
 * Leetcode1130: 叶值的最小代价树
 * - 此题有两种思路，此包中仅书写dp思路，在单调栈包会书写另外一种思路
 * - 区间dp:
 *      - 状态定义: dp[i][j]: [i, j]区间上的最小叶值和
 *      - 状态转移:
 *          dp[i][j] = Math.min(
 *              dp[i][j],
 *              dp[i][cut] + dp[cut + 1][j] + leftLeave*rightLeave
 *         )
 *
 * - 时间复杂度: O(n^3)
 */
public class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int inf = (int)1e9;

        int[][] max = new int[n][n]; //快速查找区间最大值
        for(int i = 0; i < n; i ++) {
            for(int j = i; j < n; j ++) {
                for(int k = i; k <= j; k ++) {
                    max[i][j] = Math.max(max[i][j], arr[k]);
                }
            }
        }

        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i ++) {
            Arrays.fill(dp[i], inf);
            dp[i][i] = 0;
        }

        for(int bias = 1; bias < n; bias ++) {
            for(int l = 0; l < n - bias; l ++) {
                int r = l + bias;
                for(int cut = l; cut < r; cut ++) {
                    dp[l][r] = Math.min(
                            dp[l][r],
                            dp[l][cut] + dp[cut + 1][r] + max[l][cut]*max[cut + 1][r]
                    );
                }
            }
        }

        return dp[0][n - 1];
    }
}
