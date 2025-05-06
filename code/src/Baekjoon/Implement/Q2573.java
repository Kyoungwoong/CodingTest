package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2573 {
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] sea;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Map<Integer, Integer> iceMap = new HashMap<>();
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sea = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] != 0) {
                    iceMap.put(i * M + j, sea[i][j]);
                }
            }
        }

        int year = 0;
        while (true) {
            int count = getIceCnt();

            if (count >= 2) break;
            if (iceMap.isEmpty()) {
                year = 0;
                break;
            }

            int[][] melt = new int[N][M];
            for (int key : iceMap.keySet()) {
                int x = key / M;
                int y = key % M;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (inRange(nx, ny) && sea[nx][ny] == 0) {
                        melt[x][y]++;
                    }
                }
            }

            Iterator<Integer> it = iceMap.keySet().iterator();
            while (it.hasNext()) {
                int key = it.next();
                int x = key / M;
                int y = key % M;

                sea[x][y] = Math.max(0, sea[x][y] - melt[x][y]);
                if (sea[x][y] == 0) {
                    it.remove();
                }
            }

            year++;
        }

        System.out.println(year);
    }

    private static int getIceCnt() {
        visited = new boolean[N][M];
        int count = 0;

        for (int key : iceMap.keySet()) {
            int x = key / M;
            int y = key % M;

            if (!visited[x][y]) {
                bfs(x, y);
                count++;
            }
        }

        return count;
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (canGo(nx, ny)) {
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }
        }
    }

    private static boolean canGo(int x, int y) {
        return inRange(x, y) && sea[x][y] > 0 && !visited[x][y];
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
