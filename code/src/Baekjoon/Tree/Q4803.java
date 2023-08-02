package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Q4803 {
    private static int N, M;
    private static int[] parent;
    private static StringBuilder sb = new StringBuilder();
    private static HashSet<Integer> graph;
    private static HashSet<Integer> cycle;

    public static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else if (a == b) {
            if (a < b) {
                parent[a] = 0;
            } else {
                parent[b] = 0;
            }
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 1; ; i++){
            boolean isCycle = false;
            graph = new HashSet<>();
            cycle = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
//            System.out.println("N&M : " + N + " " + M);

            if (N == 0 && M == 0) {
                break;
            }

            parent = new int[N + 1];
            for (int j = 1; j <= N; j++) {
                parent[j] = j;
                graph.add(j);
            }

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            Set<Integer> treeSet = new HashSet<>();
            for (int p = 1; p <= N; p++) {
                int pp = findParent(p);
                if (pp > 0) {
                    treeSet.add(pp);
                }
            }

            int treeNum= treeSet.size();
            if(treeNum ==0) {
                sb.append("Case " + i +": ").append("No trees.\n");
            }else if(treeNum ==1) {
                sb.append("Case " + i +": ").append("There is one tree.\n");
            }else if(treeNum >1){
                sb.append("Case " + i +": ").append("A forest of "+ treeNum+" trees.\n");
            }


        }
        System.out.println(sb);


    }
}
