package Programmers.BFSDFS;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPath {

//    private static int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}}; // 11
    private static int[][] maps = {{1, 0, 1, 1, 1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}}; // -1

    private static boolean[][] visited;
    private static int[][] s;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int n, m;

    public static void main(String[] args) {
//        prev();
        int ans = nov20();
    }

    private static int nov20() {
        int n = maps.length;
        int m = maps[0].length;

        int[][] step = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        visited[0][0] = true;
        step[0][0] = 1;

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if ((0 <= nextX && nextX < n && 0 <= nextY && nextY < m) &&
                        maps[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                    step[nextX][nextY] = step[cur.x][cur.y] + 1;
                }
            }
        }

        if (step[n-1][m-1] == 0) {
            return -1;
        }
        return step[n - 1][m - 1];
    }

    private static void prev() {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        s = new int[n][m];

        bfs(0, 0);

        System.out.println("s[n-1][m-1] = " + s[n - 1][m - 1]);
    }

    public static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        visited[x][y] = true;
        s[x][y] = 1;
        q.add(new Pair(0, 0));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY)) {
                    s[nextX][nextY] = s[cur.x][cur.y] + 1;
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    private static boolean canGo(int nextX, int nextY) {
        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) return false;
        if (maps[nextX][nextY] == 0 || visited[nextX][nextY]) return false;
        return true;
    }

    public static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
