package Baekjoon.DivideConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Q1992 {
    private static int[][] arr;
    private static StringBuilder sb = new StringBuilder();

    public static void QuadTree(int row, int col, int size) {
        int half = size / 2;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[row][col] != arr[i][j]) {
                    sb.append('(');
                    QuadTree(row, col, half);
                    QuadTree(row, col + half, half);
                    QuadTree(row + half, col, half);
                    QuadTree(row + half, col + half, half);
                    sb.append(')');
                    return;
                }
            }
        }
        sb.append(arr[row][col]);
    }

    public static void binary(int row, int col, int size) {
        if (size == 1) {
            if (arr[row][col] == 1) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            return;
        }

        int half = size / 2;
        boolean isZero = true;
        boolean isOne = true;
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] == 1) {
                    isZero = false;
                } else {
                    isOne = false;
                }
            }
        }

        if (isOne || isZero) {
            if (isOne) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            return;
        }

        sb.append("(");
        binary(row, col, half);
        binary(row, col + half, half);
        binary(row + half, col, half);
        binary(row + half, col + half, half);
        sb.append(")");
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        binary(0, 0, N);
        System.out.println(sb);
    }
}
