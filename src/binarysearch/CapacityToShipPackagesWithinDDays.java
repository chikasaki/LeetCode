package binarysearch;

/**
 * Leetcode1011: 在D天内送达包裹的能力
 * - 思路:
 *  - 动态规划
 *      - 状态定义: dp[i][j] 表示前i个货物，j天内送达的最低运载
 *      - 状态转移: 不那么明显，但是感觉上应该可以做？ 不过此题中数据量很大，这种dp必定超时
 *  - 暴力枚举 [1, answer] 中的每个运载能力
 *      - 找到第一个在 D 天内完成任务的，就是答案
 *      - 时间复杂度: O(n*answer)
 *  - 二分搜索:
 *      - 优化暴力枚举
 *      - 可以给出一个尽量大的范围 [1, inf], 最优解必定在此范围内
 *      - 此范围内最小的那个，满足D天要求的数，解释答案
 *      - 以下给出的二分搜索模板，可以求出 指定范围内，最小的满足条件的索引
 *
 */
public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int inf = (int)1e9;

        int lo = 1, hi = inf;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;

            int cost = 1, sum = 0;
            for(int i = 0; i < n; i ++) {
                if(sum + weights[i] <= mid) {
                    sum += weights[i];
                } else {
                    cost ++;
                    sum = weights[i];
                    if(sum > mid) {
                        cost = inf;
                        break;
                    }
                }
            }

            if(cost > D) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
