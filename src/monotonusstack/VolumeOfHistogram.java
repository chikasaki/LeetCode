package monotonusstack;

import java.util.Stack;

/**
 * Leetcode面试题17.21: 直方图的水量
 * 思路:
 *  - 维护单调递减栈，找到对当前柱子而言，两边比它大的最近元素
 */
public class VolumeOfHistogram {
    public int trap(int[] height) {
        int n = height.length;

        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int curH = height[stack.pop()];
                if(stack.isEmpty()) continue;
                int h = Math.min(height[i], height[stack.peek()]) - curH;
                int w = i - stack.peek() - 1;
                ans += h * w;
            }
            stack.push(i);
        }
        return ans;
    }
}
