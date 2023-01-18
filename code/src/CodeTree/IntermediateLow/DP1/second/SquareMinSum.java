package CodeTree.IntermediateLow.DP1.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SquareMinSum {
    private static int n;
    private static int[][] arr = new int[100][100];
    private static int[][] dp = new int[100][100];

    public static void init(){
        dp[0][n-1] = arr[0][n-1];

        // 처음행
        for(int i = n-2; i >= 0; i--){
            dp[0][i] = arr[0][i] + dp[0][i+1];
        }

        // 마지막 열
        for(int i = 1; i < n; i++){
            dp[i][n-1] = arr[i][n-1] + dp[i-1][n-1];
        }

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        init();

        for(int i = 1; i < n; i++){
            for(int j = n-2; j >= 0; j--){
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j+1]) + arr[i][j];
            }
        }

        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < n; j++){
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.println(dp[n-1][0]);
    }
}