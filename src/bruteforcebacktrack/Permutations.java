package bruteforcebacktrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 46全排列
 * 采取思路: 数组中的某一位可以出现在排列完后的数组中的任意一位，使用visited数组标识某一位是否被用过；
 * 采用回溯实现
 */
public class Permutations {
    private boolean[] visited;
    private int[] nums;
    private int n;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        visited = new boolean[n];

        return dfs(0);
    }

    private List<List<Integer>> dfs(int depth) {
        List<List<Integer>> res = new ArrayList<>();
        if(depth == n) {
            res.add(new ArrayList<>());
            return res;
        }

        for(int i = 0; i < n; i ++) {
            if(!visited[i]) {
                visited[i] = true;
                List<List<Integer>> nextRes = dfs(depth + 1);
                for(List<Integer> list: nextRes) {
                    list.add(nums[i]);
                    res.add(list);
                }
                visited[i] = false;
            }
        }
        return res;
    }
}
