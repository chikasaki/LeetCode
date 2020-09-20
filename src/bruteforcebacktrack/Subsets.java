package bruteforcebacktrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode78: 子集
 *  - 暴力枚举出所有 2^n 个子集即可
 */
public class Subsets {
    private int[] nums;
    private int n;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        this.n = nums.length;

        return dfs(0, new ArrayList<>());
    }

    private List<List<Integer>> dfs(int start, List<Integer> path) {
        List<List<Integer>> res = new ArrayList<>();
        if(start == n) {
            res.add(new ArrayList<>(path));
            return res;
        }

        List<List<Integer>> nothave = dfs(start + 1, path);
        path.add(nums[start]);
        List<List<Integer>> have = dfs(start + 1, path);
        path.remove(path.size() - 1);

        res.addAll(nothave);
        res.addAll(have);
        return res;
    }
}
