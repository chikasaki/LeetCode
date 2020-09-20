package bruteforcebacktrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 39: 组合总和
 * 跟Leetcode 47相似，为了防止重复，一个数字用过就不能再用；
 * 由于此题数组元素不重复，所以只要考虑一个元素使用多少次，之后再也不用它就行了
 */
public class CombinationSum {
    private int[] candidates;
    private int n;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.candidates = candidates;
        this.n = candidates.length;

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

        int cur = target;
        while(cur >= 0) {
            List<List<Integer>> nextRes = dfs(index + 1, cur, path);
            res.addAll(nextRes);
            cur -= candidates[index];
            path.add(candidates[index]);
        }
        while(cur != target) {
            cur += candidates[index];
            path.remove(path.size() - 1);
        }
        return res;
    }
}
