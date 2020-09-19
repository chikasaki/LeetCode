package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode257: 二叉树中的所有路径
 * - 思路: 非常常规的树形dp思路
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null) return res;
        if(root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }

        final String prefix = root.val + "->";
        List<String> leftRes = binaryTreePaths(root.left);
        List<String> rightRes = binaryTreePaths(root.right);
        for(String str: leftRes) res.add(prefix + str);
        for(String str: rightRes) res.add(prefix + str);

        return res;
    }
}
