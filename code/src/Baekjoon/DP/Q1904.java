package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1904 {
    private static int[] dp = new int[1000001];

    public static void init() {
        dp[1] = 1;
        dp[2] = 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        init();
        for (int i = 3; i <= N; i++) {
            if (i % 2 == 1) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
            } else {
                dp[i] = (dp[i - 2] + dp[i - 1]) % 15746;
            }
        }
        System.out.println(dp[N]);
    }
}
