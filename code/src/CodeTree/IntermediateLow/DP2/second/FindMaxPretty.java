package CodeTree.IntermediateLow.DP2.second;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Clothes{
    int prev, cur, sum;
    public Clothes(int prev, int cur, int sum){
        this.prev = prev;
        this.cur = cur;
        this.sum = sum;
    }
}

public class FindMaxPretty {
    private static int N, M;
    private static int[] start = new int[201];
    private static int[] end = new int[201];
    private static int[] value = new int[201];
    // i번째 날짜에 j번까지 옷을 고려했을 때의 최대 화려함.
    private static int[][] dp = new int[201][201];

    public static void init(){
        for(int i = 0; i <= M; i++){
            for(int j = 0; j <= N; j++){
                dp[i][j] = Integer.MIN_VALUE;
                // dp[i][j] = Integer.MIN_VALUE;
            }
        }

        for(int i = 1; i <= N; i++){
            if(start[i] <= 1 && end[i] >= 1){
                dp[1][i] = 0;
            }
        }

    }

    public static void findMaxPretty(){
        init();

        for(int i = 2; i <= M; i++){
            for(int j = 1; j <= N; j++){
                for(int k = 1; k <= N; k++){
                    if(start[k] <= i - 1 && i - 1 <= end[k] && start[j] <= i && i <= end[j])
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Math.abs(value[j] - value[k]));
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            start[i] = Integer.parseInt(st.nextToken());
            end[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        findMaxPretty();

        int ans = -1;
        for(int i = 1; i <= N; i++){
            ans = Math.max(ans, dp[M][i]);
        }
        System.out.println(ans);
    }
}