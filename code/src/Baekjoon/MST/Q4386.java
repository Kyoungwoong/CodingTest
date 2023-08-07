package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q4386 {
    private static class Dot {
        int idx;
        double x, y;
        double cost;

        public Dot(int idx, double x, double y, double cost) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        public Dot(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }

    }

    private static class Pair implements Comparable<Pair> {
        int src, dest;
        double cost;

        public Pair(int src, int dest, double cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair dot) {
            if (this.cost < dot.cost) {
                return -1;
            } else if (this.cost == dot.cost) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    private static int N;
    private static int[] parent;

    private static ArrayList<Dot> graph = new ArrayList<>();
    private static ArrayList<Pair> ans = new ArrayList<>();

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
        for(int i = 1; i <= N; i++){
            parent[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        init();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
//            System.out.println(x + " " + y);
            graph.add(new Dot(i, x, y));
        }

        for (int i = 0; i < graph.size(); i++) {
            Dot cur = graph.get(i);
            for (int j = i; j < graph.size(); j++) {
                if (i == j) {
                    continue;
                }
                Dot dest = graph.get(j);
                double x = Math.pow(Math.abs(cur.x - dest.x), 2);
                double y = Math.pow(Math.abs(cur.y - dest.y), 2);
                double cost = (Math.round(Math.sqrt(x + y) * 100) / 100.0);
                ans.add(new Pair(cur.idx, dest.idx, cost));
            }
        }

        Collections.sort(ans);

//        for (Pair pair: ans) {
//            System.out.println(pair.src + " " + pair.dest + " " + pair.cost);
//        }

        double result = 0;
        for(int i = 0; i < ans.size(); i++){
            double cost = ans.get(i).cost;
            int a = ans.get(i).src;
            int b = ans.get(i).dest;

            if(findParent(a) != findParent(b)){
                union(a, b);
                result += cost;
            }
        }

        System.out.println(result);
    }
}
