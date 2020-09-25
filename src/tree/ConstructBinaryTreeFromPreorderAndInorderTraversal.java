package tree;

/**
 * Leetcode105: 前序+中序 序列构造二叉树
 * - 思路:
 *  - 前序序列中的第一个数是当前树根节点的数
 *  - 在中序序列中找到上面说的那个数
 *  - 中序序列中找到相应数之后，左边部分是左子树，右边部分是右子树
 *  - 前序序列中，左子树的部分是连续的，右子树部分也是连续的
 *  - 因为两部分左子树和右子树的长度是一样的，可以通过中序序列找到前序序列中左子树的部分和右子树的部分
 *  - 递归建树
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private int[] pre, in;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.pre = preorder;
        this.in = inorder;

        return build(0, pre.length - 1, 0, in.length - 1);
    }

    private TreeNode build(int ps, int pe, int is, int ie) {
        if(ps > pe) return null;

        TreeNode node = new TreeNode(pre[ps]);
        int s = is;
        while(s <= ie && in[s] != node.val) {
            s ++;
        }
        node.left = build(ps + 1, ps + s - is, is, s - 1);
        node.right = build(ps + s - is + 1, pe, s + 1, ie);
        return node;
    }
}
