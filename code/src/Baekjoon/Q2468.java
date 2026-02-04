package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2468 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, limit = 0;
    static int[][] land;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        int ans = solve();
        System.out.println(ans);
    }

    private static int solve() {
        int result = 1;

        for (int height = 1; height < limit; height++) {
            result = Math.max(result, findSafeArea(height));
        }

        return result;
    }

    private static int findSafeArea(int height) {
        int result = 0;
        boolean[][] visited = new boolean[N][N];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (!visited[row][col]) {
                    if (land[row][col] > height) {
                        bfs(row, col, height, visited);
                        result++;
                    } else {
                        visited[row][col] = true;
                    }
                }
            }
        }

        return result;
    }


    private static void bfs(int row, int col, int height, boolean[][] visited) {
        if (!canGo(row, col, height, visited)) {
            return;
        }

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        visited[row][col] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nextX = cur.x + dx[dir];
                int nextY = cur.y + dy[dir];

                if (canGo(nextX, nextY, height, visited)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    private static boolean canGo(int x, int y, int height, boolean[][] visited) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || land[x][y] <= height) return false;

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        land = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                land[row][col] = Integer.parseInt(st.nextToken());
                limit = Math.max(limit, land[row][col]);
            }
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
