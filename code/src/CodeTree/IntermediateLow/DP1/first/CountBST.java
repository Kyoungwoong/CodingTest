package CodeTree.IntermediateLow.DP1.first;

import java.util.Scanner;

public class CountBST {
    private static int n;
    private static int[] dp = new int[20];

    public static void main(String[] args) {
        // 여기에 코드를 작성해주세요.
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dp[0] = 1;
        dp[1] = 1;

        dp[2] = 2;
        dp[3] = 5;

        for(int i = 4; i <= n; i++){
            for(int j = 0; j < i; j++){
                dp[i] = (dp[i] + dp[j]*dp[i-j-1]);
            }
        }
        System.out.println(dp[n]);
    }
}
