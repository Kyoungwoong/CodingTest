package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Knight {
    int x, y, cnt;

    public Knight(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Q7562 {
    private static int N, destX, destY;
    private static StringBuilder sb = new StringBuilder();
    private static int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
    private static int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};
    private static Queue<Knight> q = new LinkedList<>();
    private static int[][] map;
    private static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y]) return false;
        return true;
    }

    public static void bfs(int x, int y) {
        q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Knight(x, y, 0));
        while (!q.isEmpty()) {
            Knight cur = q.poll();
            if (cur.x == destX && cur.y == destY) {
                sb.append(cur.cnt + "\n");
                return;
            }
            for (int i = 0; i < 8; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY)) {
                    q.add(new Knight(nextX, nextY, cur.cnt + 1));
                    visited[nextX][nextY] = true;
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            destX = Integer.parseInt(st.nextToken());
            destY = Integer.parseInt(st.nextToken());
            bfs(row, col);
        }
        System.out.println(sb);
    }
}
