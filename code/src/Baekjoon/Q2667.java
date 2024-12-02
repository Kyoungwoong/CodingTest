package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2667 {
    // https://www.acmicpc.net/problem/2667
    private static boolean[][] visited;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int N;
    private static List<Integer> ans = new ArrayList<>();
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void bfs(int[][] map, int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (canGo(nextX, nextY)) {
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                    cnt++;
                }
            }
        }
        ans.add(cnt);
    }

    private static boolean canGo(int nextX, int nextY) {
        if (!(0 <= nextX && nextX < N && 0 <= nextY && nextY < N)) return false;
        if (visited[nextX][nextY] || map[nextX][nextY] == 0) return false;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(map, i, j);
                }
            }
        }

        System.out.println(ans.size());
        Collections.sort(ans);
        for (int result : ans) {
            System.out.println(result);
        }
    }
}
