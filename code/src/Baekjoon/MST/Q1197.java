package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q1197 {

    private static class Node implements Comparable<Node> {
        int src, dest, cost;

        public Node(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    private static int V, E;
    private static int[] parent;
    private static ArrayList<Node> graph = new ArrayList<>();

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

    public static void init(){
        for(int i = 1; i <= V; i++){
            parent[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];

        init();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.add(new Node(a, b, c));
        }

        Collections.sort(graph);

        long result = 0;
        for(int i = 0; i < graph.size(); i++){
            int cost = graph.get(i).cost;
            int a = graph.get(i).src;
            int b = graph.get(i).dest;

            if(findParent(a) != findParent(b)){
                union(a, b);
                result += cost;
            }
        }
        System.out.println(result);
    }
}
