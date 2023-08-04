package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q11657 {
    private static class City {
        int end, weight;

        public City(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    private static int N, M, INF = Integer.MAX_VALUE;
    private static long[] d;
    private static ArrayList<ArrayList<City>> graph = new ArrayList<>();

    public static boolean bellmanFord() {
        d[1] = 0;
        boolean update = false;

        for (int i = 1; i < N; i++) {
            update = false;
            for (int j = 1; j <= N; j++) {
                for (City city : graph.get(j)) {
                    if (d[j] == INF) {
                        break;
                    }

                    if (d[city.end] > d[j] + city.weight) {
                        d[city.end] = d[j] + city.weight;
                        update = true;
                    }
                }
            }
            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 1; i <= N; i++) {
                for (City city: graph.get(i)) {
                    if (d[i] == INF) {
                        break;
                    }
                    if (d[city.end] > d[i] + city.weight) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        d = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            d[i] = INF;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new City(b, c));
        }

        StringBuilder sb = new StringBuilder();
        if (bellmanFord()) {
            sb.append("-1\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if (d[i] == INF) {
                    sb.append("-1\n");
                } else {
                    sb.append(d[i] + "\n");
                }
            }
        }
        System.out.println(sb);

    }
}
