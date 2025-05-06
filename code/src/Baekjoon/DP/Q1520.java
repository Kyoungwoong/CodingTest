package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1520 {
    private static int M, N;
    private static int[][] dp, map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static final int INVALID = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], INVALID);
        }
        dp[M-1][N-1] = 1;

        dfs(0, 0);

//        for (int i = 0; i < M; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[0][0]);
    }

    private static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }
        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for (int idx = 0; idx < 4; idx++) {
            int nextX = x + dx[idx];
            int nextY = y + dy[idx];
            if (canGo(nextX, nextY, x, y)) {
                dp[x][y] += dfs(nextX, nextY);
            }
        }
        return dp[x][y];
    }

    private static boolean canGo(int nx, int ny, int x, int y) {
        if (!inRange(nx, ny)) {
            return false;
        }
        if (map[nx][ny] >= map[x][y]) {
            return false;
        }
        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}
