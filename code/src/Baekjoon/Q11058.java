package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Q11058 {
    // https://www.acmicpc.net/problem/11058
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N <= 4) {
            System.out.println(N);
            return;
        }

        // i번 눌렀을 때 A의 최대 개수
        int[] dp = new int[N + 1];
        for (int i = 1; i <= 3; i++) {
            dp[i] = i;
        }

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 3; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
            }
        }
        System.out.println(dp[N]);
    }
}
