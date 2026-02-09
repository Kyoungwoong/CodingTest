package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1964 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int MOD = 45678;
    static int N;
    static int[] arr = new int[10000001];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        init();

        System.out.println(arr[N]);
    }

    private static void init() {
        arr[1] = 5;

        for (int i = 2; i <= 10000000; i++) {
            arr[i] = (arr[i - 1] % MOD + (3 * i + 1) % MOD) % MOD;
        }
    }

    private static int solve(int N) {
        if (N == 1) {
            return 5;
        }

        return (solve(N - 1) % MOD + (3 * N + 1) % MOD) % MOD;
    }

}
