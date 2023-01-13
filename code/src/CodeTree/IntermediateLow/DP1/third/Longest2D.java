package CodeTree.IntermediateLow.DP1.third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Longest2D {
    private static int n, m, maxRow = 50, maxCol = 50;;
    private static int[][] arr = new int[maxRow][maxCol];
    private static int[][] dp = new int[maxRow][maxCol];

    public static void init(){
        // for(int i = 0; i < n; i++){
        //     dp[i][0] = 1;
        // }
        // for(int j = 0; j < m; j++){
        //     dp[0][j] = 1;
        // }
    }

    public static void longest2D(){
        // init();
        dp[0][0] = 1;

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){

                int stdX = i-1;
                int stdY = j-1;

                for(int s = 0; s <= stdX; s++){
                    for(int t = 0; t <= stdY; t++){
                        if(dp[s][t] == 0) continue;
                        if(arr[s][t] < arr[i][j]){
                            dp[i][j] = Math.max(dp[i][j], dp[s][t] + 1);
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        longest2D();

        int max = -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                // System.out.print(dp[i][j] + " ");
                max = Math.max(max, dp[i][j]);
            }
            // System.out.println();
        }

        System.out.println(max);
    }
}