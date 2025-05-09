package Baekjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int aIdx = 0, bIdx = 0;

        StringBuilder sb = new StringBuilder();
        while (aIdx < N || bIdx < M) {
            if (aIdx == N) {
                sb.append(B[bIdx++]);
            } else if (bIdx == M) {
                sb.append(A[aIdx++]);
            } else {
                if (A[aIdx] < B[bIdx]) {
                    sb.append(A[aIdx++]);
                } else {
                    sb.append(B[bIdx++]);
                }
            }

            if (aIdx == N && bIdx == M) {

            } else {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
