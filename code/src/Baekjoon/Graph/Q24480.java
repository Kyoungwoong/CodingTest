package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q24480 {
    private static int cnt = 1;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int[] ans;

    public static void dfs(int s) {

        for (int i = 0; i < graph.get(s).size(); i++) {
            int dest = graph.get(s).get(i);
            if (!visited[dest]) {
                cnt++;
                ans[dest] = cnt;
                visited[dest] = true;
                dfs(dest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
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
            Collections.sort(graph.get(i), Collections.reverseOrder());
        }

        ans[R] = cnt;
        visited[R] = true;
        dfs(R);
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i] + "\n");
        }
        System.out.println(sb);

    }
}
