package SWExpert.DP;

import java.io.*;
import java.util.*;

public class Q1263 {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] dist = new int[N][N];

            // 인접 행렬 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int val = Integer.parseInt(st.nextToken());
                    if (i == j) dist[i][j] = 0;
                    else dist[i][j] = (val == 1) ? 1 : INF;
                }
            }

            // Floyd-Warshall 알고리즘
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            // Closeness Centrality 계산
            int minCC = INF;
            for (int i = 0; i < N; i++) {
                int cc = 0;
                for (int j = 0; j < N; j++) {
                    cc += dist[i][j];
                }
                minCC = Math.min(minCC, cc);
            }

            sb.append("#").append(t).append(" ").append(minCC).append("\n");
        }

        System.out.print(sb);
    }
}
