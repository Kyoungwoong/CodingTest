package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gold {
    private static int T, n, m;
    private static int[][] gold;

    public static int find() {
        int[][] result = new int[n][m];

        // initiation
        for (int i = 0; i < n; i++) {
            result[i][0] = gold[i][0];
        }

        // dynamic programming
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                // first row
                if (i - 1 < 0) {
                    result[i][j] = gold[i][j] + Math.max(result[i][j-1], result[i+1][j-1]);
                } else if (i + 1 >= n) {
                    result[i][j] = gold[i][j] + Math.max(result[i][j - 1], result[i - 1][j - 1]);
                }else{
                    result[i][j] = gold[i][j] + Math.max(Math.max(result[i][j - 1], result[i - 1][j - 1]), result[i + 1][j - 1]);
                }
            }
        }

        // find result
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, result[i][m-1]);
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            gold = new int[n][m];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    gold[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println(find());
        }

    }
}
/*
2
3 4
1 3 3 2 2 1 4 1 0 6 4 7
4 4
1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
--- 19
--- 16
 */
