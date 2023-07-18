package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10798 {
    private static char[][] board = new char[15][15];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = '.';
            }
        }

        for (int i = 0; i < 5; i++) {
            String read = br.readLine();
            int len = read.length();
            for (int j = 0; j < len; j++) {
                board[i][j] = read.charAt(j);
            }
        }

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[j][i] != '.') {
                    System.out.print(board[j][i]);
                }
            }
        }
    }
}

// Aa0FfBb1GgCc2HhDd3IiEe4Jj
// Aa0aPAf985Bz1EhCz2W3D1gkD6x