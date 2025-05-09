package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1647 {
    static class City implements Comparable<City> {
        int src, desc, cost;

        public City(int src, int desc, int cost) {
            this.src = src;
            this.desc = desc;
            this.cost = cost;
        }

        @Override
        public int compareTo(City city) {
            return this.cost - city.cost;
        }
    }

    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        PriorityQueue<City> pq = new PriorityQueue<City>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new City(src, desc, cost));
        }

        int ans = 0, max = -1;
        while (!pq.isEmpty()) {
            City c = pq.poll();
            int src = c.src;
            int desc = c.desc;
            int cost = c.cost;

            if (findParent(src) != findParent(desc)) {
                union(src, desc);
                ans += cost;
                max = Math.max(max, cost);
            }
        }

        System.out.println(ans - max);
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
}
