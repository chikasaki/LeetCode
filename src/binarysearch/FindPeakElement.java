package binarysearch;

/**
 * Leetcode162: 寻找峰值
 * - 思路:
 *  - 考虑极端情况: 如果整个数组本身就是有序的
 *      - 单调递增: 最后一个元素就是answer
 *      - 单调递减: 第一个元素就是answer
 *  - 假装整个数组是有序的，尝试使用二分(为什么使用二分？其实我也没想清楚，但题目都这么明示了，就尝试一下):
 *      - 考虑中间元素
 *      - if nums[mid] < nums[mid + 1]:
 *          - 那么 mid 这个元素必然不可能是answer
 *          - [mid + 1, end] 这一段必然存在answer
 *      - if nums[mid] > nums[mid + 1]:
 *          - 那么 mid 这个元素可能是answer
 *          - [start, mid] 这一段必然存在answer
 *      - if nums[mid] == nums[mid + 1]: 题目说了整个数组中不存在重复元素
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        int inf = Integer.MIN_VALUE;

        int lo = 0, hi = n - 1;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if(nums[mid] < (mid + 1 < n ? nums[mid + 1] : inf)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return hi;
    }
}
