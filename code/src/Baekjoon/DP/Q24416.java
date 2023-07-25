package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q24416 {
    private static int fac = 0;
    private static int iter = 0;

    public static int facFib(int n) {
        fac++;
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return facFib(n - 1) + facFib(n - 2);
        }
    }

    public static int iterFib(int n) {
        int[] f = new int[41];
        f[1] = f[2] = 1;
        for (int i = 3; i <= n; i++) {
            iter++;
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        iterFib(n);
        System.out.println(facFib(n) + " " + iter);
    }
}
