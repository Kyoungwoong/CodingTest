package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q12026 {
    // https://www.acmicpc.net/problem/12026

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String block = br.readLine();

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < N; i++) {
            char cur = block.charAt(i);

            switch (cur) {
                case 'B':
                    for (int j = 0; j < i; j++) {
                        char prev = block.charAt(j);
                        if (prev == 'J' && dp[j] != Integer.MAX_VALUE) {
                            dp[i] = Math.min(dp[i], dp[j] + (i-j)*(i-j));
                        }
                    }
                    break;
                case 'O':
                    for (int j = 0; j < i; j++) {
                        char prev = block.charAt(j);
                        if (prev == 'B' && dp[j] != Integer.MAX_VALUE) {
                            dp[i] = Math.min(dp[i], dp[j] + (i-j)*(i-j));
                        }
                    }
                    break;
                case 'J':
                    for (int j = 0; j < i; j++) {
                        char prev = block.charAt(j);
                        if (prev == 'O' && dp[j] != Integer.MAX_VALUE) {
                            dp[i] = Math.min(dp[i], dp[j] + (i-j)*(i-j));
                        }
                    }
                    break;
            }
        }
        System.out.println((dp[N - 1] == Integer.MAX_VALUE) ? -1 : dp[N - 1]);
    }
}
