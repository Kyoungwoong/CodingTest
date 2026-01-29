package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q4963 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int w, h;
    static int[][] maps;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};

    // 1은 땅, 0은 바다
    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }

            maps = new int[h][w];
            visited = new boolean[h][w];

            for (int row = 0; row < h; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < w; col++) {
                    maps[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            int landCount = 0;
            for (int row = 0; row < h; row++) {
                for (int col = 0; col < w; col++) {
                    if (!visited[row][col] && maps[row][col] == 1) {
                        solve(row, col);
                        landCount++;
                    }
                }
            }
            sb.append(landCount).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void solve(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int idx = 0; idx < 8; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];

                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || maps[x][y] == 0) return false;

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
