package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11057 {
    private static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // dp[i][d] = 길이가 i이고, 마지막 자리가 d인 오르막 수의 개수
        // (0 ≤ d ≤ 9)
        long[][] dp = new long[N + 1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k >= 0; k--) {
                    dp[i][j] = (dp[i][j] % MOD + dp[i - 1][k] % MOD) % MOD;
                }
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
//            System.out.print(dp[N][i] + " ");
            ans = (ans % MOD + dp[N][i] % MOD) % MOD;
        }
        System.out.println(ans);
    }
}
