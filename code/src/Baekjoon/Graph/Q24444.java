package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q24444 {
    private static int cnt = 1, R;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static Queue<Integer> q = new LinkedList<>();
    private static boolean[] visited;
    private static int[] ans;

    public static void init() {
        ans[R] = cnt;
        q.add(R);
        visited[R] = true;
    }

    public static void bfs() {
        init();

        while (!q.isEmpty()) {
            int dest = q.poll();
            for (int i = 0; i < graph.get(dest).size(); i++) {
                int temp = graph.get(dest).get(i);
                if (!visited[temp]) {
                    cnt++;
                    q.add(temp);
                    visited[temp] = true;
                    ans[temp] = cnt;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        ans = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        bfs();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i] + "\n");
        }
        System.out.println(sb);

    }
}
