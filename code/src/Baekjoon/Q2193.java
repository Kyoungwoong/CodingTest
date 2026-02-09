package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2193 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, ans = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            return;
        }

        long[] dp = new long[N + 1];

        dp[1] = 1; // 1
        dp[2] = 1; // 10

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        System.out.println(dp[N]);
    }
}
