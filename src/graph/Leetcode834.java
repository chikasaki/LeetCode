package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode834: 树中距离之和
 * - 思路:
 *  - 暴力dfs, O(n^2)
 *      - 对每个点，都以它作为根，进行N次dfs
 *  - 暴力dfs基础上，进行剪枝
 *      - 考虑点 u，一开始以它作为根，只需要求出他到所有点的距离之和
 *          - 可以让无向变成有向，整棵树变成从根到叶的有向树
 *          - dp数组: 以 x 为根，他到子树所有点距离之和
 *          - sz数组: 以 x 为根，整颗树的大小
 *      - 求出 res[u] 之后，对于 u 的相邻节点 v， 是否可以通过一定步骤，O(1)操作直接换根?
 *          - 观察后发现，dp数组和sz数组在交换过程中，只需要更改 u 和 v 的值，这是一个O(1)操作
 *              - dp[u] -= (dp[v] + sz[v])
 *              - sz[u] -= sz[u]
 *              - dp[v] += (dp[u] + sz[u])
 *              - sz[v] += sz[u]
 *      - 换根操作使用 dfs 回溯即可
 *
 */
public class Leetcode834 {
    private final static int inf = (int)1e8;
    private int root;
    private int[] dp, sz, res;
    private List<Integer>[] nexts;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        root = 0;
        dp = new int[N]; sz = new int[N]; res = new int[N];
        nexts = new List[N];
        Arrays.fill(dp, inf); Arrays.fill(sz, 1); Arrays.fill(res, inf);
        for(int i = 0; i < N; i ++) {
            nexts[i] = new ArrayList<>();
        }
        for(int[] edge: edges) {
            nexts[edge[0]].add(edge[1]);
            nexts[edge[1]].add(edge[0]);
        }

        dfs(root);
        res[root] = dp[root];
        dfsSwapRoot(root);
        return res;
    }

    private void dfs(int cur) {
        dp[cur] = 0;
        for(int next: nexts[cur]) {
            if(dp[next] != inf) continue;
            dfs(next);
            dp[cur] += (dp[next] + sz[next]);
            sz[cur] += sz[next];
        }
    }

    // dfs回溯
    private void dfsSwapRoot(int cur) {
        for(int next: nexts[cur]) {
            if(res[next] != inf) continue;
            swapRoot(cur, next);
            res[next] = dp[next];
            dfsSwapRoot(next);
            swapRoot(next, cur);
        }
    }

    private void swapRoot(int root, int u) {
        dp[root] -= (dp[u] + sz[u]);
        sz[root] -= sz[u];
        dp[u] += (dp[root] + sz[root]);
        sz[u] += sz[root];
    }
}
