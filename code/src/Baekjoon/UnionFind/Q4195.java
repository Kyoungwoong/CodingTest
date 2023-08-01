package Baekjoon.UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q4195 {

    private static int[] parent;
    private static int[] level;

    public static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    public static int union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
            level[a] += level[b];
            return level[a];
        } else if (a == b) {
           return level[a];
        } else {
            parent[a] = b;
            level[b] += level[a];
            return level[b];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int F = Integer.parseInt(br.readLine());

            parent = new int[F * 2];
            level = new int[F * 2];
            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                level[i] = 1;
            }
            int cnt = 0; // 현재까지의 들어온 사람 수
            HashMap<String, Integer> network = new HashMap<>();

            for (int f = 0; f < F; f++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (!network.containsKey(a)) {
                    network.put(a, cnt++);
                }

                if (!network.containsKey(b)) {
                    network.put(b, cnt++);
                }

                sb.append(union(network.get(a), network.get(b)) + "\n");
            }
        }
        System.out.println(sb);
    }
}
