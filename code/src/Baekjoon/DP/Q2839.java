package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Q2839 {
    private static int[] basket = {3, 5};
    private static int[] weights;
    private static final int INVALID = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
//        prev();
        newProblem();
    }

    private static void newProblem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        Arrays.fill(weights, INVALID);
        weights[0] = 0;

        for (int i = 3; i <= N; i++) {
            if (weights[i - 3] != INVALID) {
                weights[i] = Math.min(weights[i], weights[i - 3] + 1);
            }
            if (i-5 >= 0 && weights[i - 5] != INVALID) {
                weights[i] = Math.min(weights[i], weights[i - 5] + 1);
            }
        }

        System.out.println(weights[N] == INVALID ? -1 : weights[N]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        weights = new int[N + 1];
        Arrays.fill(weights, INVALID);
        weights[0] = 0;

        int size = basket.length;
        for (int i = 1; i <= N; i++) {
            for (int idx = 0; idx < size; idx++) {
                if (i - basket[idx] >= 0 && weights[i - basket[idx]] != INVALID) {
                    weights[i] = Math.min(weights[i], weights[i - basket[idx]]) + 1;
                }
            }
        }

//        for (int i = 0; i <= N; i++) {
//            System.out.print(i + ": \t");
//            System.out.println(weights[i]);
//        }

        if (weights[N] == INVALID) {
            System.out.println(-1);
        } else {
            System.out.println(weights[N]);
        }
    }
}
