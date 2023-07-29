package Baekjoon.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11401 {
    private static int MOD = 1000000007;
    private static long[] arr;

    public static long fac(int n) {
        long fac = 1L;

        while(n > 1) {
            fac = (fac * n) % MOD;
            n--;
        }
        return fac;
    }

    public static long pow(long base, long expo) {
        long result = 1;

        while (expo > 0) {
            if (expo % 2 == 1) {
                result *= base;
                result %= MOD;
            }
            base = (base * base) % MOD;
            expo /= 2;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long numer = fac(N);

        long denom = fac(K) * fac(N - K) % MOD;

        System.out.println(numer * pow(denom, MOD - 2) % MOD);
    }
}
/*
169152626591028520278300
 */
