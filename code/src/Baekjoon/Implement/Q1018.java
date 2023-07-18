package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1018 {
    private static int N, M;
    private static char[][] board;

    public static int checkWhite() {
        char[][] white = {
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'}
        };

        int result = 1000000;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // i, j가 시작 위치
                int std = 0;
                for (int k = i; k < i + 8; k++) {
                    for (int p = j; p < j + 8; p++) {
                        if (board[k][p] != white[k - i][p - j]) {
                            std++;
                        }
                    }
                }
                result = Math.min(result, std);
            }
        }
        return result;
    }

    public static int checkBlack() {
        char[][] black = {
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'},
                {'B','W','B','W','B','W','B','W'},
                {'W','B','W','B','W','B','W','B'}
        };

        int result = 10000000;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // i, j가 시작 위치
                int std = 0;
                for (int k = i; k < i + 8; k++) {
                    for (int p = j; p < j + 8; p++) {
                        if (board[k][p] != black[k - i][p - j]) {
                            std++;
                        }
                    }
                }
                result = Math.min(result, std);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        int ans = 0;
        ans = Math.min(checkWhite(), checkBlack());
        System.out.println(ans);

    }
}
