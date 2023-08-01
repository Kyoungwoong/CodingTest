package Baekjoon.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1976 {
    private static int N, M;
    private static int[] map;
    private static ArrayList<Integer> plans = new ArrayList<>();

    public static int findParent(int n) {
        if (map[n] != n) {
            map[n] = findParent(map[n]);
        }
        return map[n];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            map[b] = a;
        } else {
            map[a] = b;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            map[i] = i;
        }
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cmd = Integer.parseInt(st.nextToken());
                if (cmd == 1) {
                    union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plans.add(Integer.parseInt(st.nextToken()) - 1);
        }

//        for (int i = 0; i <= N; i++) {
//            System.out.print(map[i] + " ");
//        }
//        System.out.println();

        for (int i = 1; i < M; i++) {
            if (findParent(plans.get(i - 1)) != findParent(plans.get(i))) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }
}
