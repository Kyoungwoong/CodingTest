package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10162 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int A = 300, B = 60, C = 10;
    static int T;

    static int[] count = new int[3];

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        if (T % C != 0) {
            System.out.println(-1);
            return;
        }

        if (T / A > 0) {
            count[0] = T / A;
            T %= A;
        }

        if (T / B > 0) {
            count[1] = T / B;
            T %= B;
        }

        if (T / C > 0) {
            count[2] = T / C;
            T %= C;
        }

        if (T == 0) {
            System.out.print(count[0] + " " + count[1] + " " + count[2]);
        }
    }
}
