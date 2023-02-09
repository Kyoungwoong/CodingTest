package CodeTree.IntermediateMID.PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BelongStone {
    private static int n, k;
    private static int[][] stone = new int[100001][4];

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= n; i++){
            int num = Integer.parseInt(br.readLine());
            for(int j = 1; j <= 3; j++){
                if(num == j){
                    stone[i][j] = stone[i-1][j] + 1;
                }else{
                    stone[i][j] = stone[i-1][j];
                }
                // System.out.print(stone[i][j] + " ");
            }
            // System.out.println();
        }

        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= 3; j++){
                sb.append((stone[e][j] - stone[s-1][j]) + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
