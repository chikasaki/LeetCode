package tree.levelorder;

import tree.TreeNode;

/**
 * Leetcode5532:
 * - 传统的bfs队列写法这里就不写了；换一种使用 next 指针的bfs写法；
 * 参考Leetcode117
 */
public class EvenOddTree {
    private class Node {
        TreeNode node;
        Node next;
        public Node(TreeNode node) {
            this.node = node;
        }
    }
    public boolean isEvenOddTree(TreeNode root) {
        Node pre = new Node(root);
        int depth = 0;
        while(pre != null) {
            Node cur = null, curHead = null;
            int preVal = depth % 2 == 0 ? 0 : (int)1e7;
            while(pre != null) {
                if((pre.node.val + depth) % 2 == 0) return false;
                if(depth % 2 == 0 && pre.node.val <= preVal) return false;
                if(depth % 2 != 0 && pre.node.val >= preVal) return false;
                preVal = pre.node.val;
                if(pre.node.left != null) {
                    if(curHead == null) {
                        curHead = new Node(pre.node.left);
                        cur = curHead;
                    } else {
                        cur.next = new Node(pre.node.left);
                        cur = cur.next;
                    }
                }
                if(pre.node.right != null) {
                    if(curHead == null) {
                        curHead = new Node(pre.node.right);
                        cur = curHead;
                    } else {
                        cur.next = new Node(pre.node.right);
                        cur = cur.next;
                    }
                }
                pre = pre.next;
            }
            depth ++;
            pre = curHead;
        }
        return true;
    }
}
