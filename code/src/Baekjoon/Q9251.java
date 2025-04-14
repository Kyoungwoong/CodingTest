package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int max = Integer.MIN_VALUE;
        int lenA = A.length();
        int lenB = B.length();

        int[][] dp = new int[lenB][lenA];

        // init
        for (int i = 0; i < lenA; i++) {
            if (A.charAt(i) == B.charAt(0)) {
                dp[0][i] = 1;
            } else {
                if (i - 1 >= 0) {
                    dp[0][i] = dp[0][i-1];
                }
            }
        }

        for (int i = 0; i < lenB; i++) {
            if (B.charAt(i) == A.charAt(0)) {
                dp[i][0] = 1;
            } else {
                if (i - 1 >= 0) {
                    dp[i][0] = dp[i-1][0];
                }
            }
        }

        for (int i = 1; i < lenB; i++) {
            for (int j = 1; j < lenA; j++) {
                if (A.charAt(j) == B.charAt(i)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        for (int i = 0; i < lenA; i++) {
            max = Math.max(max, dp[lenB - 1][i]);
        }
        System.out.println(max);
    }
}
