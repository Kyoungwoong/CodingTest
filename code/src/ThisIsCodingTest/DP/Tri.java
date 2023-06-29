package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tri {
    private static int n;
    private static int[][] pascal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pascal = new int[n][n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= idx; j++) {
                pascal[i][j] = Integer.parseInt(st.nextToken());
            }
            idx++;
        }

        int[][] result = new int[n][n];
        result[0][0] = pascal[0][0];

        // left
        for (int i = 1; i < n; i++) {
            result[i][0] = pascal[i][0] + result[i-1][0];
        }
        // right
        for (int i = 1; i < n; i++) {
            result[i][i] = pascal[i][i] + result[i - 1][i - 1];
        }

        // mid
        idx = 2;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < idx; j++) {
                result[i][j] = pascal[i][j] + Math.max(result[i - 1][j - 1], result[i - 1][j]);
            }
            idx++;
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, result[n - 1][i]);
        }
        System.out.println(max);
    }
}
/*
5
7
3 8
8 1 0
2 7 4 4
4 5 2 6 5
---30
 */
