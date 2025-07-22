package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Mine {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int tc, N;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<Pair> q;
    private static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < N; j++) {
                    char mine = line.charAt(j);
                    if (mine == '.') {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = -1;
                    }
                }
            }

            buildMineCnt();

            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] == 0) {
                        solve(i, j);
                        ans++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != -1 && !visited[i][j]) {
                        ans++;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int x, int y) {
        visited[x][y] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int idx = 0; idx < 8; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    if (map[nextX][nextY] == 0) {
                        q.add(new Pair(nextX, nextY));
                    }
                }
            }
        }
    }

    private static void buildMineCnt() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -1) {
                    continue;
                }
                int cnt = 0;
                for (int idx = 0; idx < 8; idx++) {
                    int nextX = i + dx[idx];
                    int nextY = j + dy[idx];
                    if (inRange(nextX, nextY) && map[nextX][nextY] == -1) {
                        cnt++;
                    }
                }
                map[i][j] = cnt;
            }
        }
    }

    private static boolean canGo(int x, int y) {
        if(!inRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == -1) return false;
        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

/*
2
3
..*
..*
**.
5
..*..
..*..
.*..*
.*...
.*...
 */