package Baekjoon.DivideConquer;

import CodeTree.IntermediateLow.Backtracking.second.Min;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
    private static int[][] arr;
    private static int cnt_1 = 0, cnt_0 = 0, cntM = 0;

    public static void tripleSearch(int row, int col, int size) {
        if (size == 1) {
            if (arr[row][col] == -1) {
                cntM++;
            } else if (arr[row][col] == 0) {
                cnt_0++;
            } else {
                cnt_1++;
            }
            return;
        }

        int thirds = size / 3;
        boolean isMinus = true;
        boolean isOne = true;
        boolean isZero = true;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] == -1) {
                    isOne = false;
                    isZero = false;
                } else if (arr[i][j] == 0) {
                    isMinus = false;
                    isOne = false;
                } else {
                    isMinus = false;
                    isZero = false;
                }
            }
        }
        if (isMinus || isOne || isZero) {
            if (isMinus) {
                cntM++;
            } else if (isOne) {
                cnt_1++;
            } else {
                cnt_0++;
            }
            return;
        }
        tripleSearch(row, col, thirds);
        tripleSearch(row, col + thirds, thirds);
        tripleSearch(row, col + 2 * thirds, thirds);
        tripleSearch(row + thirds, col, thirds);
        tripleSearch(row + thirds, col + thirds, thirds);
        tripleSearch(row + thirds, col + 2 * thirds, thirds);
        tripleSearch(row + 2 * thirds, col, thirds);
        tripleSearch(row + 2 * thirds, col + thirds, thirds);
        tripleSearch(row + 2 * thirds, col + 2 * thirds, thirds);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        tripleSearch(0, 0, N);
        System.out.println(cntM);
        System.out.println(cnt_0);
        System.out.println(cnt_1);
    }
}
