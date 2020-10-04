package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Leetcode542: 01矩阵
 * - 思路:
 *  - 求某一个位置，离他最近的0，直接bfs
 *  - 求所有位置，对所有位置bfs，必然超时
 *  - 对所有位置bfs的情况进行剪枝:
 *      - 所有位置 bfs 到最后，必然都是回到所有 0 的位置
 *      - 只要从所有 0 的位置开始bfs，并对经过的位置做记录，对曾经经过的位置，直接pass即可；
 *      因为多次经过某个位置，他的最短路是最早出现的
 */
public class Leetcode542 {
    public int[][] updateMatrix(int[][] matrix) {
        int inf = (int)1e8;
        int m = matrix.length;
        if(m == 0) return new int[0][0];
        int n = matrix[0].length;
        final int[][] nexts = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        int[][] res = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < m; i ++) {
            for(int j = 0; j < n; j ++) {
                if(matrix[i][j] == 0) {
                    q.add(new int[]{i, j, 0});
                }
                res[i][j] = inf;
            }
        }

        while(!q.isEmpty()) {
            int[] cur = q.remove();
            res[cur[0]][cur[1]] = Math.min(res[cur[0]][cur[1]], cur[2]);
            for(int next = 0; next < 4; next ++) {
                int newX = cur[0] + nexts[next][0];
                int newY = cur[1] + nexts[next][1];
                if(newX >= 0 && newX < m && newY >= 0 && newY < n && res[newX][newY] == inf) {
                    q.add(new int[]{newX, newY, cur[2] + 1});
                }
            }
        }
        return res;
    }
}
