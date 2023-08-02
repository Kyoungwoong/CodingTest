package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11725 {
    private static int N;
    private static int[] parent;
    private static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    private static boolean[] visited;

    public static void dfs(int start) {
        visited[start] = true;
        for (int i = 0; i < tree.get(start).size(); i++) {
            int dest = tree.get(start).get(i);
            if (!visited[dest]) {
                parent[dest] = start;
                dfs(dest);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
//            parent[i] = i;
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        dfs(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i] + "\n");
        }
        System.out.println(sb);
    }
}
