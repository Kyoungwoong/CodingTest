package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q13460 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Pair hole;
    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        Pair red = null, blue = null;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'O') {
                    hole = new Pair(i, j);
                } else if (map[i][j] == 'R') {
                    red = new Pair(i, j);
                } else if (map[i][j] == 'B') {
                    blue = new Pair(i, j);
                }
            }
        }

        dfs(red, blue, 1);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void dfs(Pair red, Pair blue, int cnt) {
        if (cnt > 10) {
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            boolean redHole = false, blueHole = false;

            int[] nextRed = move(red, dir);
            redHole = (nextRed[0] == hole.x && nextRed[1] == hole.y);

            int[] nextBlue = move(blue, dir);
            blueHole = (nextBlue[0] == hole.x && nextBlue[1] == hole.y);

            if (blueHole) continue;

            if (redHole) {
                ans = Math.min(ans, cnt);
                return;
            }

            // 먼저 가는거 체크
            if (nextRed[0] == nextBlue[0] && nextRed[1] == nextBlue[1]) {
                if (moveRedFirst(red, blue, dir)) {
                    nextBlue[0] -= dx[dir];
                    nextBlue[1] -= dy[dir];
                } else {
                    nextRed[0] -= dx[dir];
                    nextRed[1] -= dy[dir];
                }
            }

            dfs(new Pair(nextRed[0], nextRed[1]),
                    new Pair(nextBlue[0], nextBlue[1]), cnt + 1);
        }
    }

    public static boolean moveRedFirst(Pair red, Pair blue, int dir) {
        if (dir == 0) {
            return red.x < blue.x;
        } else if (dir == 1) {
            return red.y > blue.y;
        } else if (dir == 2) {
            return red.x > blue.x;
        } else {
            return red.y < blue.y;
        }
    }

    public static int[] move(Pair ball, int dir) {
        int x = ball.x;
        int y = ball.y;

        while (true) {
            x += dx[dir];
            y += dy[dir];
            if (!canGo(x, y)) {
                return new int[]{x - dx[dir], y - dy[dir]};
            }
            if (hole.x == x && hole.y == y) break;
        }

        return new int[]{x, y};
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (map[x][y] == '#') return false;
        return true;
    }
}
