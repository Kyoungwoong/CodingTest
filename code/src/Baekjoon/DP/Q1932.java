package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1932 {
    private static int N;
    private static int[][] arr, dp;

    public static void init() {
        dp[0][0] = arr[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }

        for (int i = 1; i < N; i++) {
            dp[i][i] = dp[i - 1][i - 1] + arr[i][i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();

        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= i - 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
            }
        }

        int ans = -1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(dp[N - 1][i], ans);
        }
        System.out.println(ans);
    }
}
