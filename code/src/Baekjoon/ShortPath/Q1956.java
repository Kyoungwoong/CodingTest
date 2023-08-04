package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1956 {

    private static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private static int V, E;
    private static int[][] d;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void floyd() {
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (d[i][k] != Integer.MAX_VALUE && d[k][j] != Integer.MAX_VALUE) {
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        d = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    d[i][j] = 0;
                }
                d[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            d[a][b] = c;
            graph.get(a).add(new Node(b, c));
        }

        floyd();

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            // i가 시작.
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                if (d[i][j] != Integer.MAX_VALUE && d[j][i] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, d[i][j] + d[j][i]);
                }
            }
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
