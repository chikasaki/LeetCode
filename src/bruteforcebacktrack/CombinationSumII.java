package bruteforcebacktrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 40: 组合总和II
 * 跟Leetcode39: 组合总和 很相似，思路都是一致的，额外再维护使用的计数器而已
 */
public class CombinationSumII {
    private int[] candidates;
    private int n;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.candidates = candidates;
        this.n = candidates.length;

        Arrays.sort(candidates);

        return dfs(0, target, new ArrayList<>());
    }

    private List<List<Integer>> dfs(int index, int target, List<Integer> path) {
        List<List<Integer>> res = new ArrayList<>();
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return res;
        }
        if(index == n) {
            return res;
        }

        int val = candidates[index];
        int nextIdx = index;
        while(nextIdx < n && candidates[nextIdx] == val) {
            nextIdx ++;
        }

        List<List<Integer>> nextRes = dfs(nextIdx, target, path);
        res.addAll(nextRes);

        int cur = target;
        for(int i = index; i < nextIdx && cur >= 0; i ++) {
            cur -= val;
            path.add(val);
            res.addAll(dfs(nextIdx, cur, path));
        }
        while(cur != target) {
            cur += val;
            path.remove(path.size() - 1);
        }

        return res;
    }
}
