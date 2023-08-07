package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q6497 {
    private static class House implements Comparable<House> {
        int x, y, z;

        public House(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int compareTo(House house) {
            return this.z - house.z;
        }
    }
    private static int N, M;

    private static ArrayList<House> graph = new ArrayList<>();
    private static int[] parent;

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
        }else{
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                System.out.println(sb);
                break;
            }

            parent = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            int max = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                graph.add(new House(x, y, z));
                max += z;
            }

            Collections.sort(graph);

            int min = 0;
            for (int i = 0; i < graph.size(); i++) {
                int src = graph.get(i).x;
                int dest = graph.get(i).y;
                int dist = graph.get(i).z;

                if (findParent(src) != findParent(dest)) {
                    union(src, dest);
                    min += dist;
                }
            }
            sb.append(max - min + "\n");
        }
    }
}
