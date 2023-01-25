package CodeTree.IntermediateLow.DP2.fourth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class EditDistance {
    private static int lenA, lenB;
    private static String A, B;
    // 첫번째 문자열 i번째와 두번째 문자열 j까지 고려했을 때의 편집 거리
    private static int[][] dp = new int[1001][1001];

    public static void init(){
        for(int i = 1; i <= lenA; i++){
            dp[i][0] = i;
        }

        for(int j = 1; j <= lenB; j++){
            dp[0][j] = j;
        }

    }

    public static void editDis(){
        init();

        for(int i = 1; i <= lenA; i++){
            for(int j = 1; j <= lenB; j++){

                if(A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int min = Math.min(dp[i][j-1], dp[i-1][j]);
                    min = Math.min(min, dp[i-1][j-1]);
                    dp[i][j] = min + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        lenA = A.length();
        lenB = B.length();

        editDis();

        System.out.println(dp[lenA][lenB]);
    }
}