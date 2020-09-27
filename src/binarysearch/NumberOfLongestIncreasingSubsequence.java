package binarysearch;

import java.util.Map;
import java.util.TreeMap;

/**
 * Leetcode 673: 最长递增子序列的个数
 * - 思路:
 *  - 模仿Leetcode300: 使用ends数组
 *      - 考虑每一个元素，可能有多个以该元素为最后一个元素的最长递增子序列
 *      - 所以对于各个长度的最长递增子序列，需要同时记录尾部是谁，长度有多长
 *      - 当一个新元素 nums[i] 过来时，他可以接在 ends[j] 后，这时候有以下两种情况:
 *          - ends[j]中所有最长递增子序列，他们尾部的元素都比 nums[i] 小
 *          - ends[j]中所有最长递增子序列，可能有一部分尾部的元素比 nums[i] 大
 *          - 使用 TreeMap 可以在 O(logn)时间复杂度内找到比 nums[i] 大的部分的边界
 *      - ends[i][j][k] 表示 长度为 i + 1 的递增子序列中，结尾元素大于等于 j 的子序列有 k 个
 *  - 线段树(在线段树包中查找)
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        TreeMap<Integer, Integer>[] ends = new TreeMap[n];
        int maxL = 0;

        ends[0] = new TreeMap<>();
        ends[0].put(nums[0], 1);
        maxL = 1;
        for(int i = 1; i < n; i ++) {
            if(nums[i] > ends[maxL - 1].firstEntry().getKey()) {
                ends[maxL] = new TreeMap<>();
                ends[maxL].put(nums[i], getPre(ends, maxL, nums[i]));
                maxL ++;
                continue;
            }

            int lo = 0, hi = maxL - 1;
            while(lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if(nums[i] > ends[mid].firstEntry().getKey()) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            int bottomeValue = ends[lo].firstEntry().getValue();
            ends[lo].put(nums[i], bottomeValue + getPre(ends, lo, nums[i]));
        }
        return ends[maxL - 1].firstEntry().getValue();
    }

    private Integer getPre(TreeMap<Integer, Integer>[] ends, int index, int num) {
        int pre;
        if(index == 0) {
            pre = 1;
        } else {
            int a1 = ends[index - 1].firstEntry().getValue();
            Map.Entry<Integer, Integer> entry = ends[index - 1].ceilingEntry(num);
            int a2;
            if(entry == null) {
                a2 = 0;
            } else {
                a2 = entry.getValue();
            }

            pre = a1 - a2;
        }
        return pre;
    }
}
