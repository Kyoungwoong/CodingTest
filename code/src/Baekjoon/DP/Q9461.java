package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9461 {
    private static long[] dp = new long[101];

    public static void P() {
        dp[0] = dp[1] = dp[2] = 1;
        dp[3] = dp[4] = 2;
        for (int i = 5; i <= 100; i++) {
            dp[i] = dp[i - 5] + dp[i - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        P();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N-1] + "\n");
        }
        System.out.println(sb);

    }
}
