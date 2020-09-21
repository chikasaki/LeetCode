package dp.gametheory;

/**
 * Leetcode1510: 石子游戏IV
 * 思路:
 *  - 与Leetcode1406相似
 *  - 假设 dp[i] 表示当前石子堆还剩 i 个石头，当前先手者最后的输赢情况
 *  - 当前先手者可以取任意平方数的石头，故状态转移方程如下:
 *      dp[i] |= !dp[i - j*j], 其中 j*j <= i
 *  - 时间复杂度: O(n(n)^(1/2))
 */
public class StoneGameIV {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        for(int i = 1; i <= n; i ++) {
            for(int j = 1; j*j <= i; j ++) {
                dp[i] |= !dp[i - j*j];
            }
        }

        return dp[n];
    }
}
