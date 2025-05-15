package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Q1719 {
    static class Node implements Comparable<Node> {
        int desc, cost, init;

        public Node(int desc, int cost) {
            this.desc = desc;
            this.cost = cost;
            this.init = -1;
        }

        public Node(int desc, int cost, int init) {
            this.desc = desc;
            this.cost = cost;
            this.init = init;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class Sibling {
        int idx, depth;

        public Sibling(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
    private static int N, M;
    private static List<List<Node>> graph = new ArrayList<>();
    private static Sibling[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(src).add(new Node(desc, cost));
            graph.get(desc).add(new Node(src, cost));
        }

        int[][] ans = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Sibling[] temp = dijkstra(i);
            for (int j = 1; j <= N; j++) {
                ans[i][j] = temp[j].idx;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(i == j ? "-" : ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static Sibling[] dijkstra(int start) {
        int[] d = new int[N + 1];
        parent = new Sibling[N + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        for (int i = 0; i <= N; i++) {
            parent[i] = new Sibling(i, Integer.MAX_VALUE);
        }
        parent[start] = new Sibling(start, 0);
        d[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
//        pq.add(new Node(start, 0));

        for (Node next : graph.get(start)) {
            d[next.desc] = next.cost;
            parent[next.desc] = new Sibling(next.desc, 1);
            pq.add(new Node(next.desc, d[next.desc]));
        }

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int idx = now.desc;
            int cost = now.cost;

            if (d[idx] < cost) continue;

            for (Node next : graph.get(idx)) {
                int nextIdx = next.desc;
                int nextCost = next.cost;
                int init = next.init;

//                for (int i = 1; i <= N; i++) {
//                    System.out.print(parent[i].depth == Integer.MAX_VALUE ?
//                            "- " : parent[i].idx + " ");
//                }
//                System.out.println();

                if (d[nextIdx] > d[idx] + nextCost) {
                    union(idx, nextIdx);
                    pq.add(new Node(nextIdx, d[idx] + nextCost, nextIdx));
                    d[nextIdx] = d[idx] + nextCost;
                }
            }
        }

        return parent;
    }

    private static int findParent(int n) {
        if (parent[n].depth == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (parent[n].depth > 1) {
            int root = findParent(parent[n].idx);
            parent[n] = parent[root];
        }

        return parent[n].idx;
    }

    private static void union(int a, int b) { // 6 5
        int parentA = findParent(a); // 6
        int parentB = findParent(b); // 5 -> 3

        if (parentA == Integer.MAX_VALUE) {
            parent[a] = new Sibling(parent[b].idx, parent[b].depth + 1);
        } else if (parentB == Integer.MAX_VALUE) {
            parent[b] = new Sibling(parent[a].idx, parent[a].depth + 1);
        } else {
            parent[b] = new Sibling(parentA, parent[parentA].depth + 1);
//            if (parentA < parentB) {
//                parent[b] = new Sibling(parentA, parent[parentA].depth + 1);
//            } else {
//                parent[a] = new Sibling(parentB, parent[parentB].depth + 1);
//            }
        }
    }
}
