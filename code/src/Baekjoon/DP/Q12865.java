package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12865 {
    private static int N, K;
    private static int[] W = new int[101];
    private static int[] V = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            W[i] = w;
            V[i] = v;
        }

        // i번째 보석까지 고려했을 때 j 무게일때의 최대 가치
        int[] dp = new int[100001];
        int MIN = Integer.MIN_VALUE;
        Arrays.fill(dp, MIN);

        int w = W[0];
        int v = V[0];
        if (w <= K) {
            dp[w] = Math.max(dp[w], v);
        }
        dp[0] = 0;

        for (int i = 1; i < N; i++) {
            w = W[i];
            v = V[i];

            for (int j = K; j >= w; j--) {
                if (j - w < 0) {
                    continue;
                }
                if(dp[j-w] == MIN) {
                    continue;
                }
                dp[j] = Math.max(dp[j], dp[j-w] + v);
            }

//            for (int q = 0; q <= K; q++) {
//                System.out.print(dp[q] + " ");
//            }
//            System.out.println();
        }

        int ans = MIN;
        for (int i = 0; i <= K; i++) {
//            System.out.print(dp[i] + " ");
            ans = Math.max(ans, dp[i]);
        }
//        System.out.println();
        System.out.println(ans);

    }
}
