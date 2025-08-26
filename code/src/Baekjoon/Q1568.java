package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1568 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans = 0, K = 1;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        while (N > 0) {
            if (N < K) {
                K = 1;
            }
            N -= K;
            K++;
            ans++;
        }
        System.out.println(ans);
    }
}
