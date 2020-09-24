package dp;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Leetcode787: K站中转内最便宜的航班
 * - 两种思路，本包只书写dp, dijstra请查看 graph.dijstra
 *  - 状态定义:
 *      dp[i][k]: 从src出发到 i，至多经过k次中转的最少花费
 *  - 状态转移:
 *      dp[i][k] = Math.min(dp[j][k - 1] + dist[j][i], dp[i][k])
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int inf = (int)1e8;

        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i ++) {
            Arrays.fill(dist[i], inf);
            dist[i][i] = 0;
        }
        for(int[] flight: flights) {
            dist[flight[0]][flight[1]] = flight[2];
        }

        int[][] dp = new int[n][K + 1];
        for(int i = 0; i < n; i ++) {
            Arrays.fill(dp[i], inf);
            dp[i][0] = dist[src][i];
        }

        for(int k = 1; k <= K; k ++) {
            for(int i = 0; i < n; i ++) {
                for(int j = 0; j < n; j ++){
                    dp[i][k] = Math.min(
                            dp[i][k],
                            dp[j][k - 1] + dist[j][i]
                    );
                }
            }
        }
        return dp[dst][K] == inf ? -1 : dp[dst][K];
    }
}
