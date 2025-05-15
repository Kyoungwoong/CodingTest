package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q2665 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N;
    private static int[][] miro;
    private static int[][] step;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        miro = new int[N][N];
        step = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(step[i], Integer.MAX_VALUE);
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                miro[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(step[N - 1][N - 1]);
    }

    private static void bfs() {
        Deque<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        step[0][0] = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int idx = 0; idx < 4; idx++) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                if (inRange(nx, ny)) {
                    if (miro[nx][ny] == 1 && step[nx][ny] > step[x][y]) {
                        step[nx][ny] = step[x][y];
                        q.addFirst(new Pair(nx, ny));
                    } else if (miro[nx][ny] == 0 && step[nx][ny] > step[x][y] + 1) {
                        step[nx][ny] = step[x][y] + 1;
                        q.addLast(new Pair(nx, ny));
                    }
                }
            }
        }
    }
}
