package Baekjoon.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.StringTokenizer;

public class Q25682 {
    private static char[][] board;

    private static int[][] sum;
    private static int N, M, K;

    public static int getSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2]     - sum[x1 - 1][y2] -
                sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }

    public static int correct(char start) {
        int count = Integer.MAX_VALUE;
        int value;
        sum = new int[N + 1][M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                if((i + j) % 2 == 0) {
                    value = board[i][j] != start? 1 : 0;
                }else {
                    value = board[i][j] == start? 1 : 0;
                }
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + value;
            }
        }

        for(int i = 1; i <= N - K + 1; i++) {
            for(int j = 1; j <= M - K + 1; j++) {
                count = Math.min(count, getSum(i, j, i + K - 1, j + K - 1));
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N + 1][M + 1];
        sum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                board[i][j] = line.charAt(j - 1);
            }
        }

        System.out.println(Math.min(correct('B'), correct('W')));

    }
}
