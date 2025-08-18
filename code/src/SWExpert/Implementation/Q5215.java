package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q5215 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int testCase, N, L;
    private static int[] scores;
    private static int[] calories;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test = 1; test <= testCase; test++) {
            input();

            int maxScore = solve();

            sb.append(String.format("#%d %d\n", test, maxScore));
        }
        System.out.println(sb.toString());
    }

    private static int solve() {
        for (int foodIdx = 0; foodIdx < N; foodIdx++) {
            int score = scores[foodIdx];
            int calory = calories[foodIdx];

            for (int limit = L; limit >= calory; limit--) {
                if (dp[limit - calory] != Integer.MIN_VALUE) {
                    dp[limit] = Math.max(dp[limit], dp[limit - calory] + score);
                }
            }
        }

        int result = 0;
        for (int i = 0; i <= L; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }


    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        scores = new int[N];
        calories = new int[N];

        for (int foodIdx = 0; foodIdx < N; foodIdx++) {
            st = new StringTokenizer(br.readLine());
            scores[foodIdx] = Integer.parseInt(st.nextToken());
            calories[foodIdx] = Integer.parseInt(st.nextToken());
        }

        // i번째 칼로리까지 고려했을 때 최대 맛
        dp = new int[L + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
    }
}
