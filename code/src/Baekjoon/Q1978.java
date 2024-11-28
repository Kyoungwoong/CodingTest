package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1978 {
    // https://www.acmicpc.net/problem/1978
    private static final int MAX_SIZE = 1001;
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
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int ans = 0;
        findPrime();

        while (st.hasMoreTokens()) {
            if (isPrime[Integer.parseInt(st.nextToken())]) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
