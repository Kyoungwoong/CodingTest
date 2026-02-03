package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1260 {
    private static int N, M, V;
    private static boolean[] visited;
    private static Queue<Integer> q = new LinkedList<>();
    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void init() {
        visited = new boolean[N + 1];
    }

    public static void dfs(int s) {
        visited[s] = true;
        sb.append(s + " ");

        for (int i = 0; i < graph.get(s).size(); i++) {
            int dest = graph.get(s).get(i);
            if (!visited[dest]) {
                visited[dest] = true;
                dfs(dest);
            }
        }
    }

    public static void bfs() {
        q.add(V);
        visited[V] = true;
        sb.append(V + " ");

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int dest = graph.get(cur).get(i);
                if (!visited[dest]) {
                    sb.append(dest + " ");
                    q.add(dest);
                    visited[dest] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();
        jan30();
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        init();

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

        dfs(V);
        sb.append("\n");

        init();

        bfs();

        System.out.println(sb);
    }

    private static void jan30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
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
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        StringBuilder sb = new StringBuilder();
        // dfs
        _dfs(V, sb);

        visited = new boolean[N + 1];
        sb.append("\n");

        // bfs
        _bfs(V, sb);

        System.out.println(sb.toString());
    }

    private static void _dfs(int cur, StringBuilder sb) {
        sb.append(cur).append(" ");
        visited[cur] = true;

        for (int desc : graph.get(cur)) {
            if (!visited[desc]) {
                _dfs(desc, sb);
            }
        }
    }

    private static void _bfs(int start, StringBuilder sb) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(" ");
            for (int desc : graph.get(cur)) {
                if (!visited[desc]) {
                    q.add(desc);
                    visited[desc] = true;
                }
            }
        }
    }
}
/*
4 5 1
1 2
1 3
1 4
2 4
3 4
---
1 2 4 3
1 2 3 4

10 9 1
1 2
1 3
2 4
4 8
4 9
3 5
3 6
3 7
7 10


나와야 하는 답:
1 2 4 8 9 3 5 6 7 10
1 2 3 4 5 6 7 8 9 10
 */
