package Baekjoon.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] temps = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            temps[i] = Integer.parseInt(st.nextToken());
            temps[i] += temps[i - 1];
        }
        int max = Integer.MIN_VALUE;
        for (int i = K; i <= N; i++) {
            max = Math.max(max, temps[i] - temps[i - K]);
        }
        System.out.println(max);
    }
}
