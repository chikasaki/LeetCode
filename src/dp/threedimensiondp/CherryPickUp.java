package dp.threedimensiondp;

import java.util.Arrays;

/**
 * Leetcode741: 摘樱桃
 * - 根据题意，需要一来一回，但一来一回不方便代码书写，也不方便状态定义，
 *      可以转化为，两个人同时从右上角出发，去往左下角
 * - 状态定义:
 *      - dp[t][r1][r2] 代表 当前每个人各走了t步，A到达了位置 (r1, t - r1), B到达了位置 (r2, t - r2)
 * - 状态转移:
 *      - 由于A，B都可由左和上两种情况转移而来，所以当前状态总共可由 4 种状态转移而来
 *      - 4种状态:
 *          - A: left, B: left, dp[t - 1][r1][r2]
 *          - A: left, B: top,  dp[t - 1][r1][r2 - 1]
 *          - A: top, B: left,  dp[t - 1][r1 - 1][r2]
 *          - A: top, B: top,   dp[t - 1][r1 - 1][r2 - 1]
 *      - dp[t][r1][r2] = Math.max(dp[t][r1][r2], all states pre)
 */
public class CherryPickUp {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int inf = (int)-1e9;

        int[][][] dp = new int[2*n - 1][n + 1][n + 1];
        for(int t = 0; t <= 2*n - 2; t ++) {
            for(int r1 = 0; r1 <= n; r1 ++) {
                Arrays.fill(dp[t][r1], inf);
            }
        }

        if(grid[0][0] == 0) {
            dp[0][1][1] = 0;
        } else if(grid[0][0] == 1) {
            dp[0][1][1] = 1;
        }

        int c1, c2;
        for(int t = 1; t <= 2*n - 2; t ++) {
            for(int r1 = Math.max(0, t + 1 - n); r1 <= Math.min(t, n - 1); r1 ++) {
                c1 = t - r1;
                for(int r2 = Math.max(0, t + 1 - n); r2 <= Math.min(t, n - 1); r2 ++) {
                    c2 = t - r2;
                    if(grid[r1][c1] == -1 || grid[r2][c2] == -1) {
                        continue;
                    }
                    int score;
                    if(grid[r1][c1] == 1 && grid[r2][c2] == 1) {
                        if(r1 == r2) {
                            score = 1;
                        } else {
                            score = 2;
                        }
                    } else if(grid[r1][c1] == 1 || grid[r2][c2] == 1) {
                        score = 1;
                    } else {
                        score = 0;
                    }

                    for(int trans1 = 0; trans1 < 2; trans1 ++) {
                        int oldR1 = trans1 == 0 ? r1 + 1 : r1;
                        for(int trans2 = 0; trans2 < 2; trans2 ++) {
                            int oldR2 = trans2 == 0 ? r2 + 1 : r2;
                            dp[t][r1 + 1][r2 + 1] = Math.max(
                                    dp[t][r1 + 1][r2 + 1],
                                    dp[t - 1][oldR1][oldR2] + score
                            );
                        }
                    }
                }
            }
        }

        return Math.max(dp[2 * n - 2][n][n], 0);
    }
}
