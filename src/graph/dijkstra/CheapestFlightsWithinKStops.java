package graph.dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Leetcode787: K站中转内最便宜的航班
 * - 两种思路，本包只书写dijkstra，dp做法请查看dp包
 *  - 一开始我的思路是:
 *      - 使用数组 dp 来记录源点到其他点的最短路径，数组 time 来记录从源点到其他点的中转次数
 *          如果中转次数过大，就continue掉此次循环
 *      - 这样操作带来的问题: 中转次数很多，但是长度更短的路径，会把中转次数很少，但是长度更长的路径覆盖掉；
 *                          从而可能找不到结果，但是实际是存在结果的，比如下面这种情况:
 *                      [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
 *  - 正确的思路:
 *      不需要实时维护 time 数组，只要把中转信息放入优先队列的pair就可以了
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int inf = (int)1e8;

        int[][] dist = new int[n][n];
        for(int[] flight: flights) {
            dist[flight[0]][flight[1]] = flight[2];
        }

        int[][] dp = new int[n][K + 3];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], inf);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> a[2] - b[2]
        );
        pq.offer(new int[]{src, 0, 0});

        while(!pq.isEmpty()) {
            int[] info = pq.poll();
            int cur = info[0], k = info[1], d = info[2];

            if(k > K + 1 || d > dp[cur][k]) continue;
            if(cur == dst) return d;

            for(int i = 0; i < n; i ++) {
                if(dist[cur][i] != 0 && dp[i][k + 1] > d + dist[cur][i]) {
                    pq.offer(new int[]{i, k + 1, d + dist[cur][i]});
                    dp[i][k + 1] = d + dist[cur][i];
                }
            }
        }

        return -1;
    }
}
