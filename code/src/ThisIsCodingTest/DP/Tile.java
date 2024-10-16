package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tile {
    public static int N;
    public static int[] dp = new int[1001];
    
    public static void main(String[] args) throws IOException {
//        prev();
        oct15();
    }

    private static void oct15() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 796796;
        }

        System.out.println("dp[N] = " + dp[N]);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 3;

        for(int i = 3; i <= N; i++){
            dp[i] = (dp[i-1] + 2*dp[i-2])%796796;
        }
        System.out.println("dp[N] = " + dp[N]);
    }
}
