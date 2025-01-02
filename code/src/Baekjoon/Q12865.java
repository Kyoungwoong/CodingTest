package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q12865 {
    // https://www.acmicpc.net/problem/12865
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] W = new int[101];
        int[] V = new int[101];

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

        dp[W[0]] = V[0];

        for (int i = 1; i < N; i++) {
            int w = W[i];
            int v = V[i];

            for (int j = K; j >= 0; j--) {
                if (dp[j] == MIN) {
                    continue;
                }
                if (j + w > K) {
                    continue;
                }
                dp[j + w] = Math.max(dp[j + w], dp[j] + v);
            }

            dp[w] = Math.max(dp[w], v);
        }

        int ans = MIN;
        for (int i = 0; i <= K; i++) {
//            System.out.print(dp[i] + " ");
            ans = Math.max(ans, dp[i]);
        }
//        System.out.println();
        System.out.println(ans == MIN ? 0 : ans);

    }
}