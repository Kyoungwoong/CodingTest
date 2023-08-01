package Baekjoon.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q20040 {
    private static int N, M;
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
        } else {
            parent[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (findParent(a) != findParent(b)) {
                union(a, b);
            } else {
                System.out.println(i + 1);
                System.exit(0);
            }
        }
        System.out.println(0);
    }
}
