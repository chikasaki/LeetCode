package dp;

import java.util.Arrays;

/**
 * Leetcode1402: 做菜顺序
 *  思路:
 *      - 很明显，满意程度越大的菜应该越往后放
 *      - 状态转移:
 *          - 假设当前是第 i 道菜，加上当前这道菜，已经做了 j 道菜
 *          - 对于每一道菜，有做与不做两种选择
 *          - 状态转移方程:
 *              dp[i][j] = Math.max(
 *                  dp[i - 1][j - 1] + j*s[i], //做
 *                  dp[i - 1][j] //不做
 *              )
 */
public class ReducingDishes {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int inf = (int)-1e9;

        int[][] dp = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i ++) {
            Arrays.fill(dp[i], inf);
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= i; j ++) {
                dp[i][j] = Math.max(
                        dp[i - 1][j - 1] + j*satisfaction[i - 1],
                        dp[i - 1][j]
                );
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dp[n][i]);
        }
        return ans;
    }
}
