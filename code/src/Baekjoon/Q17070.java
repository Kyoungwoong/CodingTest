package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17070 {
    // https://www.acmicpc.net/problem/17070
    private static int[][] wall;
    private static int ans = 0, N;
    private static int[] dx = {0, 1, 1};
    private static int[] dy = {1, 0, 1};

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static boolean canGo(int x, int y, int prevDir, int nextDir) {
        if (!inRange(x, y) || wall[x][y] == 1) return false;

        if (prevDir == 0 && nextDir == 1) return false;
        if (prevDir == 1 && nextDir == 0) return false;

        if (nextDir == 2) {
            if (wall[x - 1][y] == 1 || wall[x][y - 1] == 1) return false;
        }

        return true;
    }

    public static void dfs(int x, int y, int dir) {
        if (x == N - 1 && y == N - 1) {
            ans++;
            return;
        }

        for (int i = 0; i < 3; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (canGo(nextX, nextY, dir, i)) {
                dfs(nextX, nextY, i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        wall = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                wall[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);

        System.out.println(ans);
    }
}
