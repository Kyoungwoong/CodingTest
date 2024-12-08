package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] V = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][M + 1];
        dp[0][S] = true;

        // DP 전개
        for (int i = 1; i <= N; i++) {
            for (int v = 0; v <= M; v++) {
                if (dp[i - 1][v]) {
                    if (v + V[i] <= M) dp[i][v + V[i]] = true;
                    if (v - V[i] >= 0) dp[i][v - V[i]] = true;
                }
            }
        }

        int result = -1;
        for (int v = 0; v <= M; v++) {
            if (dp[N][v]) result = Math.max(result, v);
        }

        System.out.println(result);
    }
}
