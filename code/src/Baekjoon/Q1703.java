package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1703 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, S, P;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            if (N == 0) {
                break;
            }

            long ans = 1;
            for (int i = 0; i < N; i++) {
                S = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());

                ans *= S;
                ans -= P;
            }

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }
}
