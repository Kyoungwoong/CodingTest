package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q10026 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N;
    private static char[][] picture;
//    private static boolean[][] visited;
//    private static boolean[][] weaknessVisited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                picture[i][j] = line.charAt(j);
            }
        }

        boolean[][] visited = new boolean[N][N];
        boolean[][] weaknessVisited = new boolean[N][N];

        StringBuilder sb = new StringBuilder();
        int normal = 0, weakness = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    color(i, j, visited, false);
                    normal++;
                }

                if (!weaknessVisited[i][j]) {
                    color(i, j, weaknessVisited, true);
                    weakness++;
                }
            }
        }
        System.out.println(normal + " " + weakness);
    }

    private static void color(int x, int y, boolean[][] visited, boolean isWeak) {
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nx = cur.x + dx[idx];
                int ny = cur.y + dy[idx];
                if (canGo(nx, ny, picture[cur.x][cur.y], visited, isWeak)) {
                    q.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static boolean canGo(int x, int y, char prevColor,
                                 boolean[][] visited, boolean isWeak) {
        if (!inRange(x, y)) return false;
        if (visited[x][y]) return false;

        char curColor = picture[x][y];
        if (isWeak) {
            if (prevColor == 'B' && (curColor == 'R' || curColor == 'G')) {
                return false;
            } else if ((prevColor == 'R' || prevColor == 'G') && curColor == 'B') {
                return false;
            }
        } else {
            if (prevColor != curColor) {
                return false;
            }
        }

        return true;
    }
}
