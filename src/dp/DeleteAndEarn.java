package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Leetcode740: 删除与获得点数
 * - 思路:
 *  - 点数大小范围为 [1, 10000]，可以遍历这个范围，对每个在数组中的数进行状态转移
 *  - 状态定义: dp[i] 表示当nums中只包含 i和所有比i小的数 时，可获得的最大点数
 *  - 状态转移:
 *       dp[i] = max(dp[i - 1], dp[i - 2] + i*freq[i])
 *       dp[i]由两种状态转移而来:
 *          - 不拿取 i 这个数
 *          - 拿取 i 这个数，那么 i - 1 这个数就不能再拿取了
 */
public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        final int bound = 10000;
        Map<Integer, Integer> freq = new HashMap<>();

        for(int num: nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int[] dp = new int[bound + 1];
        dp[0] = 0; dp[1] = freq.getOrDefault(1, 0);
        for(int i = 2; i <= bound; i ++) {
            dp[i] = Math.max(
                    dp[i - 1],
                    dp[i - 2] + freq.getOrDefault(i, 0)*i
            );
        }
        return dp[bound];
    }
}
