package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int[] coins = {25, 10, 5, 1};
        for (int i = 0; i < T; i++) {
            int C = Integer.parseInt(br.readLine());

            for (int j = 0; j < 4; j++) {
                sb.append(C / coins[j] + " ");
                C -= (C / coins[j]) * coins[j];
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
