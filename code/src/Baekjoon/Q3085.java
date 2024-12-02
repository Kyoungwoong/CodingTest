package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3085
public class Q3085 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        int max = 0;
        // 보드를 순회하며 인접한 사탕 교환
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 오른쪽 교환
                if (j + 1 < N) {
                    swap(board, i, j, i, j + 1);
                    max = Math.max(max, getMax(N, board));
                    swap(board, i, j, i, j + 1); // 원상복구
                }

                // 아래쪽 교환
                if (i + 1 < N) {
                    swap(board, i, j, i + 1, j);
                    max = Math.max(max, getMax(N, board));
                    swap(board, i, j, i + 1, j); // 원상복구
                }
            }
        }

        System.out.println(max);
    }

    // 보드에서 최대 연속된 동일 색상 사탕의 길이를 계산
    private static int getMax(int N, char[][] board) {
        int max = 0;

        for (int i = 0; i < N; i++) {
            int row = 1; // 현재 행에서 연속된 사탕 길이
            int col = 1; // 현재 열에서 연속된 사탕 길이

            for (int j = 1; j < N; j++) {
                // 행 확인
                if (board[i][j] == board[i][j - 1]) {
                    row++;
                } else {
                    max = Math.max(max, row);
                    row = 1;
                }

                // 열 확인
                if (board[j][i] == board[j - 1][i]) {
                    col++;
                } else {
                    max = Math.max(max, col);
                    col = 1;
                }
            }

            // 마지막 연속 길이 처리
            max = Math.max(max, row);
            max = Math.max(max, col);
        }

        return max;
    }

    // 보드에서 두 위치의 사탕을 교환
    private static void swap(char[][] board, int x1, int y1, int x2, int y2) {
        char temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}
