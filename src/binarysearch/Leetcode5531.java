package binarysearch;

/**
 * Leetcode5531: 特殊数组的特征值
 * - 二分思路:
 *  - 假设 nums 是一个特殊数组
 *      - 已知nums中数的范围为 [0, x]
 *      - 假设一个数 t，随着 t 增大，数组nums中大于等于 t的数的个数count会递减
 *          即 count 为关于 t 的单调递减函数
 *      - lo = 0, hi = x;目标是 mid == count
 *          - mid > count: 说明 mid 太大了，要减小，hi = mid
 *          - mid < count: 说明 mid 太小了，要增大，lo = lo + 1
 *          - mid == count: 找到答案
 */
public class Leetcode5531 {
    public int specialArray(int[] nums) {
        int n = nums.length;

        int lo = 0, hi = 1000;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            for(int i = 0; i < n; i ++) {
                if(nums[i] >= mid) count ++;
            }
            if(count > mid) lo = mid + 1;
            else if(count < mid) hi = mid;
            else if(count == mid) return mid;
        }
        return -1;
    }
}
