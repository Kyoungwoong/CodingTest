package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q16202 {
    static class Node implements Comparable<Node> {
        int src, desc, cost;

        public Node(int src, int desc, int cost) {
            this.src = src;
            this.desc = desc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static int N, M, K;
    private static int[] parent;
    static List<Node> edges = new ArrayList<>();

    private static void init() {
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }
    private static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    private static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            edges.add(new Node(src, dest, i));
        }

        StringBuilder sb = new StringBuilder();
        int removeStart = 0; // 간선 제거 시작 인덱스

        for (int turn = 0; turn < K; turn++) {
            int result = kruskal(removeStart);

            sb.append(result);
            if (turn != K - 1) sb.append(" ");

            if (result == 0) {
                for (int i = turn + 1; i < K; i++) {
                    sb.append("0");
                    if (i != K - 1) sb.append(" ");
                }
                break;
            }

            removeStart++;
        }

        System.out.println(sb.toString());
    }

    private static int kruskal(int skip) {
        init();
        int costSum = 0;
        int edgeCount = 0;

        for (int i = skip; i < edges.size(); i++) {
            Node edge = edges.get(i);
            int a = findParent(edge.src);
            int b = findParent(edge.desc);
            if (a != b) {
                union(a, b);
                costSum += edge.cost;
                edgeCount++;
                if (edgeCount == N - 1) break;
            }
        }

        return (edgeCount == N - 1) ? costSum : 0;
    }

}
