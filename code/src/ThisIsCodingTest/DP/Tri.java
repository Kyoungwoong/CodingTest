package ThisIsCodingTest.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tri {
    private static int n;
    private static int[][] pascal;

    public static void main(String[] args) throws IOException {
//        prev();
        nov1();
    }

    private static void nov1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pascal = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                pascal[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] result = new int[n][n];
        result[0][0] = pascal[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = Math.max(result[i][j], result[i-1][j] + pascal[i][j]);
                if (j - 1 >= 0) {
                    result[i][j] = Math.max(result[i][j], result[i - 1][j - 1] + pascal[i][j]);
                }
            }
        }

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(result[n - 1][i], max);
        }
        System.out.println("max = " + max);

    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        pascal = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                pascal[i][j] = Integer.parseInt(st.nextToken());
            }
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
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                result[i][j] = pascal[i][j] + Math.max(result[i - 1][j - 1], result[i - 1][j]);
            }
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
