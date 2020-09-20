package dp.threedimensiondp;

import java.util.Arrays;

/**
 * Leetcode1473: 给房子涂色
 *  - 遍历到每一个房子，都可能有以下状态:
 *      - 当前房子颜色为 j (1 <= j <= n)
 *      - 当前组成了 k 个街区 (1 <= k <= target)
 *  - 状态定义:
 *      - dp[i][j][k]: 当前总共有 i 个房子，并且第 i 个房子的颜色为 j，共组成了 k 个街区
 *  - 状态转移:
 *      dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][preJ][j == preJ ? k : k - 1] + cost)
 *      其中 1 <= preJ <= n, cost == 0 if houses[i] != 0, cost != 0 if houses[i] != 0
 */
public class PaintHouseIII {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int inf = (int)1e8;

        int[][][] dp = new int[m + 1][n + 1][target + 1];
        for (int i = 0; i <= m; i++) {
            for(int j = 1; j <= n; j ++) {
                Arrays.fill(dp[i][j], inf);
                if(i == 0) dp[0][j][0] = 0;
            }
        }

        for(int i = 1; i <= m; i ++) {
            for(int j = 1; j <= n; j ++) {
                if(houses[i - 1] != 0 && j != houses[i - 1]) {
                    continue;
                }
                int c = houses[i - 1] == j ? 0 : cost[i - 1][j - 1];
                for(int k = 1; k <= target; k ++) {
                    for(int preJ = 1; preJ <= n; preJ ++) {
                        dp[i][j][k] = Math.min(
                            dp[i][j][k],
                            dp[i - 1][preJ][j == preJ ? k : k - 1] + c
                        );
                    }
                }
            }
        }

        int ans = inf;
        for(int j = 1; j <= n; j ++) {
            ans = Math.min(ans, dp[m][j][target]);
        }
        return ans == inf ? -1 : ans;
    }
}
