package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Line implements Comparable<Line> {
    int a, b;

    public Line(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Line line) {
        return this.a - line.a;
    }
}

public class Q2565 {
    private static int N;
    private static Line[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new Line[N];
        B = new Line[N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[i] = new Line(a, b);
//            B[i] = b;
        }

        Arrays.sort(A);

        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[i].a > A[j].a && A[i].b > A[j].b) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = -1;
        for (int i = 0; i < N; i++) {
//            System.out.print(dp[i] + " ");
            ans = Math.max(ans, dp[i]);
        }
//        System.out.println();
        System.out.println(N - ans);
    }
}
