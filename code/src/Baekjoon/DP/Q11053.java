package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11053 {
    private static int N;
    private static int[] arr, dp;

    public static void main(String[] args) throws IOException {
//        prev();
        april16();
    }

    private static void april16() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n + 1];

        dp[1] = 1;
        int ans = dp[1];
        for (int i = 2; i <= n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);

    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.print(dp[i] + " ");
//        }
        int ans = -1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
