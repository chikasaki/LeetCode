package binarysearch;

/**
 * Leetcode300: 最长上升子序列
 * 思路:
 *  - 动态规划
 *  - 贪心 + 二分
 *      - ends数组: ends[i]表示长度为 i + 1 的子序列中，到目前为止最后一位最小的值
 *      - ends数组必然是有序的，新来一个元素的时候，可以使用二分找到当前元素应该插入的位置
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;

        int[] ends = new int[n];
        int maxL = 0;

        ends[0] = nums[0]; maxL = 1;
        for(int i = 1; i < n; i ++) {
            if(nums[i] > ends[maxL - 1]) {
                ends[maxL] = nums[i];
                maxL ++;
                continue;
            }

            int lo = 0, hi = maxL - 1;
            while(lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if(nums[i] > ends[mid]) lo = mid + 1;
                else hi = mid;
            }
            ends[lo] = nums[i];
        }
        return maxL;
    }
}
