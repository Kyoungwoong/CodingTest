package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11404 {
    private static int n, m;
    private static int[][] d;

    public static void main(String[] args) throws IOException {
//        prev();
        may13();
    }

    private static void may13() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        d = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
            d[i][i] = 0;
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken()) - 1;
            int desc = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            d[src][desc] = Math.min(d[src][desc], cost);
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (d[i][k] == Integer.MAX_VALUE ||
                            d[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(d[i][j] == Integer.MAX_VALUE ?
                        0 : d[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        d = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (d[a][b] != 0) {
                d[a][b] = Math.min(d[a][b], c);
            } else {
                d[a][b] = c;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (d[i][k] != 0 && d[k][j] != 0) {
                        if (d[i][j] == 0) {
                            d[i][j] = d[i][k] + d[k][j];
                        } else {
                            d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(d[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
