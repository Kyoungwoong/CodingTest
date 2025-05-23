package Baekjoon.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] miro, step;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N][M];
        step = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                miro[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(step[N - 1][M - 1] + 1);
    }

    public static void bfs() {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(0, 0));
        visited[0][0] = true;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Dot cur = q.poll();
            for (int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    step[nextX][nextY] = step[cur.x][cur.y] + 1;
                    q.add(new Dot(nextX, nextY));
                }
            }
        }
    }

    private static boolean canGo(int x, int y) {
        if(!inRange(x, y)) return false;
        if(visited[x][y] || miro[x][y] == 0) return false;
        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
