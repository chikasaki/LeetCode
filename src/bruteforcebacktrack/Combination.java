package bruteforcebacktrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 77: 组合
 * 思路: 与排列思路不太一样，组合不管一个集合的排列顺序如何，所以 [1, 2] 和 [2, 1] 算重复
 * 与Leetcode 47: 全排列II 相同，产生重复的原因还是因为相对顺序发生了变化，所以保持相对顺序不变即可
 */
public class Combination {
    private int n, k;
    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;

        List<Integer> path = new ArrayList<>();
        return dfs(1, path);
    }

    private List<List<Integer>> dfs(int index, List<Integer> path) {
        List<List<Integer>> res = new ArrayList<>();
        if(path.size() == k) {
            res.add(new ArrayList<>(path));
            return res;
        }
        if(index > n) return res;

        List<List<Integer>> next1 = dfs(index + 1, path);
        path.add(index);
        List<List<Integer>> next2 = dfs(index + 1, path);
        path.remove(path.size() - 1);

        res.addAll(next1);
        res.addAll(next2);
        return res;
    }
}
