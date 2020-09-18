package monotonusstack;

import java.util.Stack;

/**
 * Leetcode84: 柱状图中的最大矩形
 * 经典单调栈问题
 * 个人理解的单调栈这种数据结构的适用场景:
 *  - 求数组中某元素，左右两边比此元素大的最近元素，用单调递减栈
 *  - 求数组中某元素，左右两边比此元素小的最近元素，用单调递增栈
 *
 * 此题思路:
 *  - 对于当前位置，如果使用此位置高度作为矩形高，则应该找到左右两边比此位置高度小的最近元素
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int w, h;

        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                h = heights[stack.pop()];
                if(stack.isEmpty()) w = i;
                else w = i - stack.peek() - 1;
                ans = Math.max(ans, h*w);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            h = heights[stack.pop()];
            w = n - (stack.isEmpty() ? 0 : (stack.peek() + 1));
            ans = Math.max(ans, h * w);
        }
        return ans;
    }
}
