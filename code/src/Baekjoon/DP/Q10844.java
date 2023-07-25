package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10844 {
    private static final int MOD = 1000000000;
    private static int N;
    private static long[][] dp; // i번째 자릿수 j

    public static void init() {
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][10];
        init();

        for(int i = 2; i <= N; i++) {
            for(int j = 0; j < 10; j++) {
                if(j == 0) {
                    dp[i][0] = dp[i - 1][1];
                }
                else if (j == 9) {
                    dp[i][9] = dp[i - 1][8];
                }
                else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dp[N][i];
        }
        System.out.println(sum%MOD);
    }
}
