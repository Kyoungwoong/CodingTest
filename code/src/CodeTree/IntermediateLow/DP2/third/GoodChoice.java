package CodeTree.IntermediateLow.DP2.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class GoodChoice {
    private static int n, INT_MIN = Integer.MIN_VALUE;
    private static int[] red = new int[201];
    private static int[] blue = new int[201];
    // i번째까지 고려했고, j개의 blue를 선택했을 때의 최대
    private static int[][] dp = new int[201][201];

    public static void init(){
        for(int i = 0; i <= 2*n; i++){
            for(int j = 0; j <= 2*n; j++){
                dp[i][j] = INT_MIN;
            }
        }

        dp[0][0] = 0;

        for(int i = 1; i <= 2*n; i++){
            dp[i][0] = dp[i-1][0] + red[i];
        }
        dp[1][1] = blue[1];
    }

    public static void goodChoice(){
        init();
        for(int i = 2; i <= 2*n; i++){
            for(int j = 1; j <= i; j++){
                dp[i][j] = Math.max(dp[i-1][j-1] + blue[i], dp[i-1][j] + red[i]);
            }
        }

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 1 ; i <= 2*n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            red[i] = Integer.parseInt(st.nextToken());
            blue[i] = Integer.parseInt(st.nextToken());
        }
        goodChoice();

        System.out.println(dp[2*n][n]);
    }
}