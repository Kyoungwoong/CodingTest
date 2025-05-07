package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1922 {
    static class Computer implements Comparable<Computer> {
        int src, desc, cost;

        public Computer(int src, int desc, int cost) {
            this.src = src;
            this.desc = desc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Computer o) {
            return this.cost - o.cost;
        }
    }
    private static int N, M;
    private static int[] parent;

    private static void init() {
        for (int i = 0; i <= N; i++) {
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
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        StringTokenizer st;
        PriorityQueue<Computer> computers = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            computers.add(new Computer(src, desc, cost));
        }

        init();

        int ans = 0;
        while (!computers.isEmpty()) {
            Computer c = computers.poll();
            int src = c.src;
            int desc = c.desc;
            int cost = c.cost;

            if (findParent(src) != findParent(desc)) {
                union(src, desc);
                ans += cost;
            }
        }

        System.out.println(ans);
    }
}
