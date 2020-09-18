package monotonusstack;

import java.util.Stack;

/**
 * Leetcode1124: 表现良好的最长时间段
 *  - 本质与Leetcode962相同
 *  - 需要一步转化:
 *      - 假设区间 (i, j]，若此区间是一个符合题意的区间:
 *          - 假设良好的一天为1，不良好的一天为0
 *          - (i, j]的区间和 sum, 应满足以下式子:
 *              - sum > j - i - sum -> 2*sum > j - i
 *          - 使用前缀和数组标识区间和:
 *              sum = pre[j] - pre[i], so 2pre[j] - 2pre[i] > j - i -> 2pre[j] - j > 2pre[i] - i
 *          - 最终转化为: given i < j, 2pre[i] - i < 2pre[j] - j, find max(j - i)
 *          - 其中 2pre[x] - x 相当于数组 arr[i]
 *      - 通过上面转化，此题转化为Leetcode962
 */
public class LongestWellPerformingInterval {
    public int longestWPI(int[] hours) {
        int n = hours.length;

        int[] arr = new int[n + 1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += hours[i - 1] > 8 ? 1 : 0;
            arr[i] = 2*sum - i;
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if(stack.isEmpty() || arr[stack.peek()] > arr[i]) {
                stack.push(i);
            }
        }

        int ans = 0;
        for (int i = n; i >= 1; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                ans = Math.max(ans, i - stack.pop());
            }
        }
        return ans;
    }
}
