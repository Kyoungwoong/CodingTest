package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2178 {
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (map[x][y] == 0 || visited[x][y]) return false;
        return true;
    }

    public static void bfs(){
        int[][] S = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                S[i][j] = Integer.MAX_VALUE;
            }
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0 ));
        visited[0][0] = true;
        S[0][0] = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY)) {
                    S[nextX][nextY] = Math.min(S[nextX][nextY], S[cur.x][cur.y] + 1);
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
        System.out.println(S[N - 1][M - 1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        bfs();
    }
}
