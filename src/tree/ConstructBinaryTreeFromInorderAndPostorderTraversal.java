package tree;

/**
 * Leetcode106: 中序+后序 遍历序列构造二叉树
 * - 思路: 和leetcode105的思路是一样的，只不过后序序列的最后一个数是当前树的根节点
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    private int[] inorder, postorder;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;

        return build(0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build(int is, int ie, int ps, int pe) {
        if(is > ie) return null;
        if(is == ie) {
            return new TreeNode(inorder[is]);
        }

        TreeNode node = new TreeNode(postorder[pe]);
        int s = is;
        while(s <= ie && inorder[s] != node.val) {
            s ++;
        }

        node.left = build(is, s - 1, ps, ps + s - is - 1);
        node.right = build(s + 1, ie, ps + s - is, pe - 1);
        return node;
    }
}
