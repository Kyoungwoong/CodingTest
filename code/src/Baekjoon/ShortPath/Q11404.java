package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
 * 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 *
 * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 */
public class Q11404 {
    static class Path {
        int desc, cost;

        public Path(int desc, int cost) {
            this.desc = desc;
            this.cost = cost;
        }
    }
    private static int n, m;
    private static List<List<Path>> graph;
    private static int[][] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        d = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
            d[i][i] = 0;
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(src).add(new Path(desc, cost));
            d[src][desc] = Math.min(d[src][desc], cost);
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (d[i][j] == Integer.MAX_VALUE) {
                    sb.append(0);
                } else {
                    sb.append(d[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (d[i][k] == Integer.MAX_VALUE || d[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }
}
/*
5
14
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
3 5 10
3 1 8
1 4 2
5 1 7
3 4 2
5 2 4
---
0 2 3 1 4
12 0 15 2 5
8 5 0 1 1
10 7 13 0 3
7 4 10 6 0
 */

//public class Q11404 {
//    private static int n, m;
//    private static int[][] d;
//
//    public static void main(String[] args) throws IOException {
////        prev();
//        may13();
//    }
//
//    private static void may13() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        n = Integer.parseInt(br.readLine());
//        m = Integer.parseInt(br.readLine());
//
//        d = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(d[i], Integer.MAX_VALUE);
//            d[i][i] = 0;
//        }
//
//        StringTokenizer st;
//        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine());
//            int src = Integer.parseInt(st.nextToken()) - 1;
//            int desc = Integer.parseInt(st.nextToken()) - 1;
//            int cost = Integer.parseInt(st.nextToken());
//            d[src][desc] = Math.min(d[src][desc], cost);
//        }
//
//        for (int k = 0; k < n; k++) {
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (i == j) {
//                        continue;
//                    }
//                    if (d[i][k] == Integer.MAX_VALUE ||
//                            d[k][j] == Integer.MAX_VALUE) {
//                        continue;
//                    }
//                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
//                }
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                sb.append(d[i][j] == Integer.MAX_VALUE ?
//                        0 : d[i][j]);
//                sb.append(" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb.toString());
//    }
//
//    private static void prev() throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        n = Integer.parseInt(br.readLine());
//        m = Integer.parseInt(br.readLine());
//        d = new int[n + 1][n + 1];
//
//        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine());
//            int a = Integer.parseInt(st.nextToken());
//            int b = Integer.parseInt(st.nextToken());
//            int c = Integer.parseInt(st.nextToken());
//
//            if (d[a][b] != 0) {
//                d[a][b] = Math.min(d[a][b], c);
//            } else {
//                d[a][b] = c;
//            }
//        }
//
//        for (int k = 1; k <= n; k++) {
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= n; j++) {
//                    if (i == j) {
//                        continue;
//                    }
//                    if (d[i][k] != 0 && d[k][j] != 0) {
//                        if (d[i][j] == 0) {
//                            d[i][j] = d[i][k] + d[k][j];
//                        } else {
//                            d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
//                        }
//                    }
//                }
//            }
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                sb.append(d[i][j] + " ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
//    }
//}
