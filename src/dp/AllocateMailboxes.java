package dp;

import java.util.Arrays;

/**
 * Leetcode1478: 安排邮筒
 *  思路: 一道个人觉得很难的dp题，主要是枚举所有情况的方式比较难想
 *      - 基本思想: 针对每一个邮筒，枚举出所有可能被他使用的位置；
 *                  这个过程必定会枚举到最优解
 *      - 当只有一个邮筒时，所有位置的中位数即邮筒应该设置的位置
 *          - 证明:
 *              - 假设只有两个位置: A, B
 *                 A            B
 *                 这时候将邮筒放在A,B之间的任意一个位置都可以得到最优解
 *              - 假设有三个位置: A, B, C
 *                 A      C     B
 *                 这时候为了让邮筒到A, B之间的距离和最小，则邮筒必须要设置在A, B之间
 *                 另外，同时也要满足到C的距离最小，所以邮筒必须设置在C处
 *              - 假设有k个位置: X1, X2, ..., XK
 *                 与上面的过程一样，每次找到最外面的两个位置，邮筒必须在这两个位置之间
 *                 去掉上面两个位置，再找出最外面的两个位置
 *                 ...
 *                 最后，
 *                    - 只剩下一个位置，则邮筒就设置在这个位置
 *                    - 剩下两个位置，则邮筒可以设置在这两个位置之间的任意一个位置
 *      - 当有多个邮筒时:
 *          - 假设当前有 i 个位置， j 个邮筒
 *          - 针对最后一个邮筒，枚举所有可能被他使用的位置:
 *              - 有邮筒时，绝不浪费邮筒；所以前面 j - 1 个位置时最后一个邮筒绝对使用不到的
 *              - 最小的使用情况: 只使用在第i个位置上
 *          - 则状态转移方程为:
 *              dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + cost[k][i]
 *              其中，j <= k <= i, cost[k][i]为 [k, i]上的所有位置到最后一个邮筒的距离之和(使用基本情况可预处理)
 */
public class AllocateMailboxes {
    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        int n = houses.length;
        long inf = (long)1e12;

        int[][] cost = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i ++) {
            for(int j = i; j <= n; j ++) {
                int mid = i + (j - i) / 2;
                for(int t = i; t <= j; t ++) {
                    cost[i][j] += Math.abs(houses[mid - 1] - houses[t - 1]);
                }
            }
        }

        long[][] dp = new long[n + 1][k + 1];
        for(int i = 1; i <= n; i ++) {
            Arrays.fill(dp[i], inf); // dp[i][0] 也要赋值为 inf，因为不存在这种情况; 防止影响状态转移
            for(int j = 1; j <= k; j ++) {
                for(int t = j; t <= i; t ++) {
                    dp[i][j] = Math.min(dp[i][j], dp[t - 1][j - 1] + cost[t][i]);
                }
            }
        }
        return (int)dp[n][k];
    }
}
