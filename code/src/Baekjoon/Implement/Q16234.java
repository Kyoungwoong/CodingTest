package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Thread.sleep;

public class Q16234 {
    static class City {
        Pair idx;
        int cnt;

        public City(Pair idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    private static int N, L, R;
    private static int[][] cities;
    private static int ans = 0;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        cities = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        migrateCities();

        System.out.println(ans);
    }

    private static Set<Integer> united;
    private static boolean[][] visited;
    private static void migrateCities() {

        boolean check = true;
        while (check) {
            check = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            check = true;
                        }
                    }
                }
            }
            if (check) {
                ans++;
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
    private static boolean bfs(int x, int y) {
        united = new HashSet<>();
        int sum = cities[x][y];
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Pair(x, y));
        united.add(N * x + y);

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];

                if (canGo(nextX, nextY, cur.x, cur.y)) {
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                    sum += cities[nextX][nextY];
                    united.add(N * nextX + nextY);
                }
            }
        }

        if (united.size() > 1) {
            int size = united.size();
            int avg = sum / size;
            for (int idx : united) {
                cities[idx / N][idx % N] = avg;
            }
            return true;
        }
        return false;
    }

    private static boolean canGo(int nx, int ny, int x, int y) {
        if (!inRange(nx, ny)) return false;
        if (visited[nx][ny] || Math.abs(cities[nx][ny] - cities[x][y]) < L
                || Math.abs(cities[nx][ny] - cities[x][y]) > R) return false;
        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
