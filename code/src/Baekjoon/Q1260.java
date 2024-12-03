package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1260 {
    // https://www.acmicpc.net/problem/1260
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static List<Integer> dfsList = new ArrayList<>();
    private static List<Integer> bfsList = new ArrayList<>();

    public static void dfs(int start) {
        visited[start] = true;
        dfsList.add(start);

        for (int desc : graph.get(start)) {
            if (!visited[desc]) {
                dfs(desc);
            }
        }
    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            bfsList.add(cur);

            for (int desc : graph.get(cur)) {
                if (!visited[desc]) {
                    visited[desc] = true;
                    q.add(desc);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            graph.get(src).add(desc);
            graph.get(desc).add(src);
        }

        // 그래프 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        visited = new boolean[N + 1];
        dfs(V);

        visited = new boolean[N + 1];
        bfs(V);

        StringBuilder sb = new StringBuilder();
        for (int num : dfsList) {
            sb.append(num).append(" ");
        }
        sb.append("\n");
        for (int num : bfsList) {
            sb.append(num).append(" ");
        }

        System.out.println(sb.toString());
    }
}
