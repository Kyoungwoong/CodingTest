package CodeTree.IntermediateLow.DP1.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindJump {
    private static int n;
    private static int[] arr = new int[1001];
    private static int[] dp = new int[1001];

    public static void findJump(){
        dp[1] = 0;
        // System.out.print(dp[1]+ " ");
        for(int i = 2; i <= n; i++){
            for(int j = 1; j < i; j++){

                if(j + arr[j] >= i){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            // System.out.print(dp[i] + " ");
        }
        // System.out.println();
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findJump();

        int ans = -1;
        for(int i = 2; i <= n; i++){
            ans = Math.max(ans, dp[i]);
            if(dp[i] == 0) {
                break;
            }
        }

        System.out.println(ans);
    }
}