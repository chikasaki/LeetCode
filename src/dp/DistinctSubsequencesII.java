package dp;

import java.util.Arrays;

/**
 * Leetcode940: 不同的子序列II
 * 思路:
 *  - 状态定义: dp[i] 表示 S[1-i] 包含不同非空子序列的个数
 *  - 状态转移:
 *      dp[i] = 2*dp[i - 1] + 1
 *      dp[i] -= (dp[last[s[i] - 'a'] - 1] + 1)
 *    其中，last[i]表示小写字母(i + 'a')在已经遍历过的部分中，最近的位置
 *    - 为什么要在 dp[last[] - 1] 中减1？
 *      举个例子: asds, 其中s重复了两次；"ass" 也是一个可能的子序列
 *    - 为什么要在 dp[last[] - 1] + 1 外加1？
 *      举个例子: asds, 排除掉 's' 的重复
 *  - 时间复杂度: O(n)
 */
public class DistinctSubsequencesII {
    public int distinctSubseqII(String S) {
        int n = S.length();
        final int MOD = 1_000_000_007;

        int[] last = new int[26];
        Arrays.fill(last, -1);
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i ++) {
            dp[i] = (2*dp[i - 1] + 1) % MOD;
            char c = S.charAt(i - 1);
            if(last[c - 'a'] != -1) {
                dp[i] -= (dp[last[c - 'a'] - 1] + 1);
            }
            last[c - 'a'] = i;
            dp[i] %= MOD;
        }

        return dp[n] < 0 ? dp[n] + MOD : dp[n];
    }
}
