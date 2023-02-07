package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class SumN3 {
    private static int n, k;
    private static int[][] arr = new int[501][501];
    private static int[][] sum = new int[501][501];

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
            }
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                int row = i - k;
                int col = j - k;
                if(row < 0){
                    row = 0;
                }
                if(col < 0){
                    col = 0;
                }
                ans = Math.max(ans, sum[i][j] - sum[row][j] - sum[i][col] + sum[row][col]);
            }
        }
        System.out.println(ans);
    }
}
