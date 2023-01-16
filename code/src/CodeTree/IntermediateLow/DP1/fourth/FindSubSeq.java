package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class FindSubSeq {
    private static int n, m, INT_MAX = Integer.MAX_VALUE;
    private static int[] arr = new int[100];
    private static int[] dp = new int[10001];

    public static void init(){
        for(int i = 0; i <= m; i++){
            dp[i] = INT_MAX;
        }

        dp[0] = 0;
    }

    public static void findSubSeq(){
        init();

        for(int i = n-1; i >= 0; i--){
            for(int j = m; j >= 0; j--){
                if(j >= arr[i]){
                    if(dp[j - arr[i]] == INT_MAX) continue;
                    dp[j] = Math.min(dp[j], dp[j-arr[i]] + 1);
                }
            }
        }

        // for(int i = 1; i <= m; i++){
        //     System.out.print(dp[i] + " ");
        // }
        // System.out.println();
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findSubSeq();

        if(dp[m] == INT_MAX){
            System.out.println("No");
        }else{
            System.out.println("Yes");
        }

    }
}