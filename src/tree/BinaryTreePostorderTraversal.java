package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Leetcode145: 二叉树的后序遍历
 * - 迭代思路:
 *  - 二叉树后序遍历的顺序时 左-右-中
 *  - 每个节点被遍历的时候，前一个节点必然是null或者它的右节点
 *  - 所以只需要在遍历之前判断前一个节点是不是右节点就行了
 */
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre = null; //pre 记录前一个被遍历的节点
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.peek();
            if(cur.right == null || cur.right == pre) { //在这里，每个节点会被遍历到
                res.add(cur.val);
                pre = cur;
                cur = null;
                stack.pop();
            } else {
                cur = cur.right;
            }
        }
        return res;
    }
}
