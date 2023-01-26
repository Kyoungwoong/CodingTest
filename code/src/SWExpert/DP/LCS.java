package SWExpert.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;


public class LCS {
    private static int lenA, lenB, ans;
    private static String A, B;
    private static int[][] dp;

    public static void init(){
        dp = new int[1001][1001];

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
        ans = dp[lenB][lenA];
    }

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("SW_Testcase/DP/LCS.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = st.nextToken();
            B = st.nextToken();
            lenA = A.length();
            lenB = B.length();

            LCS();
            System.out.println("#" + test_case + " " + ans);
        }
    }
}