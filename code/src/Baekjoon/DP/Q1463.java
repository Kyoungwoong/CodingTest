package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1463 {
    private static int N;
    private static long[] dp;

    public static void init() {
        dp[1] = 0;
        if(N >= 2)
            dp[2] = 1;
        if (N >= 3) {
            dp[3] = 1;
        }

    }

    public static void main(String[] args) throws IOException {
//        prev();
//        april16();
        jan29();
    }

    private static final int INVALID = Integer.MAX_VALUE;
    private static void jan29() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int[] array = new int[X + 1];
        Arrays.fill(array, INVALID);
        array[1] = 0;
        if (X >= 2) {
            array[2] = 1;
            if (X >= 3) {
                array[3] = 1;
            }
        }

        for (int i = 4; i <= X; i++) {
            // X가 3으로 나누어 떨어지면, 3으로 나눈다.
            if (i % 3 == 0 && array[i / 3] != INVALID) {
                array[i] = Math.min(array[i], array[i / 3] + 1);
            }
            // X가 2으로 나누어 떨어지면, 2으로 나눈다.
            if (i % 2 == 0 && array[i / 2] != INVALID) {
                array[i] = Math.min(array[i], array[i / 2] + 1);
            }
            // 1을 뺀다
            array[i] = Math.min(array[i], array[i - 1] + 1);
        }

//        for (int i = 0; i <= X; i++) {
//            System.out.print(array[i] + "\t");
//        }
//        System.out.println();
        System.out.println(array[X]);
    }

    private static void april16() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1000001];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
        }

        System.out.println(dp[N]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+1];

        init();


        for (int i = 4; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

        }

        System.out.println(dp[N]);
    }
}
