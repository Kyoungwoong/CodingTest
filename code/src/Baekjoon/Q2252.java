package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2252 {
    // https://www.acmicpc.net/problem/2252
    private static List<List<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> q = new LinkedList<>();
    private static boolean[] visited;
    private static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        degree = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            graph.get(src).add(desc);
            degree[desc]++;
        }

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                visited[i] = true;
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");

            for (int next : graph.get(now)) {
                degree[next]--;
                if (degree[next] == 0 && !visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
