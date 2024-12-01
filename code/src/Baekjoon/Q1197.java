package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1197 {
    // https://www.acmicpc.net/problem/1197
    static class Edge implements Comparable<Edge> {
        int src, desc, cost;

        public Edge(int src, int desc, int cost) {
            this.src = src;
            this.desc = desc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.cost - edge.cost;
        }
    }

    private static int[] parent;

    public static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }

        return parent[n];
    }

    public static void union(int a, int b) {
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
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        parent = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Edge(src, desc, cost));
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int src = cur.src;
            int desc = cur.desc;
            int cost = cur.cost;

            if (findParent(src) != findParent(desc)) {
                union(src, desc);
                ans += cost;
            }
        }

        System.out.println(ans);
    }
}
