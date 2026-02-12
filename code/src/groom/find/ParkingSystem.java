package groom.find;
import java.io.*;
import java.util.*;

class ParkingSystem {
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, M;
    private static int[][] parkingArea;
    private static boolean[][] visited;
    private static Queue<Pair> q;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        parkingArea = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                parkingArea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && (parkingArea[i][j] == 0 || parkingArea[i][j] == 2)) {
                    // 처음 방문하는 곳 + (장애인 주차구역 or 빈공간)
                    ans = Math.max(ans, bfs(i, j));
                }
            }
        }
        System.out.println(ans < 0 ? 0 : ans);
    }

    public static int bfs(int startX, int startY) {
        q = new LinkedList<>();
        q.add(new Pair(startX, startY));
        visited[startX][startY] = true;
        int score = parkingArea[startX][startY] == 0 ? 1 : -2;

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextX = cur.x + dx[dir];
                int nextY = cur.y + dy[dir];
                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                    score += parkingArea[nextX][nextY] == 0 ? 1 : -2;
                }
            }
        }
        return score;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || parkingArea[x][y] == 1) return false;
        return true;
    }
}
