package Baekjoon.Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q27433 {
    public static Long fac(int N) {
        if (N == 1 || N == 0) {
            return 1L;
        }
        return N * fac(N - 1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(fac(N));
    }
}
