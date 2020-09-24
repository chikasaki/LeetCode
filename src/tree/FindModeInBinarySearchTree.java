package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode501: 二叉树中的众数
 * - 三种思路:
 *  - 使用map记录每个元素出现的次数，最后再遍历map
 *  - 到达某个节点之后，找到所有与该节点值相同的节点，并记录出现的次数；时间复杂度: O(nlogn)
 *  - Morris中序遍历，时间复杂度: O(n), 空间复杂度: O(1)
 * - 这里只书写 Morris中序遍历
 */
public class FindModeInBinarySearchTree {
    public int[] findMode(TreeNode root) {
        if(root == null) return new int[0];

        List<Integer> resList = new ArrayList<>();
        int count, preVal, maxCount;

        TreeNode cur = root;
        count = 0; preVal = cur.val; maxCount = 0;
        while(cur != null) {
            TreeNode pre = cur.left;

            if(pre != null) {
                while(pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                pre.right = pre.right == null ? cur : null;
            }

            if(pre == null || pre.right == null) {
                if(preVal == cur.val) {
                    count ++;
                } else {
                    preVal = cur.val;
                    count = 1;
                }
                if(count >= maxCount) {
                    if(count > maxCount) {
                        resList.clear();
                        maxCount = count;
                    }
                    resList.add(cur.val);
                }

                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        int[] res = new int[resList.size()];
        int index = 0;
        for(int num: resList) res[index ++] = num;
        return res;
    }
}
