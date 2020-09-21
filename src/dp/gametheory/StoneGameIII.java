package dp.gametheory;

/**
 * Leetcode1406: 石子游戏III
 * 思路:
 *  - 经典博弈论 + 动态规划
 *  - 对于两个人来说，任务都是一样的，保证自己的分数足够高；
 *  - 假设使用 f[i] 表示从 i 位置开始取，当前先手者可获得的最高分数
 *      - 当前可取 1，2，3 堆石头，故可由 i -> i + 1, i + 2, i + 3
 *      - 由于当前取了，下一轮是另外一个人取；保证自己分数足够高，换个思考角度就是保证下一个人的分数足够低
 *      - 所以状态转移方程:
 *          f[i] = sum - min(f[i + 1], f[i + 2], f[i + 3]), sum为区间[i, n - 1]的和
 */
public class StoneGameIII {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        int[] presum = new int[n + 1];
        for(int i = 1; i <= n; i ++) {
            presum[i] = presum[i - 1] + stoneValue[i - 1];
        }

        int[] f = new int[n + 3];
        for(int i = n - 1; i >= 0; i --) {
            int sum = presum[n] - presum[i];
            f[i] = sum - Math.min(
                    f[i + 1],
                    Math.min(f[i + 2], f[i + 3])
            );
        }

        if(2*f[0] > presum[n]) return "Alice";
        else if(2*f[0] < presum[n]) return "Bob";
        else return "Tie";
    }
}
