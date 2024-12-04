package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2606 {
    // https://www.acmicpc.net/problem/2606
    private static List<List<Integer>> networks = new ArrayList<>();
    private static boolean[] visited;
    private static int N, ans = 0;

    public static void bfs(int start) {
        Queue<Integer> virus = new LinkedList<>();
        visited[start] = true;
        virus.add(start);

        while (!virus.isEmpty()) {
            int infection = virus.poll();
            for (int num : networks.get(infection)) {
                if (!visited[num]) {
                    ans++;
                    visited[num] = true;
                    virus.add(num);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            networks.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            networks.get(src).add(desc);
            networks.get(desc).add(src);
        }

        bfs(1);

        System.out.println(ans);
    }
}
