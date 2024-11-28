package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2581 {
    // https://www.acmicpc.net/problem/2581
    private static final int MAX_SIZE = 10001;
    private static boolean[] isPrime = new boolean[MAX_SIZE];

    public static void findPrime() {
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i < MAX_SIZE; i++) {
            for (int j = i + i; j < MAX_SIZE; j += i) {
                isPrime[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        findPrime();

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int ans = 0;
        int min = Integer.MAX_VALUE;
        for (int i = M; i <= N; i++) {
            if (isPrime[i]) {
                ans += i;
                min = Math.min(min, i);
            }
        }

        if (ans == 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans + "\n" + min);
        }
    }
}
