package dp.rectangle;

/**
 * Leetcode 1504:
 * 两种思路:
 *  - 动态规划 O(n^3)
 *      - 状态定义: left[i][j]表示以 (i, j) 为最右，可向左扩展的最大边长
 *                 top[i][j]表示以 (i, j) 为最下，可向上扩展的最大边长
 *      - 根据 left, top 求出以 (i, j) 为右下角的矩形个数
 *          - 固定宽/高，假设固定宽(即left[i][j])，每次增加高h(即top[i][j])，枚举以h为高的所有子矩形
 *
 *  - 单调栈 O(n^2): 请在单调栈包查看，类名与此类名是一样的
 */
public class CountSubmatricesWithAllOnes {
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;

        int[][] left = new int[m][n];
        int[][] top = new int[m][n];
        left[0][0] = top[0][0] = mat[0][0];
        for(int i = 1; i < m; i ++) {
            left[i][0] = mat[i][0];
            top[i][0] = mat[i][0] == 1 ? top[i - 1][0] + 1 : 0;
        }
        for(int j = 1; j < n; j ++) {
            left[0][j] = mat[0][j] == 1 ? left[0][j - 1] + 1 : 0;
            top[0][j] = mat[0][j];
        }
        for(int i = 1; i < m; i ++) {
            for(int j = 1; j < n; j ++) {
                if(mat[i][j] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    top[i][j] = top[i - 1][j] + 1;
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j ++) {
                int w = left[i][j];
                for(int h = 1; h <= top[i][j]; h ++) {
                    w = Math.min(w, left[i - h + 1][j]);
                    ans += w;
                }
            }
        }
        return ans;
    }
}
