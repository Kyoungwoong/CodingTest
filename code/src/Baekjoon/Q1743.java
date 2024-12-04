package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1743 {
    // https://www.acmicpc.net/problem/1743
    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int ans = Integer.MIN_VALUE, N, M;
    private static int[][] aisle;
    private static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || aisle[x][y] == 0) return false;

        return true;
    }

    public static void bfs(int x, int y) {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(x, y));
        visited[x][y] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int std = 1;

        while (!q.isEmpty()) {
            Dot cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    q.add(new Dot(nextX, nextY));
                    std++;
                }
            }
        }
        ans = Math.max(ans, std);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        aisle = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            aisle[r][c] = 1;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && aisle[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(ans);
    }
}
