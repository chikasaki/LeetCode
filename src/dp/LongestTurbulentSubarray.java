package dp;

/**
 * Leetcode978: 最大湍流子数组
 * - 思路:
 *  - 最大连续问题的模板问题
 *  - 状态定义:
 *      - dp[i] 表示以 i 结尾的，最长湍流数组
 *      - 由于这里湍流数组可能由两种情况:
 *          - 以 i 结尾的，并且从 i - 1 到 i 是递增的
 *          - 以 i 结尾的，并且从 i - 1 到 i 是递减的
 *        所以使用两种状态分别表示这两种情况
 *  - 状态转移:
 *      初始化: dp[i][0] = dp[i][1] = 1;
 *      if A[i] > A[i - 1], dp[i][0] = dp[i - 1][1] + 1
 *      if A[i] < A[i - 1], dp[i][1] = dp[i - 1][0] + 1
 */
public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] A) {
        int n = A.length;

        int ans = 1;
        int[][] dp = new int[n][2];
        dp[0][0] = dp[0][1] = 1;
        for(int i = 1; i < n; i ++) {
            dp[i][0] = dp[i][1] = 1;
            if (A[i] > A[i - 1]) {
                dp[i][0] = dp[i - 1][1] + 1;
            } else if (A[i] < A[i - 1]) {
                dp[i][1] = dp[i - 1][0] + 1;
            }
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }
}
