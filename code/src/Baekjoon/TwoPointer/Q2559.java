package Baekjoon.TwoPointer;

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

        int[] temp = new int[N];
        int[] prefix = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
            prefix[i + 1] = prefix[i] + temp[i];
        }

        for (int i = 0; i <= N; i++) {
            System.out.print(prefix[i] + " ");
        }
        System.out.println();

        int start = 0, end = K;
        int max = Integer.MIN_VALUE;

        while (end <= N) {
            max = Math.max(max, prefix[end++] - prefix[start++]);
        }

        System.out.println(max);
    }
}
