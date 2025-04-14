package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    // https://www.acmicpc.net/problem/2178
    static class Dot {
        int x, y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, M;
    private static int[][] map, step;
    private static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == 0) return false;

        return true;
    }

    public static void bfs() {
        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(0, 0));
        step[0][0] = 1;
        visited[0][0] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Dot cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    step[nextX][nextY] = step[cur.x][cur.y] + 1;
                    q.add(new Dot(nextX, nextY));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        prev();
        prepareNaver();
    }

    public static void prepareNaver() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        step = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        Queue<Dot> q = new LinkedList<>();
        q.add(new Dot(0, 0));
        visited[0][0] = true;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Dot cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY)) {
                    step[nextX][nextY] = step[cur.x][cur.y] + 1;
                    visited[nextX][nextY] = true;
                    q.add(new Dot(nextX, nextY));
                }
            }
        }

        System.out.println(step[N - 1][M - 1] + 1);
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        step = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(step[N - 1][M - 1]);
    }
}
