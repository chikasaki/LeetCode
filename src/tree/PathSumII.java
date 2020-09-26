package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode113: 路径总和II
 * - 思路： 回溯， 时间复杂度O(n)
 */
public class PathSumII {
    private List<List<Integer>> res = new ArrayList<>();
    private int sum = 0;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        this.sum = sum;

        dfs(root, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> path, int cur) {
        if(node == null) return;

        cur += node.val;
        path.add(node.val);
        if(node.left == null && node.right == null) {
            if(sum == cur) {
                res.add(new ArrayList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }

        dfs(node.left, path, cur);
        dfs(node.right, path, cur);
        path.remove(path.size() - 1);
    }
}
