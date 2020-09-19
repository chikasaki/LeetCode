package dp;

import java.util.Arrays;

/**
 * LCP19: 秋叶收藏集
 *  - 思路: 经典动态规划，找状态 -> 状态转移
 *      - 状态如下:
 *          - 0: null
 *          - 1: rrr
 *          - 2: rrryy
 *          - 3: rrryyrrr
 *      - 状态转移:
 *          - 0: come r -> 1
 *               come y -> invalid
 *          - 1: come r -> 1
 *               come y -> 2
 *          - 2: come r -> 3
 *               come y -> 2
 *          - 3: come r -> 3
 *               come y -> invalid
 *      - 状态转移方程:
 *          - next[0] = inf(在有叶子的情况下，不可能为空)
 *          - next[1] = min(next[1] + c == 'r' ? 0 : 1, next[0] + c == 'r' ? 0 : 1)
 *          - next[2] = min(next[2] + c == 'y' ? 0 : 1, next[1] + c == 'y' ? 0 : 1)
 *          - next[3] = min(next[3] + c == 'r' ? 0 : 1, next[2] + c == 'r' ? 0 : 1)
 */
public class LeavesCollection {
    // 存在的初始状态初始化
    public int minimumOperations(String leaves) {
        int n = leaves.length();
        int inf = (int)1e9;

        int[] next = new int[4];
        next[0] = inf;
        next[1] = leaves.charAt(0) == 'r' ? 0 : 1;
        next[2] = inf;
        next[3] = inf;

        for(int i = 1; i < n; i ++) {
            char c = leaves.charAt(i);
            next[3] = Math.min(next[2] + cost(c, 'r'), next[3] + cost(c, 'r'));
            next[2] = Math.min(next[1] + cost(c, 'y'), next[2] + cost(c, 'y'));
            next[1] = next[1] + cost(c, 'r');
            next[0] = inf;
        }

        return next[3];
    }

    // 不存在的初始状态初始化
    public int minimumOperationsOther(String leaves) {
        int n = leaves.length();
        int inf = (int)1e9;

        int[] next = new int[4];
        Arrays.fill(next, inf);
        next[0] = 0;
        for(char c: leaves.toCharArray()) {
            next[3] = Math.min(next[2] + cost(c, 'r'), next[3] + cost(c, 'r'));
            next[2] = Math.min(next[1] + cost(c, 'y'), next[2] + cost(c, 'y'));
            next[1] = Math.min(next[0] + cost(c, 'r'), next[1] + cost(c, 'r'));
            next[0] = inf;
        }

        return next[3];
    }

    private int cost(char a, char b) {
        return a == b ? 0 : 1;
    }
}
