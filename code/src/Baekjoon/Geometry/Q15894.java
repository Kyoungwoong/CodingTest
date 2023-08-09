package Baekjoon.Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q15894 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long result = 3;
        for (int i = 1; i < N; i++) {
            result += 3;
        }
        result += N;
        System.out.println(result);
    }
}
