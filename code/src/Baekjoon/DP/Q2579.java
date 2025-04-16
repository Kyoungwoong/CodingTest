package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Stair {
    int cost, count;

    public Stair(int cost, int count) {
        this.cost = cost;
        this.count = count;
    }
}

public class Q2579 {
    public static int N;
    public static int[] stairs;
    public static int[] dp;

    public static void init() {

        dp[0] = stairs[0];
        dp[1] = Math.max(stairs[0] + stairs[1], stairs[1]);
        dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
    }


    public static void main(String[] args) throws IOException {
//        prev();
        april16();
    }

    private static void april16() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1]; // 0 열은 현재 칸을 밟은 경우 1열은 현재 칸을 안밟은 경우
        int[] stair = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            stair[i + 1] = num;
        }

        dp[1] = stair[1];
        if (n == 1) {
            System.out.println(dp[1]);
            return;
        }
        dp[2] = dp[1] + stair[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stair[i - 1]) + stair[i];
        }
        System.out.println(dp[n]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1 || N == 2) {
            int ans = 0;
            for (int i = 0; i < N; i++) {
                ans += stairs[i];
            }
            System.out.println(ans);
            System.exit(0);
        }

        init();

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 2] + stairs[i], stairs[i - 1] + stairs[i] + dp[i - 3]);
        }

        System.out.println(dp[N-1]);
    }
}
/*
6
10
20
15
25
10
20
--- 75
5
1
6
1
6
1
--- 14
4
1
100
100
1
--- 102
10
3
5
10
9
2
1
2
5
2
9
--- 37
 */
