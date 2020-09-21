package monotonusstack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Leetcode1130: 叶值的最小代价树
 * - 此题有两种思路，此包中仅书写单调栈思路，在dp.intervaldp中会书写dp思路
 * - 单调栈:
 *      - 对于每个叶子，其值都至少会被使用一遍
 *      - 对于值最小的叶子，其值最多只会被使用一遍
 *      - 依次类推
 * - 贪心:
 *      - 为了最后的值最小，子过程对于某一个叶子求值时，要使他尽可能小
 *      - 如: 三个叶子A,B,C，假设B最小，A最大，C中间
 *          - B先与A结合，再与C结合: B*A + A*C
 *          - B先与C结合，再与A结合: B*C + A*C
 *          - 显然，第二种情况比较小，并且可扩展
 * - 综合以上两点特性(极小值只会被使用一遍)，使用单调栈
 *
 * - 时间复杂度: O(n)
 */
public class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;

        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i ++) {
            while(!stack.isEmpty() && stack.peek() <= arr[i]) {
                int small = stack.pop();
                if(stack.isEmpty()) ans += small*arr[i];
                else ans += small*Math.min(arr[i], stack.peek());
            }
            stack.push(arr[i]);
        }

        while(!stack.isEmpty()) {
            int small = stack.pop();
            if(stack.isEmpty()) break;
            ans += small * stack.peek();
        }

        return ans;
    }
}
