package SWExpert.Graph.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1855 {

    private static int MAX_N = 100_001;
    private static int MAX_LOG = 18;

    private static int tc, N, ans = 0;
    private static List<List<Integer>> graph;
    private static int[][] parent; // row는 숫자, col은 depth
    private static int[] depth; // num에 해당하는 depth
    private static boolean[] visited; // dfs를 위한 visited 처리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            init();
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N; i++) {
                int parent = Integer.parseInt(st.nextToken());
                graph.get(parent).add(i + 1);
            }
            buildDepthAndParent(1);
            buildParent();

            simulate();
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        ans = 0;
        parent = new int[MAX_N][MAX_LOG];
        depth = new int[MAX_N];
        visited = new boolean[MAX_N];
        graph = new ArrayList<>();
        for (int i = 0; i < MAX_N; i++) {
            graph.add(new ArrayList<>());
        }
    }

    static void buildDepthAndParent(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);
        depth[root] = 0;
        parent[root][0] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                if (next != parent[cur][0]) { // 부모로 되돌아가지 않기
                    depth[next] = depth[cur] + 1;
                    parent[next][0] = cur;
                    queue.add(next);
                }
            }
        }
    }

    static void buildParent() {
        for (int k = 1; k < MAX_LOG; k++) {
            for (int v = 1; v <= N; v++) {
                parent[v][k] = parent[parent[v][k - 1]][k - 1];
            }
        }
    }

    private static void simulate() {
        Queue<Integer> fromQueue = new LinkedList<>();
        Queue<Integer> toQueue = new LinkedList<>();

        fromQueue.add(1);
        for (int child : graph.get(1)) {
            toQueue.add(child);
        }

        while (!fromQueue.isEmpty() && !toQueue.isEmpty()) {
            int from = fromQueue.poll();
            int to = toQueue.poll();

            int commonParent = LCA(from, to);
            ans += Math.abs(depth[from] - depth[commonParent]);
            ans += Math.abs(depth[to] - depth[commonParent]);
            for (int child : graph.get(to)) {
                toQueue.add(child);
            }
            fromQueue.add(to);
        }
    }

    private static int LCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        for (int k = MAX_LOG - 1; k >= 0; k--) {
            if (depth[u] - (1 << k) >= depth[v]) {
                u = parent[u][k];
            }
        }

        if (u == v) return u;

        for (int k = MAX_LOG - 1; k >= 0; k--) {
            if (parent[u][k] != parent[v][k]) {
                u = parent[u][k];
                v = parent[v][k];
            }
        }

        return parent[u][0];
    }
}
/*
3
4
1 1 2
4
1 1 3
11
1 1 3 3 2 4 1 3 2 9
 */
