package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1890 {
    // https://www.acmicpc.net/problem/1890
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] d = new long[N][N];
        // init
        d[N-1][N-1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            if (i + map[i][N - 1] < N) {
                d[i][N - 1] += d[i + map[i][N-1]][N-1];
            }

            if (i + map[N - 1][i] < N) {
                d[N - 1][i] += d[N-1][i + map[N-1][i]];
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            // 위쪽 체크
            if (i + map[i][i] < N) {
                d[i][i] = d[i + map[i][i]][i];
                d[i][i] += d[i][i + map[i][i]];
            } else {
                d[i][i] = 0;
            }

            for (int j = i - 1; j >= 0; j--) {
                // j,i => 1,2 0,2
                // 아래로 점프
                if (j + map[j][i] < N) {
                    d[j][i] += d[j + map[j][i]][i];
                }
                // 오른쪽 점프
                if (i + map[j][i] < N) {
                    d[j][i] += d[j][i + map[j][i]];
                }
            }

            // 왼쪽 체크
            for (int j = i - 1; j >= 0; j--) {
                // i,j => 2,1 2,0
                // 아래로 점프
                if (i + map[i][j] < N) {
                    d[i][j] += d[i + map[i][j]][j];
                }

                // 오른쪽 이동
                if (j + map[i][j] < N) {
                    d[i][j] += d[i][j + map[i][j]];
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(d[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(d[0][0]);
    }
}