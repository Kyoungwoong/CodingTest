package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15486 {
    // https://www.acmicpc.net/problem/15486
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] T = new int[N];
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[N + 1];
        for (int i = N - 1; i >= 0; i--) {
            if (i + T[i] <= N) {
                d[i] = Math.max(d[i + 1], P[i] + d[i + T[i]]);
            } else {
                d[i] = d[i + 1];
            }
        }

        System.out.println(d[0]);
    }
}
