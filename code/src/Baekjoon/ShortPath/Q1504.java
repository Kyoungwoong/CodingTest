package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1504 {
    private static class Node implements Comparable<Node>{
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
    private static int N, E, v1, v2, INF = (int)1e8;
    private static int[] d;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void dijkstra(int s) {
        d = new int[N + 1];
        Arrays.fill(d, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        d[s] = 0;
        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int start = now.dest;
            int weight = now.cost;

            if(d[start] < weight){
                continue;
            }

            for (int i = 0; i < graph.get(start).size(); i++) {
                Node dest = graph.get(start).get(i);
                if (d[dest.dest] > d[start] + dest.cost) {
                    d[dest.dest] = d[start] + dest.cost;
                    pq.offer(new Node(dest.dest, d[start] + dest.cost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N
        int first = 0;
        dijkstra(1);
        first += d[v1];
        dijkstra(v1);
        first += d[v2];
        dijkstra(v2);
        first += d[N];

        // 1 -> v2 -> v1 -> N
        int second = 0;
        dijkstra(1);
        second += d[v2];
        dijkstra(v2);
        second += d[v1];
        dijkstra(v1);
        second += d[N];

//        System.out.println(first);
//        System.out.println(second);

        if (second >= INF && first >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(second, first));
        }
    }
}

/*

 */