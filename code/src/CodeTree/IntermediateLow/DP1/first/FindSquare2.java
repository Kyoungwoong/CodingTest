package CodeTree.IntermediateLow.DP1.first;

import java.util.Scanner;

public class FindSquare2 {
    private static int n;
    private static int[] dp = new int[1001];

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp[0] = 1;
        dp[1] = 2;

        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3) % 1000000007;
            for(int j = i - 3; j >= 0; j--)
                dp[i] = (dp[i] + dp[j] * 2) % 1000000007;
        }

        System.out.println(dp[n]);
    }
}