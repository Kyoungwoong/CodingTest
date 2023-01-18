package CodeTree.IntermediateLow.DP1.first;

import java.util.Scanner;

public class FindSquare3 {
    private static int n, MOD = 10007;
    private static int[] dp = new int[1001];

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 3;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-1] + 2 * dp[i-2]) % MOD;
        }
        System.out.println(dp[n]);
    }
}