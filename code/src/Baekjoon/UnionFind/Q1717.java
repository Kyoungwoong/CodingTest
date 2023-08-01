package Baekjoon.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1717 {
    private static int N, M;
    private static int[] set;

    public static int findParent(int n) {
        if (set[n] != n) {
            set[n] = findParent(set[n]);
        }
        return set[n];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            set[b] = a;
        } else {
            set[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        set = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            set[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    if (findParent(a) == findParent(b)) {
                        sb.append("YES\n");
                    } else {
                        sb.append("NO\n");
                    }
            }
        }
        System.out.println(sb);
    }
}
