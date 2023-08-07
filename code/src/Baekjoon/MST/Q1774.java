package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q1774 {

    private static class God {
        double cost;
        int x, y, idx;

        public God(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    private static class Node implements Comparable<Node> {
        int src, dest;
        double cost;

        public Node(int src, int dest, double cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            if (this.cost < node.cost) {
                return -1;
            } else if (this.cost == node.cost) {
                return 0;
            } else {
                return 1;
            }
        }
    }


    private static int N, M;
    private static int[] parent;
    private static ArrayList<God> gods = new ArrayList<>();

    public static int findParent(int n){
        if(parent[n] != n){
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    public static void union(int a, int b){
        a = findParent(a);
        b = findParent(b);
        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        ArrayList<HashSet<Integer>> connected = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            gods.add(new God(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
            parent[i] = i;
            connected.add(new HashSet<>()); // 0 ~ N-1
        }
        connected.add(new HashSet<>()); // N

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            connected.get(a).add(b);
            union(a, b);
        }

        ArrayList<Node> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            God cur = gods.get(i);
            for (int j = i; j < N; j++) {
                if (i == j) {
                    continue;
                }
                God dest = gods.get(j);
                if(connected.get(cur.idx).contains(dest.idx)) {
                    continue;
                }
                double x = Math.pow(Math.abs(cur.x - dest.x), 2);
                double y = Math.pow(Math.abs(cur.y - dest.y), 2);
                double cost = Math.sqrt(x + y);
//                double cost = (Math.round(Math.sqrt(x + y) * 1000) / 1000.0);
//                System.out.println(cost);
                graph.add(new Node(cur.idx, dest.idx, cost));
            }
        }

        Collections.sort(graph);

        double result = 0;
        for (int i = 0; i < graph.size(); i++) {
            double cost = graph.get(i).cost;
            int a = graph.get(i).src;
            int b = graph.get(i).dest;

            if(findParent(a) != findParent(b)){
                union(a, b);
                result += cost;
            }
        }

        System.out.printf("%.2f", result);

    }
}
