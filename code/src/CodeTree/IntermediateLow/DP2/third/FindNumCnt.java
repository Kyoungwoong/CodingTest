package CodeTree.IntermediateLow.DP2.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindNumCnt {
    private static int n, m, INT_MIN = Integer.MIN_VALUE;
    private static int[] arr = new int[101];
    // i번째까지 고려했을 때 나올 수 있는 j+20 값.
    private static long[][] dp = new long[101][41];

    public static void init(){
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= 40; j++){
                // dp[i][j] = INT_MIN;
                dp[i][j] = 0;
            }
        }

        dp[1][arr[1]+20]++;
        dp[1][-1*arr[1]+20]++;

    }

    public static void findNumCnt(){
        init();

        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= 40; j++){
                if(dp[i-1][j] == 0){
                    continue;
                }

                if(0 <= (j - arr[i]) && (j-arr[i]) <= 40){
                    dp[i][j-arr[i]] += dp[i-1][j];
                }
                if(0 <= (j + arr[i]) && (j+arr[i]) <= 40){
                    dp[i][j+arr[i]] += dp[i-1][j];
                }

            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findNumCnt();

        System.out.println(dp[n][m+20]);

    }
}