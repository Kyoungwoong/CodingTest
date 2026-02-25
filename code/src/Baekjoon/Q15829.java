package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15829 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int R = 31, MOD = 1234567891;
    static int L;
    static String s;

    public static void main(String[] args) throws IOException {
        L = Integer.parseInt(br.readLine());
        s = br.readLine();

        long ans = 0;
        long pow = 1;

        for (int i = 0; i < L; i++) {
            long val = (s.charAt(i) - 'a' + 1);
            ans = (ans + (val * pow) % MOD) % MOD;
            pow = (pow * R) % MOD;
        }
        System.out.println(ans);
    }
}
