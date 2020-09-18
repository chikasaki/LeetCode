package deque;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Leetcode950: 按递增顺序显示卡牌
 * 两种思路:
 *  - 总体思路: 模拟题目流程进行
 *  - 使用数组模拟，时间复杂度O(nlogn)，每次都去找下下个可以放的位置；
 *     因为遍历一遍数组可以补全数组的一半元素，因此走 logn 次数组就完成了数组所有位置的填空
 *  - 使用双端队列可以方便地模拟，快速地将头部元素放到尾部，时间复杂度O(n)
 */
public class RevealCardsInIncreasingOrder {
    //双端队列
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] res = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();

        Arrays.sort(deck);

        for (int i = 0; i < n; i++) {
            dq.addLast(i);
        }

        int index = 0;
        while(true) {
            res[dq.removeFirst()] = deck[index ++];
            if(index == n) break;
            dq.addLast(dq.removeFirst());
        }
        return res;
    }

    //使用数组模拟
    public int[] deckRevealedIncreasingArr(int[] deck) {
        int n = deck.length;
        int[] res = new int[n];

        Arrays.sort(deck);

        int cur = 0, index = 0;
        while(true) {
            res[cur] = deck[index ++];
            if(index == n) break;

            int next = (cur + 1) % n;
            boolean flag = false;
            while(true) {
                if(res[next] == 0) { //题目给定deck所有元素均为正数
                    if(!flag) flag = true;
                    else break;
                }
                next = (next + 1) % n;
            }
            cur = next;
        }
        return res;
    }
}
