package Baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q2580 {
    private static final int N = 9;
    private static int[][] board = new int[N][N];

    public static void sudoku(int r, int c) {

        if (c == N) {
            sudoku(r + 1, 0);
            return;
        }

        if (r == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(board[i][j] + " ");
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (board[r][c] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (possibility(r, c, i)) {
                    board[r][c] = i;
                    sudoku(r, c + 1);
                }
            }
            board[r][c] = 0;
            return;
        }

        sudoku(r, c + 1);
    }

    public static boolean possibility(int row, int col, int value) {

        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == value) {
                return false;
            }
        }

        int set_row = (row / 3) * 3;
        int set_col = (col / 3) * 3;

        for (int i = set_row; i < set_row + 3; i++) {
            for (int j = set_col; j < set_col + 3; j++) {
                if (board[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }
}
