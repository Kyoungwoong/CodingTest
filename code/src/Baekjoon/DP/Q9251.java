package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Q9251 {
    private static int n, lenA, lenB;
    private static String A, B;
    // 두번째 문자열 i위치와 첫번째 문자열 j위치까지 고려햇을 때 최장 공통 부분 수열
    private static int[][] dp = new int[1001][1001];

    public static void init(){
        if(A.charAt(0) == B.charAt(0)){
            dp[1][1] = 1;
        }else{
            dp[1][1] = 0;
        }

        for(int i = 2; i <= lenB; i++){
            if(dp[i-1][1] == 1){
                dp[i][1] = 1;
                continue;
            }

            if(B.charAt(i-1) == A.charAt(0)){
                dp[i][1] = 1;
            }else{
                dp[i][1] = 0;
            }
        }

        for(int j = 2; j <= lenA; j++){
            if(dp[1][j-1] == 1){
                dp[1][j] = 1;
                continue;
            }

            if(A.charAt(j-1) == B.charAt(0)){
                dp[1][j] = 1;
            }else{
                dp[1][j] = 0;
            }
        }
    }

    public static void LCS(){
        init();

        for(int i = 2; i <= lenB; i++){
            for(int j = 2; j <= lenA; j++){
                if(A.charAt(j-1) == B.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
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

//        LCS();
        dp = new int[lenB + 1][lenA + 1];

        for (int i = 1; i <= lenB; i++) {
            for (int j = 1; j <= lenA; j++) {
                if (A.charAt(j - 1) == B.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[lenB][lenA]);
    }
}
