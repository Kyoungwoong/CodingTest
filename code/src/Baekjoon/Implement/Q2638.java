package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2638 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair p = (Pair) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static int N, M;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static List<Pair> cheese = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    cheese.add(new Pair(i, j));
                }
            }
        }

        findOuter();

        int time = 0;
        while (!cheese.isEmpty()) {
            findOuter();
            List<Pair> toMelt = new ArrayList<>();
            for (Pair p : cheese) {
                int adjOut = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nx = p.x + dx[dir];
                    int ny = p.y + dy[dir];
                    if (inRange(nx, ny) && map[nx][ny] == 2) {
                        adjOut++;
                    }
                }
                if (adjOut >= 2) {
                    toMelt.add(p);
                }
            }

            for (Pair p : toMelt) {
                map[p.x][p.y] = 2;
                cheese.remove(p);
            }

            time++;
        }
        System.out.println(time);
    }

    // 외부를 2로 만들어버리자.
    private static void findOuter() {
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        map[0][0] = 2;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int x = cur / M;
            int y = cur % M;

            for (int idx = 0; idx < 4; idx++) {
                int nextX = x + dx[idx];
                int nextY = y + dy[idx];
                if (canGo(nextX, nextY, visited)) {
                    q.add(nextX * M + nextY);
                    visited[nextX][nextY] = true;
                    map[nextX][nextY] = 2;
                }
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y, boolean[][] visited) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == 1) return false;
        return true;
    }
}
