package monotonusstack;

import java.util.Stack;

/**
 * Leetcode 1504:
 * 两种思路:
 *  - 动态规划 O(n^3): 请在dp.rectangle包查看，类名与此类名是一样的
 *
 *  - 单调栈 O(n^2): 建议在动态规划的基础上，再看这个优化
 *      - 在dp解法中，每次计算右下角的矩形个数时，都要重新枚举所有以 h 为高的子矩形个数，
 *          无法重复利用之前计算的结果，这导致了复杂度达到 O(n^3)
 *      - 假设有以下情况:
 *          -       1
 *          -     1 1
 *          -   1 1 1
 *          - 1 1 1 1
 *          - 这个时候，计算 (3, 3)的子矩形个数时，很显然是 4 + dp[2, 3]
 *      - 再有以下情况:
 *          -       1
 *          -     1 1
 *          - 1 1 1 1   dp[2,3]此时宽为4, 该行有4个子矩形
 *          -   1 1 1   该行宽为3，上面所有行的宽不能超过此宽
 *          - 这个时候，因为最后一行更短，所以不能简单复用上面的结论
 *      - 所以可将这个问题转化为:
 *          求解上面行中，第一个长度比当前行要短的行，假设该行的编号为 k
 *          则 dp[i, j] = dp[k, j] + length[i, j]*(i - k)
 *      - 可以使用单调递增栈来快速找到 k
 */
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int[][] left = new int[m][n];
        left[0][0] = mat[0][0];
        for(int i = 1; i < m; i ++) left[i][0] = mat[i][0];
        for(int j = 1; j < n; j ++) left[0][j] = mat[0][j] == 1 ? left[0][j - 1] + 1 : 0;
        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j ++) {
                if(mat[i][j] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                }
            }
        }

        int[][] dp = new int[m][n];
        int ans = 0;
        for(int j = 0; j < n; j ++) {
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < m; i ++) {
                while(!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }

                int w = left[i][j];
                int h;
                if(stack.isEmpty()) h = i + 1;
                else h = i - stack.peek();
                dp[i][j] = h*w + (stack.isEmpty() ? 0 : dp[stack.peek()][j]);
                ans += dp[i][j];

                stack.push(i);
            }
        }
        return ans;
    }
}
