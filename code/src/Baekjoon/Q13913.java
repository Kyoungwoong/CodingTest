package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q13913 {
    // https://www.acmicpc.net/problem/13913
    private static int N, K;
    private static int[] visited = new int[100001];
    private static int[] parent = new int[100001];
    private static List<Integer> path = new ArrayList<>();

    public static void dfs(int cur) {
        // 현재 위치가 K라면 경로를 완성하고 반환
        if (cur == K) {
            int idx = cur;
            while (idx != N) { // 시작 위치(N)까지 경로를 역추적
                path.add(idx);
                idx = parent[idx];
            }
            path.add(N);
            return;
        }

        for (int next : new int[]{cur * 2, cur + 1, cur - 1}) {
            if (next < 0 || next > 100000 || visited[next] != 0) continue;
            if (cur > K) continue;
//            System.out.println("cur: " + cur + " next: " + next);
            visited[next] = visited[cur] + 1;
            parent[next] = cur;
            dfs(next);
            if (!path.isEmpty()) return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }

        visited[N] = 1;
        dfs(N);

        System.out.println(visited[K] - 1);

        Collections.reverse(path);
        for (int pos : path) {
            System.out.print(pos + " ");
        }
    }
}
