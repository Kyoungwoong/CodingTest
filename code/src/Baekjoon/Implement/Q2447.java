package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q2447 {
    private static int N;

    public static void star(int x, int y, int num) {
        if ((x / num) % 3 == 1 && (y / num) % 3 == 1) {
            System.out.print(" ");
        }else{
            if (num / 3 == 0) {
                System.out.print("*");
            } else {
                star(x, y, num / 3);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                star(i, j, N);
            }
            System.out.println();
        }
    }
}
