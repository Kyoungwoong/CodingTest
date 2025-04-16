package Baekjoon.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1012 {
    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M, N, K;
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int[][] land = new int[M][N];
            boolean[][] visited = new boolean[M][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                land[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (land[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j, M, N, land, visited);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void bfs(int x, int y, int M, int N, int[][] land, boolean[][] visited) {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Dot cur = q.poll();
            for (int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if (canGo(nextX, nextY, M, N, land, visited)) {
                    visited[nextX][nextY] = true;
                    q.add(new Dot(nextX, nextY));
                }
            }
        }
    }

    public static boolean canGo(int x, int y, int M, int N, int[][] land, boolean[][] visited) {
        if (!inRange(x, y, M, N)) return false;
        if (visited[x][y] || land[x][y] == 0) return false;
        return true;
    }

    public static boolean inRange(int x, int y, int M, int N) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}
