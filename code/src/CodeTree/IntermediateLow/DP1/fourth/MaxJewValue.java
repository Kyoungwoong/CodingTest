package CodeTree.IntermediateLow.DP1.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MaxJewValue {
    private static int n, m;
    private static int[] value = new int[101];
    private static int[] weight = new int[101];
    // 무게가 i일때 얻을 수 있는 최대 가치.
    private static int[] dp = new int[10001];

    public static void maxJewValue(){
        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(j >= weight[i]){
                    dp[j] = Math.max(dp[j], dp[j-weight[i]] + value[i]);
                }
            }
        }

        // for(int i = 0; i <= m; i++){
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
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        maxJewValue();

        System.out.println(dp[m]);

    }
}
