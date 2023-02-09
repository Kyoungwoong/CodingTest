package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class GetMaxSum {

    private static int n;
    private static int[][] arr = new int[301][301];
    private static int[][] sum = new int[301][301];
    public static int[] dp = new int[301];

    public static int getSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2]     - sum[x1 - 1][y2] -
                sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }

    public static int getMaxSum(int x1, int x2){
        for(int y = 1; y <= n; y++) {
            // y열에 있는 숫자들의 합을 구해줍니다.
            int value = getSum(x1, y, x2, y);

            dp[y] = Math.max(value, dp[y - 1] + value);
        }

        // dp 값 중 최댓값이 원하는 값이 됩니다.
        int maxArea = Integer.MIN_VALUE;
        for(int y = 1; y <= n; y++)
            maxArea = Math.max(maxArea, dp[y]);

        return maxArea;

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                // sum[i][j] = arr[i][j];
            }
        }

        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= n; j++)
                sum[i][j] = sum[i - 1][j] +
                        sum[i][j - 1] -
                        sum[i - 1][j - 1] +
                        arr[i][j];

        // for(int i = 0; i <= n; i++){
        //     for(int j = 0; j <= n; j++){
        //         System.out.print(sum[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            for(int j = i; j <= n; j++){
                max = Math.max(max, getMaxSum(i,j));
            }
        }
        System.out.println(max);
    }
}
