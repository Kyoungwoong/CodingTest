package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindLongValue {
        private static int n, INT_MIN = Integer.MIN_VALUE;
        private static int[] value = new int[101];
        // i는 i번째 막대기 선택할 때 j는 길이
        private static int[][] dp = new int[101][101];

        public static void init(){
            for(int i = 0; i <= n; i++){
                for(int j = 0; j <= n; j++){
                    dp[i][j] = INT_MIN;
                }
            }
            for(int i = 0; i <= n; i++){
                dp[i][0] = 0;
            }
            for(int i = 1; i <= n; i++){
                dp[1][i] = dp[1][i-1] + value[1];
            }
        }

        public static void findLongValue(){
            for(int i = 2; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(i > j){
                        dp[i][j] = dp[i-1][j];
                    }else{ // i >= j
                        dp[i][j] = Math.max(dp[i-1][j], value[i]);
                        for(int k = j-1; k >= 1; k--){
                            dp[i][j] = Math.max(dp[i][k] + dp[i][j-k], dp[i][j]);
                        }
                    }
                }
            }
            // for(int i = 0; i <= n; i++){
            //     for(int j = 0; j <= n; j++){
            //         System.out.print(dp[i][j] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
        }

        public static void main(String[] args) throws IOException{
            // 여기에 코드를 작성해주세요.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                value[i] = Integer.parseInt(st.nextToken());
            }

            init();
            findLongValue();

            int max = INT_MIN;
            for(int i = 1; i <= n; i++){
                max = Math.max(max, dp[n][i]);
            }
            System.out.println(max);
        }
}

