package Baekjoon.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        int[] dp = new int[100000001];
        int disable = Integer.MAX_VALUE;
        Arrays.fill(dp, disable);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            int coin = coins[i];
            for (int j = 1; j <= K; j++) {
                if (j - coin < 0) {
                    continue;
                }
                if (dp[j - coin] == disable) {
                    continue;
                }
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }

//        for (int i = 0; i < 100; i++) {
//            System.out.print(dp[i] + " ");
//        }
        System.out.println(dp[K]);
    }
}
