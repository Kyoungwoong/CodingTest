package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class MinMinus {
    private static int n, ans = Integer.MAX_VALUE;
    private static int[] num = new int[101];
    // i번째 수까지 고려했을 때 고른 수의 합이 j => true;
    //                             else => false;
    private static boolean[][] dp = new boolean[101][100001];
    private static int max = 0;

    public static void init(){
        dp[0][0] = true;
    }

    public static void minMinus(){
        init();

        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= max; j++){
                if(dp[i-1][j]){
                    dp[i][j] = true;
                }
                if(j >= num[i] && dp[i-1][j-num[i]]){
                    dp[i][j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            num[i] = Integer.parseInt(st.nextToken());
            // A.add(num[i]);
            max += num[i];
        }

        minMinus();
        for(int i = 1; i < max; i++){
            if(dp[n][i]){
                ans = Math.min(ans, Math.abs(i - (max - i)));
            }
        }
        System.out.println(ans);
    }
}