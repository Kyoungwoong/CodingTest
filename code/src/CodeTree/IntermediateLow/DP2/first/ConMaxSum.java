package CodeTree.IntermediateLow.DP2.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ConMaxSum {
    private static int n, INT_MIN = Integer.MIN_VALUE;
    private static int[] arr = new int[100000];
    // i일 때 합이 최대
    private static int[] dp = new int[100000];

    public static void init(){
        for(int i = 0; i < n; i++){
            dp[i] = INT_MIN;
        }
        dp[0] = arr[0];
    }

    public static void conMaxSum(){
        init();
        for(int i = 1; i < n; i++){
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        conMaxSum();
        int ans = INT_MIN;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}