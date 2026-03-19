package Baekjoon;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Q1914 {
    private static int N;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        System.out.println(BigInteger.TWO.pow(N).subtract(BigInteger.ONE));

        if (N <= 20) {
            move(N, 1, 2, 3);
            System.out.println(sb.toString());
        }
    }

    public static void move(int n, int from, int via, int to) {
        if (n == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            return;
        }
        move(n - 1, from, to, via);
        sb.append(from).append(" ").append(to).append("\n");
        move(n - 1, via, from, to);
    }
}
