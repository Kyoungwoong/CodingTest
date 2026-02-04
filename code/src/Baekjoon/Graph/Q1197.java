package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1197 {
    static class Path implements Comparable<Path> {
        int src, desc, cost;

        public Path(int src, int desc, int cost) {
            this.src = src;
            this.desc = desc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path path) {
            if (this.cost == path.cost) {
                return this.src - path.src;
            }
            return this.cost - path.cost;
        }
    }
    private static int V, E;
    private static int[] parent;
    private static PriorityQueue<Path> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Path(src, desc, cost));
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Path cur = pq.poll();

            if (findParent(cur.src) != findParent(cur.desc)) {
                union(cur.src, cur.desc);
                ans += cur.cost;
            }
        }
        System.out.println(ans);
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

    private static int findParent(int n) {
        if (n != parent[n]) {
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }
}

/*
3 3
1 2 1
2 3 2
1 3 3
---
3
 */