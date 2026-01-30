package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1149 {
    private static int N;
    private static int[][] RGB, dp;
    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
//        prev();
        jan29();
    }


    private final static int RED = 0, GREEN = 1, BLUE = 2;

    private static void jan29() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        RGB = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][N];
        dp[0][RED] = RGB[0][RED];
        dp[0][GREEN] = RGB[0][GREEN];
        dp[0][BLUE] = RGB[0][BLUE];

        for (int i = 1; i < N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        dp[i][j] = Math.min(dp[i][j], RGB[i][j] + dp[i - 1][k]);
                    }
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            ans = Math.min(ans, dp[N - 1][i]);
        }
        System.out.println(ans);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        RGB = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                RGB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = RGB[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + RGB[i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            ans = Math.min(dp[N - 1][i], ans);
        }
        System.out.println(ans);
    }
}
