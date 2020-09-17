package graph;

import java.util.*;

/**
 * Leetcode 685
 * 两种做法:
 *  1. 暴力遍历每一条边，跑一下删除此边之后是否满足题意，时间复杂度 O(n^2)
 *  2. 官方题解，并查集 + parent数组
 *      - 两种情况:
 *          - 有入度为0的点: 此时必有一个点入度为2，即发生了冲突
 *          - 没有入度为0的点: 此时必有环，即肯定有边指向真正的根
 *      - 解决方案:
 *          - 发生冲突时，可能有环，也可能没有环；
 *              - 如果有环，那么删去的必是环上的边
 *              - 如果没有环，那两条边都可以删；按照题意删除最新的
 *          - 没有冲突时，环上任意一条边都可以删；按照题意删除最新的
 *
 *      - 关于此处使用并查集来判断有向图是否有环:
 *          - 一般情况下，是不能这么做的, 比如
 *              a ➡ b ➡ c
 *                 ⬇   ⬇
 *                d ➡ e
 *            图上没有环，但当遍历过 b-c, b-d, c-e 之后，再遍历 d-e 就会判断出此图有环
 *          - 此题因为其特殊性，保证了每个点的入度最多为2，并且在入度为2的情况下，
 *          因为冲突，我们可以不将两个点connect起来，避免了上面的特例
 *
 *          - 正是因为可以使用并查集，所以才能将环判断这一步时间复杂度降低，从而提高了算法的性能
 */
public class RendudantConnectionII {
    // 并查集 + parent数组
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;

        UnionFind uf = new UnionFind(n + 1);
        int[] parent = new int[n + 1];
        int conflict = -1, cycle = -1;

        Arrays.setAll(parent, i -> i);

        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            int v = edge[0], w = edge[1];

            if(parent[w] != w) {
                // 发生了冲突
                conflict = i;
            } else {
                parent[w] = v;
                // 没有发生冲突
                if(uf.isConnected(v, w)) {
                    // 检测出了环
                    cycle = i;
                } else {
                    uf.union(v, w);
                }
            }
        }

        if(conflict < 0) {
            return edges[cycle];
        } else {
            int[] edge = edges[conflict];
            if(cycle < 0) {
                return edge;
            } else {
                return new int[]{parent[edge[1]], edge[1]};
            }
        }
    }
    private class UnionFind {
        private int[] parent;
        private int[] size;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        private int find(int v) {
            if(parent[v] != v) {
                parent[v] = find(parent[v]);
            }
            return parent[v];
        }

        public boolean isConnected(int v, int w) {
            return find(v) == find(w);
        }

        public void union(int v, int w) {
            int vRoot = find(v);
            int wRoot = find(w);

            if(vRoot == wRoot) return;

            if (size[vRoot] < size[wRoot]) {
                parent[vRoot] = wRoot;
                size[wRoot] += size[vRoot];
            } else {
                parent[wRoot] = vRoot;
                size[vRoot] += size[wRoot];
            }
        }
    }

    // 暴力算法
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    private int[] indegree;
    private boolean[] inPath;
    private int n, count;
    public int[] findRedundantDirectedConnectionBF(int[][] edges) {
        int[] edge;
        int v, w;
        n = edges.length;

        indegree = new int[n + 1];
        inPath = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            edge = edges[i];
            v = edge[0];
            w = edge[1];

            indegree[w] ++;
            Set<Integer> set = g.get(v);
            if(set == null) {
                set = new HashSet<>();
                g.put(v, set);
            }
            set.add(w);
        }

        for (int i = n - 1; i >= 0; i--) {
            edge = edges[i];
            v = edge[0];
            w = edge[1];

            indegree[w] --;
            int root = getRoot();
            if(root == -1) {
                indegree[w] ++;
                continue;
            }

            Set<Integer> set = g.get(v);
            set.remove(w);
            count = 0;
            if(dfs(root) && count == n) {
                return edge;
            }

            indegree[w] ++;
            set.add(w);
            Arrays.fill(inPath, false);
        }
        return null;
    }
    // 遍历判断是否有环
    private boolean dfs(int v) {
        inPath[v] = true;
        count ++;

        Set<Integer> set = g.get(v);
        if(set == null) return true;
        for(int w: set) {
            if(inPath[w] || !dfs(w)) return false;
        }

        inPath[v] = false;
        return true;
    }
    //获取当前状态可能的根
    private int getRoot() {
        int root = -1;
        for (int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                if(root != -1) {
                    root = -1;
                    break;
                }
                root = i;
            }
        }
        return root;
    }
}
