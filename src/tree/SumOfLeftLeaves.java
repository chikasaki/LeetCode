package tree;

/**
 * Leetcode404: 左叶子之和
 * - 常规的树形dp
 * - 要求左叶子，只要对每次遍历方向做记录即可
 */
public class SumOfLeftLeaves {
    private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        dfs(root, false);
        return sum;
    }

    private void dfs(TreeNode node, boolean left) {
        if(node == null) return;

        if(node.left == null && node.right == null && left) {
            sum += node.val;
        }

        dfs(node.left, true);
        dfs(node.right, false);
    }
}
