package dp.statecompression;

import java.util.*;

/**
 * Leetcode1125: 最小的必要团队
 * - 思路:
 *  - 考虑当前有 i 个人，已经知道了对这i个人，可以完成所有任务组合的最小团队
 *  - 当第 i + 1 个人进来时，所有任务组合的最小团队都有两种情况:
 *      - 包含第 i + 1 个人
 *      - 不包含第 i + 1 个人
 *  - 由于此题中任务列表较短，只有16个，可以考虑使用状压dp:
 *      - 假设有 2 个任务，则所有任务总共有以下四种组合:
 *          00, 01, 10, 11
 *          分别代表: 没有任务，有第二个任务，有第一个任务，有两个任务
 *      - 则包含第 i + 1 个人之后，剩下的任务列表为 j & (~p[i + 1]),
 *          其中 j 为当前需要完成的任务列表，p[i + 1]为第 i + 1 个人可以完成的任务列表
 *
 */
public class SmallestSufficientTeam {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int m = req_skills.length, n = people.size();
        Map<String, Integer> map = new HashMap<>();
        int[] p = new int[n];

        for(int i = 0; i < m; i ++) {
            map.put(req_skills[i], i);
        }
        for(int i = 0; i < n; i ++) {
            List<String> list = people.get(i);
            for (String str : list) {
                if(map.containsKey(str)) {
                    p[i] |= 1 << map.get(str);
                }
            }
        }

        int req = 1 << m;
        List<Integer>[] dp = new List[req];
        List<Integer> initialList = Arrays.asList(new Integer[n + 1]);
        for(int i = 1; i < req; i ++) {
            dp[i] = initialList;
        }
        dp[0] = new ArrayList<>();

        for(int i = 0; i < n; i ++) {
            for(int j = 0; j < req; j ++) {
                if(dp[j & (~p[i])].size() + 1 < dp[j].size()) {
                    dp[j] = new ArrayList<>(dp[j & (~p[i])]);
                    dp[j].add(i);
                }
            }
        }

        int[] res = new int[dp[req - 1].size()];
        int index = 0;
        for(int num: dp[req - 1]) res[index ++] = num;
        return res;
    }
}
