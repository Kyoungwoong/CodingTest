package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1303 {
    // https://www.acmicpc.net/problem/1303
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static char[][] ground;

    private static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    private static int[] dy = {0, 1, 0, -1};
    private static int M, N; // M: 행, N: 열
    private static boolean[][] visited;
    private static Queue<Pair> q;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N; // M: 행, N: 열
    }

    public static boolean canGo(int x, int y, char prevWarrior) {
        if (!inRange(x, y)) return false;
        if (ground[x][y] != prevWarrior || visited[x][y]) return false;
        return true;
    }

    public static int bfs(int x, int y) {
        int sum = 1;
        q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY, ground[cur.x][cur.y])) {
                    q.add(new Pair(nextX, nextY));
                    visited[nextX][nextY] = true;
                    sum++;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[M][N];
        ground = new char[M][N];
        for (int i = 0; i < M; i++) { // M: 행
            String warriors = br.readLine();
            for (int j = 0; j < N; j++) { // N: 열
                ground[i][j] = warriors.charAt(j);
            }
        }

        int ourPower = 0;
        int oppPower = 0;
        for (int i = 0; i < M; i++) { // 행 순회
            for (int j = 0; j < N; j++) { // 열 순회
                if (!visited[i][j]) {
                    int power = bfs(i, j);
                    int powPower = power * power;
                    if (ground[i][j] == 'W') {
                        ourPower += powPower;
                    } else {
                        oppPower += powPower;
                    }
                }
            }
        }

        System.out.println(ourPower + " " + oppPower);
    }
}
