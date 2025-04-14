package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14503 {
    private static int N, M;
    private static int[][] room;
    private static boolean[][] visited;
    private static int ans = 0;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(r, c, d);

        System.out.println(ans);
    }

    public static void bfs(int r, int c, int d) {
        Queue<Dot> q = new LinkedList<>();
        visited[r][c] = true;
        q.add(new Dot(r, c));

        while (!q.isEmpty()) {
            Dot cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            if (room[x][y] == 0) { // case 1
                room[x][y] = 2;
                ans++;
            }

            boolean cleanUp = false;
            int dir = d;
            for (int idx = 0; idx < 4; idx++) {
                dir = (3 + dir) % 4; // 반시계 90도 회전
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];
                if (inRange(nextX, nextY) && room[nextX][nextY] == 0) { // case 3
                    visited[nextX][nextY] = true;
                    d = dir;
                    cleanUp = true;
                    q.add(new Dot(nextX, nextY));
                    break;
                }
            }

            if (!cleanUp) { // case 2
                dir = (d + 2) % 4;
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];
                if (inRange(nextX, nextY) && room[nextX][nextY] != 1) {
                    visited[nextX][nextY] = true;
                    q.add(new Dot(nextX, nextY));
                }
            }
        }
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || room[x][y] == 1) return false;
        return true;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
