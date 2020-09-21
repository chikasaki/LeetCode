package dp;

/**
 * Leetcode115: 不同的子序列
 * 思路:
 *  - 状态定义:
 *      dp[i][j]表示 S[1-i]中包含 T[1-j]的个数
 *  - 状态转移:
 *      if S[i] == T[j], dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
 *      if S[i] != T[j], dp[i][j] = dp[i - 1][j]
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();

        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i ++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= m; i ++) {
            for(int j = 1; j <= n; j ++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
