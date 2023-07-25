package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9184 {
    private static int[][][] arr = new int[21][21][21];
    public static void w() {

        for (int a = 0; a <= 20; a++) {
            for (int b = 0; b <= 20; b++) {
                for (int c = 0; c <= 20; c++) {
                    if (a == 0 || b == 0 || c == 0) {
                        arr[a][b][c] = 1;
                    } else if (a < b && b < c) {
                        arr[a][b][c] = arr[a][b][c - 1] + arr[a][b - 1][c - 1] - arr[a][b - 1][c];
                    } else {
                        arr[a][b][c] = arr[a - 1][b][c] + arr[a - 1][b - 1][c] + arr[a - 1][b][c - 1] - arr[a - 1][b - 1][c - 1];
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        w();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            if (a <= 0 || b <= 0 || c <= 0) {
                sb.append("w(" + a + ", " + b + ", " + c + ") = " + arr[0][0][0] + "\n");
            } else if (a > 20 || b > 20 || c > 20) {
                sb.append("w(" + a + ", " + b + ", " + c + ") = " + arr[20][20][20] + "\n");
            } else {
                sb.append("w(" + a + ", " + b + ", " + c + ") = " + arr[a][b][c] + "\n");
            }
        }
        System.out.println(sb);
    }
}
