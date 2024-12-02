package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q2294 {
    // https://www.acmicpc.net/problem/2294
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        List<Integer> coins = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int coin = Integer.parseInt(br.readLine());
            if (coin > k) {
                continue;
            }
            coins.add(coin);
            dp[coin] = 1;
        }

        dp[0] = 0;
        for (int i = 1; i <= k; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        System.out.println(dp[k] == Integer.MAX_VALUE ? -1 : dp[k]);
    }
}
