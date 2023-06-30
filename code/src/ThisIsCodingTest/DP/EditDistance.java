package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EditDistance {
    private static String A, B;
    private static int lenA, lenB;
    private static int[][] dp;

    public static void init() {
        for(int i = 1; i <= lenA; i++){
            dp[i][0] = i;
        }

        for(int j = 1; j <= lenB; j++){
            dp[0][j] = j;
        }
    }

    public static void editDis() {
        init();

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {

                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int min = Math.min(dp[i][j - 1], dp[i - 1][j]);
                    min = Math.min(min, dp[i - 1][j - 1]);
                    dp[i][j] = min + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = br.readLine();
        B = br.readLine();
        lenA = A.length();
        lenB = B.length();
        dp = new int[lenA + 1][lenB + 1];

        editDis();
        for (int i = 0; i <= lenA; i++) {
            for (int j = 0; j <= lenB; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[lenA][lenB]);
    }
}

/*
cat
cut
--- 1

sunday
saturday
--- 3
 */
