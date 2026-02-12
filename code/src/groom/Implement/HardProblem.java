package groom.Implement;

import java.io.*;

public class HardProblem {
    private static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        dp = new int[len + 1];

        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            dp[i] = dp[i-1] * i;
            while (dp[i] / 10 != 0) {
                // 각 자리 수 더하기
                int num = 0;
                while(dp[i] / 10 != 0) {
                    num += (dp[i] % 10);
                    dp[i] /= 10;
                }
                dp[i] += num;
            }
        }
        System.out.println(dp[len]);
    }
}
