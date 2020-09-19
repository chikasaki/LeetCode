package combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode216: 组合总和III
 * - 思路: 与Leetcode39: 组合总和 思路基本一致，维护一个额外的计数器k而已
 * 这里采用另外一种代码编写方法，而不用在函数传递过程中一直加个list引用
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        return dfs(k, n, 1);
    }

    private List<List<Integer>> dfs(int k, int n, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if(n == 0 && k == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        if(k == 0 || start > 9) {
            return res;
        }

        List<List<Integer>> have = dfs(k - 1, n - start, start + 1);
        List<List<Integer>> nothave = dfs(k, n, start + 1);

        for(List<Integer> list: have) {
            list.add(start);
            res.add(list);
        }
        res.addAll(nothave);
        return res;
    }
}
