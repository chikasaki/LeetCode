package monotonusstack;

import java.util.Stack;

/**
 * Leetcode85: 最大矩形
 * 两种思路:
 *  - 动态规划 O(n^3): 请在dp.rectangle包查看，类名与此类名是一样的
 *
 *  - 单调栈 O(n^2): 思路与 Leetcode84: 柱状图中的最大矩形基本是一致的，不过需要一步转化:
 *      - 求解整个矩阵中的最大矩形，相当于求解以每一列作为底边，并向左扩展，得到的所有柱状图中的最大矩形
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;

        int[][] left = new int[m][n];
        left[0][0] = matrix[0][0] - '0';
        for(int i = 1; i < m; i ++) left[i][0] = matrix[i][0] - '0';
        for(int j = 1; j < n; j ++) left[0][j] = matrix[0][j] == '1' ? left[0][j - 1] + 1 : 0;
        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j ++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = left[i][j - 1] + 1;
                }
            }
        }

        int ans = 0;
        for(int j = 0; j < n; j ++) {
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < m; i ++) {
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    int w = left[stack.pop()][j];
                    int h = i - (stack.isEmpty() ? 0 : (stack.peek() + 1));
                    ans = Math.max(ans, h * w);
                }
                stack.push(i);
            }

            while(!stack.isEmpty()) {
                int w = left[stack.pop()][j];
                int h = m - (stack.isEmpty() ? 0 : (stack.peek() + 1));
                ans = Math.max(ans, h * w);
            }
        }
        return ans;
    }
}
