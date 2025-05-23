package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Make1 {
    public static int X;

    public static int count(int x){
        int[] dx = new int[30001];
        dx[1] = 0;
        dx[2] = 1;
        dx[3] = 1;
        dx[4] = 2;
        dx[5] = 1;

        for(int i = 6; i <= x; i++){
            int min = 100000000;
            if(i % 5 == 0){
                min = Math.min(min, dx[i/5] + 1);
            }else if(i % 3 == 0){
                min = Math.min(min, dx[i/3] + 1);
            }else if(i % 2 == 0){
                min = Math.min(min, dx[i/2] + 1);
            }
            min = Math.min(min, dx[i-1]+1);
            dx[i] = min;
        }

        return dx[x];

    }

    private static int oct15(int x) {
        int[] dp = new int[30001];

        for (int i = 2; i <= x; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            if (i % 5 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 5] + 1);
            }
        }
        return dp[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        System.out.println("count(X) = " + count(X));
        System.out.println("oct15(X) = " + oct15(X));

    }
}
