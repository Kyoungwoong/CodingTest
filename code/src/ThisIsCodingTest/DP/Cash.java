package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Cash {
    public static int N, M;
    public static int[] dp = new int[10001];
    public static int[] coin = new int[100];

    public static void main(String[] args) throws IOException {
//        prev();
        oct15();
    }

    private static void oct15() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= M; i++){
            dp[i] = Integer.MAX_VALUE;
        } // 무한대인거는 불가능한거

        List<Integer> coins = new ArrayList<>();
        for(int i = 0; i < N; i++){
            int coin = Integer.parseInt(br.readLine());
            coins.add(coin);
            dp[coin] = 1;
        }

        for (int i = 1; i <= M; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        for (int i = 1; i <= M; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();

        if (dp[M] == Integer.MAX_VALUE) {
            System.out.println("-1 = " + -1);
        } else {
            System.out.println("dp[M] = " + dp[M]);
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= M; i++){
            dp[i] = Integer.MAX_VALUE;
        }

        for(int i = 0; i < N; i++){
            coin[i] = Integer.parseInt(br.readLine());
            dp[coin[i]] = 1;
        }

        for(int i = 1; i <= M; i++){
            for(int j = 0; j < N; j++){
                if(i - coin[j] <= 0){
                    continue;
                }
                if(dp[i-coin[j]] == Integer.MAX_VALUE){
                    continue;
                }

                dp[i] = Math.min(dp[i], dp[i-coin[j]] + 1);
            }

        }
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        // 0 0
        if(dp[M] == Integer.MAX_VALUE){
            System.out.println("-1 = " + -1);
        }else{
            System.out.println("dp[M] = " + dp[M]);
        }
    }
}
/*
2 15
2
3

3 4
3
5
7
 */
