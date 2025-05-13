package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1238 {

    static class Node implements Comparable<Node> {
        int desc, time;

        public Node(int desc, int time) {
            this.desc = desc;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
    private static int N, M, X;
    private static List<List<Node>> roads = new ArrayList<>();
    private static List<List<Node>> reverse = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            roads.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            roads.get(src).add(new Node(desc, time));
            reverse.get(desc).add(new Node(src, time));
        }

        int[] distFromX = dijkstra(X, roads);
        int[] distToX   = dijkstra(X, reverse);

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, distFromX[i] + distToX[i]);
        }

        System.out.println(maxTime);

    }

    private static int[] dijkstra(int start, List<List<Node>> g) {
        int[] d = new int[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[start] = 0;

        // 다익스트라 진행
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int desc = cur.desc;
            int time = cur.time;

            if (d[desc] < time) {
                continue;
            }

            for (Node next : g.get(desc)) {
                int nd = next.desc;
                int nt = next.time;
                if (d[nd] > d[desc] + nt) {
                    d[nd] = d[desc] + nt;
                    pq.add(new Node(nd, d[nd]));
                }
            }
        }

        return d;
    }
}
