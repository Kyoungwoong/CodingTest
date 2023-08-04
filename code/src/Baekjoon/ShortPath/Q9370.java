package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9370 {

    private static class Node implements Comparable<Node>{
        int v, w;

        public Node(int dest, int cost) {
            this.v = dest;
            this.w = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.w - node.w;
        }
    }

    private static int N, M, t, s, g, h, INF = (int)1e8;
    private static int[] dis;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    private static ArrayList<Integer> end;

    private static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void dijkstra(int s) {
        dis = new int[N + 1];
        Arrays.fill(dis, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dis[s] = 0;
        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int start = now.v;
            int weight = now.w;

            System.out.println(start + " " + weight);

            if(dis[start] < weight){
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
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            end = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 교차로 (노드)
            M = Integer.parseInt(st.nextToken()); // 도로 (간선)
            t = Integer.parseInt(st.nextToken()); // 목적지 후보

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); // 출발지
            g = Integer.parseInt(st.nextToken()); // 경유지 1
            h = Integer.parseInt(st.nextToken()); // 경유지 2

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            for (int i = 0; i < t; i++) {
                end.add(Integer.parseInt(br.readLine()));
            }
//            System.out.println("N: " + N + " M: " + M + " t: " + t + " s: " + s + " g: " + g + " h: " + h);
            // --------------------- end Init ----------------------------

            // s -> g -> h -> ??
            int way = 0;
            dijkstra(s);
//            for (int i = 1; i <= N; i++) {
//                System.out.print(dis[i] + " ");
//            }
//            System.out.println();
            way += dis[g];
            dijkstra(g);
            way += dis[h];
            dijkstra(h);
            ArrayList<Integer> ans = new ArrayList<>();

            if (way < INF) {
                for (int i = 0; i < end.size(); i++) {
                    int v = end.get(i);
                    ans.add(way + dis[v]);
                }
            }

            Collections.sort(ans);
            for (int i = 0; i < ans.size(); i++) {
                sb.append(ans.get(i) + " ");
            }

            // s -> h -> g -> ??
            way = 0;
            dijkstra(s);
            way += dis[h];
            dijkstra(h);
            way += dis[g];
            dijkstra(g);
            ans = new ArrayList<>();
            if (way < INF) {
                for (int i = 0; i < end.size(); i++) {
                    int v = end.get(i);
                    ans.add(way + dis[v]);
                }
            }
            Collections.sort(ans);
            for (int i = 0; i < ans.size(); i++) {
                sb.append(ans.get(i) +" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
