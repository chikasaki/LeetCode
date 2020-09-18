package monotonusstack;

import java.util.Stack;

/**
 * Leetcode962: 最大宽度坡
 * 问题背景: 求离当前元素最远的并且大于当前元素的元素，两者之间的距离要达到最大
 *  - given i < j & arr[i] <= arr[j], find max(j - i)
 * 思路:
 *  - 暴力算法O(n^2): 从右往左遍历第一个大于当前元素的元素
 *  - 优化: 能不能只从右往左遍历一遍？ 如果整个数组是单调递减的，那么是可以的
 *     - 给定 i, i + 1, if arr[i] < arr[i + 1]，那么 i + 1 位置必不可能成为最终答案 max(j - i) 的i
 *     - 根据上面推论，可以推导出，想要某个位置成为最终答案中的i，那么这个位置之前的所有位置元素，都必须比当前元素要大
 *       所以，可以做出一个单调递减的数组，之后就可以从右往左只遍历一遍来找到最终解
 */
public class MaximumWidthRamp {
    public int maxWidthRamp(int[] A) {
        int n = A.length;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if(stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }

        int ans = 0;
        for(int i = n - 1; i >= 1; i --) {
            while (!stack.isEmpty() && A[i] >= A[stack.peek()]) {
                ans = Math.max(ans, i - stack.pop());
            }
        }
        return ans;
    }
}
