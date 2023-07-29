package Baekjoon.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2630 {
    private static int N, white = 0, blue = 0;
    private static int[][] board;

    public static void binarySearch(int row, int col, int size) {
        for(int i=row; i<row+size; i++) {
            for(int j= col; j<col+size; j++) {
                if(board[row][col] != board[i][j]) {
                    size /= 2;
                    binarySearch(row,col,size); // 1D
                    binarySearch(row+size, col, size);
                    binarySearch(row,col+size, size);
                    binarySearch(row+size,col+size,size);
                    return;
                }
            }
        }
        if(board[row][col] == 1) {
            blue++;
            return;
        } else{
            white++;
            return;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        binarySearch(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }
}
