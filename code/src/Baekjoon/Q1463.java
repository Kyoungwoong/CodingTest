package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        long[] dp = new long[X + 1];
        if (X <= 3) {
            System.out.println(1);
            return;
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= X; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }
        System.out.println(dp[X]);
    }
}
