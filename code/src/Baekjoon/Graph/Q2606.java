package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2606 {
    private static int N, M;
    private static Queue<Integer> q = new LinkedList<>();
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void init() {
        q.add(1);
        visited[1] = true;
    }

    public static void bfs() {
        init();

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < graph.get(cur).size(); i++) {
                int dest = graph.get(cur).get(i);
                if (!visited[dest]) {
                    q.add(dest);
                    visited[dest] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs();

        int cnt = -1;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
//                System.out.println(i);
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
