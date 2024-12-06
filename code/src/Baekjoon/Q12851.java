package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q12851 {
    // https://www.acmicpc.net/problem/12851
    private static int N, K;
    private static int[] visited = new int[100001];

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = 1;

        int ans = Integer.MAX_VALUE;
        int cnt = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == K) {
                ans = visited[cur] - 1;
                cnt++;
                continue;
            }

            for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                if (next < 0 || next > 100000) continue;

                if (visited[next] == 0 || visited[next] == visited[cur] + 1) {
                    visited[next] = visited[cur] + 1;
                    queue.add(next);
                }
            }
        }

        System.out.println(ans);
        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();
    }
}
