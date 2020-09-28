package tree.levelorder;

/**
 * Leetcode117: 填充每个节点的下一个右侧节点指针II
 * - 思路:
 *  - 使用BFS，空间复杂度O(n)
 *  - 使用三个指针:
 *      - pre: 上一层的头指针，并且会随着下一层指针的遍历而依次往后指
 *      - curHead: 本层头指针，用来指定下一次的pre
 *      - cur: 本层当前指针
 *      - 过程:
 *          每遍历一个上层指针，都看其左右节点是否为null，
 *          不为null则将当前层的当前指针指向该节点，并将cur向后移一位
 */
public class PopulatingNextRightPointersInEachNodeII {
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {

        }

        public Node(int _val) {
            val = _val;
        }
    }
    public Node connect(Node root) {
        if(root == null) return null;

        Node pre = root;
        Node curHead = null, cur = null;
        while(pre != null) {
            while(pre != null) {
                if(pre.left != null) {
                    if(curHead == null) {
                        curHead = pre.left;
                        cur = curHead;
                    } else {
                        cur.next = pre.left;
                        cur = cur.next;
                    }
                }
                if(pre.right != null) {
                    if(curHead == null) {
                        curHead = pre.right;
                        cur = curHead;
                    } else {
                        cur.next = pre.right;
                        cur = cur.next;
                    }
                }
                pre = pre.next;
            }
            pre = curHead;
            curHead = cur = null;
        }
        return root;
    }
}
