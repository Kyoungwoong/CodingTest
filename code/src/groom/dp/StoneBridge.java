package groom.dp;

import java.io.*;
import java.util.*;

public class StoneBridge {
    private static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i+1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+2];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            if (i-1 >= 0) {
                min = Math.min(min, dp[i-1]);
            }
            if (i-2 >= 0) {
                min = Math.min(min, dp[i-2]);
            }
            if (i-3 >= 0) {
                min = Math.min(min, dp[i-3]);
            }
            dp[i] += min + arr[i];
        }

        System.out.println(Math.min(Math.min(dp[n], dp[n-1]), dp[n-2]));
    }
}
