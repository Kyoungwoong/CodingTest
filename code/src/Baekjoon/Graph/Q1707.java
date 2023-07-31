package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1707 {
    private static int V, E;
    private static int[] visited;
    private static ArrayList<ArrayList<Integer>> graph;
    private static StringBuilder sb = new StringBuilder();

    public static void bipartite() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= V; i++) {
            if (visited[i] == 0) {
                q.add(i);
                visited[i] = 1;
            }

            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int j = 0; j < graph.get(cur).size(); j++) {
                    int next = graph.get(cur).get(j);
                    if (visited[next] == 0) {
                        q.add(next);
                    }
                    if (visited[next] == visited[cur]) {
                        sb.append("NO\n");
                        return;
                    }
                    if (visited[cur] == 1 && visited[next] == 0) {
                        visited[next] = 2;
                    } else if (visited[cur] == 2 && visited[next] == 0) {
                        visited[next] = 1;
                    }
                }
            }
        }
        sb.append("YES\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            visited = new int[V + 1];
            graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            bipartite();
        }
        System.out.println(sb);
    }
}
