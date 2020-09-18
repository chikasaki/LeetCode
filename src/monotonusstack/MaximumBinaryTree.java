package monotonusstack;

import java.util.Stack;

/**
 * Leetcode654: 最大二叉树
 * 三种思路:
 *  - 找最大值时使用暴力的O(n^2)
 *  - 找最大值时使用线段树O(nlogn)
 *  - 直接使用单调栈O(n)解决问题
 * 这里主要写二，三两种思路
 *  - 线段树: 就是很普通的建树找最大值
 *  - 单调栈: 根据题目描述，取最大，然后分治
 *      - 对于当前遍历到的点，找到他左子树的点，再找到他右子树的点
 *      - 在左边比当前val大的最近元素与当前元素之间的所有Node，都是当前遍历到点的左子树的组成部分
 *      - 在右边比当前val大的最近元素与当前元素之间的所有Node，都是当前遍历到点的右子树的组成部分
 *      - 综合上面的分析与在Leetcode84: 最大矩形面积 中对单调栈使用场景的分析，此题可以使用单调栈求解
 */
public class MaximumBinaryTree {
    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    //单调栈
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        for (int i = 0; i < n; i++) {
            TreeNode node = new TreeNode(nums[i]);
            pre = null;
            while(!stack.isEmpty() && stack.peek().val < nums[i]) {
                stack.peek().right = pre;
                pre = stack.pop();
            }
            node.left = pre;
            stack.push(node);
        }

        pre = null;
        while(!stack.isEmpty()) {
            stack.peek().right = pre;
            pre = stack.pop();
        }
        return pre;
    }

    //线段树
    private SegTree segTree;
    private int[] nums;
    public TreeNode constructMaximumBinaryTreeSeg(int[] nums) {
        segTree = new SegTree(nums);
        this.nums = nums;
        // System.out.println(segTree.query(1, 2));

        return construct(0, nums.length - 1);
    }

    private TreeNode construct(int l, int r) {
        if(l > r) return null;
        if(l == r) return new TreeNode(nums[segTree.query(l, r)]);

        int maxIndex = segTree.query(l, r);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = construct(l, maxIndex - 1);
        node.right = construct(maxIndex + 1, r);
        return node;
    }

    private class SegTree {
        private int []nums;
        private Node root;
        public SegTree(int[] nums) {
            this.nums = nums;
            root = new Node(0, nums.length - 1);
            construct(root);
        }

        private void construct(Node node) {
            if(node.l == node.r) {
                node.valIndex = node.l;
                return;
            }

            int mid = node.l + (node.r - node.l) / 2;
            node.left = new Node(node.l, mid);
            node.right = new Node(mid + 1, node.r);
            construct(node.left);
            construct(node.right);

            int leftVal = nums[node.left.valIndex];
            int rightVal = nums[node.right.valIndex];
            node.valIndex = leftVal > rightVal ? node.left.valIndex : node.right.valIndex;
        }

        public int query(int l, int r) {
            return query(root, l, r);
        }

        private int query(Node node, int l, int r) {
            if(node.l == l && node.r == r) {
                return node.valIndex;
            }

            int mid = node.l + (node.r - node.l) / 2;
            if(r <= mid) {
                return query(node.left, l, r);
            } else if(l > mid) {
                return query(node.right, l, r);
            } else {
                int leftIdx = query(node.left, l, mid);
                int rightIdx = query(node.right, mid + 1, r);
                return nums[leftIdx] > nums[rightIdx] ? leftIdx : rightIdx;
            }
        }

        private class Node {
            int l, r;
            int valIndex;
            Node left, right;

            public Node(int l, int r) {
                this.l = l;
                this.r = r;
            }
        }
    }
}
