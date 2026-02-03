package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1916 {
    static class Path implements Comparable<Path> {
        int desc, cost;

        public Path(int desc, int cost) {
            this.desc = desc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path path) {
            return this.cost - path.cost;
        }
    }

    private static int N, M, start, end;
    private static List<List<Path>> graph;

    public static void main(String[] args) throws IOException {
        input();
        int ans = dijkstra();
        System.out.println(ans);
    }

    private static int dijkstra() {
        int[] d = new int[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        PriorityQueue<Path> pq = new PriorityQueue<>();
        pq.add(new Path(start, 0));

        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            if (d[cur.desc] < cur.cost) {
                continue;
            }
            d[cur.desc] = cur.cost;
            for (Path p : graph.get(cur.desc)) {
                if (d[p.desc] > d[cur.desc] + p.cost) {
                    d[p.desc] = d[cur.desc] + p.cost;
                    pq.add(new Path(p.desc, d[cur.desc] + p.cost));
                }

            }
        }

        return d[end];
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(src).add(new Path(desc, cost));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }
}

/*
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
---
4
 */