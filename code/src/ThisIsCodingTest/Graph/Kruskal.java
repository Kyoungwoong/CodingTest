package ThisIsCodingTest.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Kruskal {
    static class Node implements Comparable<Node>{
        int std, index, cost;

        public Node(int std, int index, int cost){
            this.std = std;
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }

    public static int V, E, result = 0;
    public static int[] parent;
    public static ArrayList<Node> graph = new ArrayList<>();


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

//        prev();
        oct16();
    }

    private static void oct16() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];

        init();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(src, desc, cost));
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (findParent(cur.std) != findParent(cur.index)) {
                union(cur.std, cur.index);
                ans += cur.cost;
            }
        }
        System.out.println("ans = " + ans);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];

        init();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Node(a, b, cost));
        }

        Collections.sort(graph);

        for(int i = 0; i < graph.size(); i++){
            int cost = graph.get(i).cost;
            int a = graph.get(i).std;
            int b = graph.get(i).index;

            if(findParent(a) != findParent(b)){
                union(a, b);
                result += cost;
            }
        }

        System.out.println("result = " + result);
    }
}
/*
7 9
1 2 29
1 5 75
2 3 35
2 6 34
3 4 7
4 6 23
4 7 13
5 6 53
6 7 25
 */