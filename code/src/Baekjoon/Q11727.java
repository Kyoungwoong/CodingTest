package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q11727 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        // 2 * N 크기의 직사각형
        int[] arr = new int[N + 1];
        arr[1] = 1;
        arr[2] = 3;

        for (int i = 3; i <= N; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2] * 2) % MOD;
        }

        System.out.println(arr[N]);
    }
}
