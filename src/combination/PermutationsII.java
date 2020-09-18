package combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Leetcode 47: 全排列II
 * 思路: 与Leetcode46相仿，但是要去除可能的重复
 *
 * 可能会产生重复的原因: 1(a) 1(b) 1(c) -> 1(b) 1(a) 1(c), 其实就是其中两个或多个可能交换了顺序；
 * 保证一个固定的相对顺序不变就可以了
 *
 * 去除重复思路: 数组中一样的元素，只要不改变他们在数组中的相对顺序，再列举出所有情况即可
 */
public class PermutationsII {
    private int n;
    private int[] nums;
    private boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        n = nums.length;
        this.nums = nums;
        Arrays.sort(nums);

        visited = new boolean[n];

        return dfs(0);
    }

    private List<List<Integer>> dfs(int index) {
        List<List<Integer>> res = new ArrayList<>();
        if(index == n) {
            res.add(new ArrayList<>());
            return res;
        }

        for(int i = 0; i < n; i ++) {
            if(!visited[i]) {
                if(i != n - 1 && !visited[i + 1] && nums[i] == nums[i + 1]) continue;

                visited[i] = true;
                List<List<Integer>> nextRes = dfs(index + 1);
                for(List<Integer> list: nextRes) {
                    list.add(nums[i]);
                    res.add(list);
                }
                visited[i] = false;
            }
        }
        return res;
    }
}
