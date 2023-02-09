package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Star {
    private static int n, k, ans;
    private static int[][] arr = new int[401][401];
    private static int[][] sum = new int[401][401];
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
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                // sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + arr[i][j];
                sum[i][j] = sum[i][j-1] + arr[i][j];
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                // 시작지점 (i, j)
                int num = 0;
                for(int r = i-k; r <= i + k; r++){
                    int c = k - Math.abs(i-r);

                    if(1 <= r && r <= n){
                        num += sum[r][Math.min(j+c, n)] - sum[r][Math.max(j-c-1, 0)];
                    }
                }
                ans = Math.max(ans, num);
            }
        }
        System.out.println(ans);

    }
}