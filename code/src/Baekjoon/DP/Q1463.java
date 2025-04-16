package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1463 {
    private static int N;
    private static long[] dp;

    public static void init() {
        dp[1] = 0;
        if(N >= 2)
            dp[2] = 1;
        if (N >= 3) {
            dp[3] = 1;
        }

    }

    public static void main(String[] args) throws IOException {
//        prev();
        april16();
    }

    private static void april16() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }

        System.out.println(dp[N]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1];

        init();


        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

        }

        System.out.println(dp[N]);
    }
}
