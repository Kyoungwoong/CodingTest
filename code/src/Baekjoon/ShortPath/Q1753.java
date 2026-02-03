package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1753 {
    static class Path implements Comparable<Path> {
        int src, cost;

        public Path(int src, int cost) {
            this.src = src;
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
    private static int V, E, S;
    private static List<List<Path>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
//            graph.add(new Path(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            graph.get(Integer.parseInt(st.nextToken())).add(new Path(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] d = new int[V + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[S] = 0;
        PriorityQueue<Path> pq = new PriorityQueue<>();
        for (Path next : graph.get(S)) {
            pq.add(next);
//            d[next.src] = next.cost;
        }
        while (!pq.isEmpty()) {
            Path cur = pq.poll();
            if (d[cur.src] < cur.cost) {
                continue;
            }
//            System.out.println(cur.src + " " + cur.cost);
//            for (int i = 0; i <= V; i++) {
//                System.out.print(d[i] + " ");
//            }
//            System.out.println();
            d[cur.src] = Math.min(d[cur.src], cur.cost);
            for (Path next : graph.get(cur.src)) {
                if (d[next.src] > d[cur.src] + next.cost) {
                    d[next.src] = d[cur.src] + next.cost;
                    pq.add(new Path(next.src, d[next.src]));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(d[i] == Integer.MAX_VALUE ? "INF" : d[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
/*
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
---
0
2
3
7
INF

2 4
2
2 1 3
2 1 10
2 1 3
2 1 8
---
3
0
 */

//public class Q1753 {
//
//    static class Node implements Comparable<Node> {
//
//        int v, w;
//
//        public Node(int v, int w) {
//            this.v = v;
//            this.w = w;
//        }
//
//        @Override
//        public int compareTo(Node node) {
//            return this.w - node.w;
//        }
//    }
//
//    private static int V, E, INF = Integer.MAX_VALUE;
//    private static int[] dis;
//    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
//    private static PriorityQueue<Node> pq = new PriorityQueue<>();
//
//    public static void dijkstra(int s) {
//        dis[s] = 0;
//        pq.add(new Node(s, 0));
//
//        while (!pq.isEmpty()) {
//            Node cur = pq.poll(); // smallest Node
//            int start = cur.v;
//            int cost = cur.w;
//
//            if(dis[start] < cost){
//                continue;
//            }
//
//            for (int i = 0; i < graph.get(start).size(); i++) {
//                Node dest = graph.get(start).get(i);
//                if (dis[dest.v] > dis[start] + dest.w) {
//                    dis[dest.v] = dis[start] + dest.w;
//                    pq.offer(new Node(dest.v, dis[start] + dest.w));
//                }
//            }
//
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        V = Integer.parseInt(st.nextToken());
//        E = Integer.parseInt(st.nextToken());
//        int s = Integer.parseInt(br.readLine());
//
//        dis = new int[V + 1];
//        for (int i = 0; i <= V; i++) {
//            graph.add(new ArrayList<>());
//            dis[i] = INF;
//        }
//
//        for (int i = 0; i < E; i++) {
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            int w = Integer.parseInt(st.nextToken());
//            graph.get(u).add(new Node(v, w));
//        }
//
//        dijkstra(s);
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= V; i++) {
//            if (dis[i] == INF) {
//                sb.append("INF\n");
//            } else {
//                sb.append(dis[i] + "\n");
//            }
//        }
//        System.out.println(sb);
//    }
//}
//
