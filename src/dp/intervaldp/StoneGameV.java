package dp.intervaldp;

import java.util.Arrays;

/**
 * Leetcode 1563: 石子游戏V
 * 思路:
 *  - 状态定义: dp[l][r] 表示 [l, r]区间上Alice能够获取的最高分数
 *  - 状态转移:
 *      dp[l][r] = Math.min(l_sum, r_sum) +
 *                  if l_sum < r_sum: dp[left]
 *                  else if l_sum > r_sum: dp[right]
 *                  else max(dp[left], dp[right])
 *  - 时间复杂度:
 *      对于每个区间[l, r]，还需要枚举其分割的情况，最终时间复杂度为 O(n^3)
 */
public class StoneGameV {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int inf = (int)-1e9;

        int[] presum = new int[n + 1];
        for(int i = 1; i <= n; i ++) {
            presum[i] = presum[i - 1] + stoneValue[i - 1];
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], inf);
            dp[i][i] = 0;
        }

        for(int bias = 1; bias < n; bias ++) {
            for(int l = 0; l < n - bias; l ++) {
                int r = l + bias;
                int sum = presum[r + 1] - presum[l];
                int l_sum = 0;
                for(int cut = l; cut < r; cut ++) {
                    l_sum += stoneValue[cut];
                    int next_state;
                    if(l_sum < sum - l_sum) next_state = dp[l][cut];
                    else if(l_sum > sum - l_sum) next_state = dp[cut + 1][r];
                    else next_state = Math.max(dp[l][cut], dp[cut + 1][r]);

                    dp[l][r] = Math.max(
                            dp[l][r],
                            next_state + Math.min(l_sum, sum - l_sum)
                    );
                }
            }
        }

        return dp[0][n - 1];
    }
}
