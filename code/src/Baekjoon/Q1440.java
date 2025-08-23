package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1440 {
    static int ans = 0;
    static int[] time;
    static Set<Integer> alreadyCheck = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ":");
        time = new int[3];
        for (int i = 0; i < 3; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, new ArrayList<>(), new boolean[3]);

        System.out.println(ans);
    }

    private static void dfs(int pos, int depth, List<Integer> list, boolean[] visited) {
        if (depth == 3) {
            if (canCount(list)) {
                ans++;
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(i);

            dfs(i + 1, depth + 1, list, visited);

            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    private static boolean canCount(List<Integer> order) {
        int hour = time[order.get(0)];
        if (hour < 1 || hour > 12) return false;   // 01~12

        int minute = time[order.get(1)];
        if (minute < 0 || minute >= 60) return false; // 00~59

        int second = time[order.get(2)];
        if (second < 0 || second >= 60) return false; // 00~59

        // 같은 값이라도 위치가 다르면 다른 방법으로 센다 → 중복 제거 안 함
        return true;
    }
}
