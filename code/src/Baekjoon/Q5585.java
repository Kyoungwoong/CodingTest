package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q5585 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = 1000 - Integer.parseInt(br.readLine());

        int[] coins = new int[] {500, 100, 50, 10, 5, 1};

        int ans = 0;
        for (int idx = 0; idx < 6; idx++) {
            int coin = coins[idx];
            if (N / coin != 0) {
                ans += N / coin;
                N %= coin;
            }

            if (N == 0) {
                break;
            }
        }

        System.out.println(ans);
    }
}
