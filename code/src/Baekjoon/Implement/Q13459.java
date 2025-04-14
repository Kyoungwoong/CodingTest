package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q13459 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static char[][] map;
    private static Pair hole, red, blue;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

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

        /**
         * point: 그쪽에 구슬이 있으면 그쪽까지 가지 못함. 그 전칸에 멈춤.
         */
        dfs(red, blue, 0);

        System.out.println(0);
    }

    public static void dfs(Pair redPos, Pair bluePos, int cnt) {
        if (cnt >= 10) return;

        for (int dir = 0; dir < 4; dir++) {
            int redX = redPos.x, redY = redPos.y;
            int blueX = bluePos.x, blueY = bluePos.y;
            boolean redHole = false, blueHole = false;

            int[] nextRed = move(redPos, dir);
            redX = nextRed[0];
            redY = nextRed[1];
            redHole = (redX == hole.x && redY == hole.y);

            int[] nextBlue = move(bluePos, dir);
            blueX = nextBlue[0];
            blueY = nextBlue[1];
            blueHole = (blueX == hole.x && blueY == hole.y);

            if (blueHole) {
                continue;
            }

            if (redHole) {
                System.out.println(1);
                System.exit(0);
            }

            if (redX == blueX && redY == blueY) {
                if (moveRedFirs(redPos, bluePos, dir)) {
                    blueX -= dx[dir];
                    blueY -= dy[dir];
                } else {
                    redX -= dx[dir];
                    redY -= dy[dir];
                }
            }

            dfs(new Pair(redX, redY), new Pair(blueX, blueY), cnt + 1);
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

    public static boolean moveRedFirs(Pair red, Pair blue, int dir) {
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

    public static boolean canGo(int x, int y) {
        if (!(0 <= x && x < N && 0 <= y && y < M)) return false;
        if (map[x][y] == '#') return false;
        return true;
    }
}
