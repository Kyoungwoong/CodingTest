package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1916 {
    private static final int INFINITY = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken()) - 1;
            int desc = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(src).add(new Node(desc, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(dijkstra(graph, start, end, N));
    }

    public static int dijkstra(List<List<Node>> graph, int start, int end, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N];
        Arrays.fill(distance, INFINITY);
        distance[start] = 0;

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int src = cur.vertex;
            int cost = cur.cost;

            if (distance[src] < cost) {
                continue;
            }

            for (Node desc : graph.get(src)) {
                if (distance[desc.vertex] > cost + desc.cost) {
                    distance[desc.vertex] = cost + desc.cost;
                    pq.offer(new Node(desc.vertex, cost + desc.cost));
                }
            }
        }

        return distance[end];
    }
}
