package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q2293 {
    // https://www.acmicpc.net/problem/2293
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[k + 1];
        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= k; i++) {
                dp[i] += dp[i - coin];
            }
            for (int j = 1; j <= k; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();
        }

        System.out.println(dp[k]);
    }
}
