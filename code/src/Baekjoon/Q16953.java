package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q16953 {
    // https://www.acmicpc.net/problem/16953
    private static int ans = Integer.MAX_VALUE;
    private static Set<Long> visited = new HashSet<>();

    public static void dfs(long cur, long target, int cnt) {
        if (cur > target || visited.contains(cur)) {
            return;
        } else if (cur == target) {
            ans = Math.min(ans, cnt);
        }

        visited.add(cur);

        dfs(cur * 2, target, cnt + 1);
        dfs(cur * 10 + 1, target, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        dfs(A, B, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : (ans + 1));
    }
}
