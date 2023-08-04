package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1753 {

    static class Node implements Comparable<Node> {

        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node node) {
            return this.w - node.w;
        }
    }

    private static int V, E, INF = Integer.MAX_VALUE;
    private static int[] dis;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void dijkstra(int s) {
        dis[s] = 0;
        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll(); // smallest Node
            int start = cur.v;
            int cost = cur.w;

            if(dis[start] < cost){
                continue;
            }

            for (int i = 0; i < graph.get(start).size(); i++) {
                Node dest = graph.get(start).get(i);
                if (dis[dest.v] > dis[start] + dest.w) {
                    dis[dest.v] = dis[start] + dest.w;
                    pq.offer(new Node(dest.v, dis[start] + dest.w));
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(br.readLine());

        dis = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
            dis[i] = INF;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }

        dijkstra(s);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dis[i] == INF) {
                sb.append("INF\n");
            } else {
                sb.append(dis[i] + "\n");
            }
        }
        System.out.println(sb);
    }
}

