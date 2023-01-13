package CodeTree.IntermediateLow.DP1.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class LongTermSequence {
    private static int n, size = 1000;
    private static int[] arr = new int[size + 1];
    private static int[] dp = new int[size + 1];

    public static void init(){
        for(int i = 1; i <=n; i++){
            dp[i] = 1;
        }
    }

    public static void LTS(){
        init();

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){

                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
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
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LTS();

        int max = 0;

        for(int i = 1; i <= n; i++){
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}