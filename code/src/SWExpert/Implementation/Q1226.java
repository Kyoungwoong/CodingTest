package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q1226 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int tc = 10, ROW = 16, COL = 16;
    private static int[][] miro;
    private static boolean[][] visited;
    private static Pair start, end;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            br.readLine();
            init();
            for (int i = 0; i < ROW; i++) {
                String line = br.readLine();
                for (int j = 0; j < COL; j++) {
                    miro[i][j] = line.charAt(j) - '0';
                    if (miro[i][j] == 2) {
                        start = new Pair(i, j);
                    } else if (miro[i][j] == 3) {
                        end = new Pair(i, j);
                    }
                }
            }

            adventure();

            sb.append("#").append(t).append(" ").append(visited[end.x][end.y] ? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }

    private static void adventure() {
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nx = cur.x + dx[idx];
                int ny = cur.y + dy[idx];
                if (canGo(nx, ny)) {
                    visited[nx][ny] = true;
                    q.add(new Pair(nx, ny));
                }
            }
        }
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) {
            return false;
        }

        if (visited[x][y] || miro[x][y] == 1) {
            return false;
        }

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < ROW && 0 <= y && y < COL;
    }

    private static void init() {
        miro = new int[ROW][COL];
        visited = new boolean[ROW][COL];
    }
}
