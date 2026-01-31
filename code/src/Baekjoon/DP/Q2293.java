package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다.
 * 그 경우의 수를 구하시오. 각각의 동전은 몇 개라도 사용할 수 있다.
 *
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 */
public class Q2293 {
    public static void main(String[] args) throws IOException {
        // prev();
        jan30();
    }

    private static void jan30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= k; i++) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        System.out.println(dp[k]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> coins = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= k; i++) {
                if (i - coin >= 0) {
                    dp[i] += dp[i - coin];
                }
                System.out.print(dp[i] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[k]);
    }
}
/*
3 10
1
2
5
---
10
 */
