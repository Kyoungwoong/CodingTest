package SWExpert.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1249 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int testCase, N;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] step;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            step = new int[N][N];

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                Arrays.fill(step[i], Integer.MAX_VALUE);
                for (int j = 0; j < N; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            bfs(0, 0);

            sb.append("#").append(t).append(" ").append(step[N - 1][N - 1]);
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        step[0][0] = 0;
        visited[0][0] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];

                if (canGo(cur.x, cur.y, nextX, nextY)) {
                    step[nextX][nextY] = step[cur.x][cur.y] + map[nextX][nextY];
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    private static boolean canGo(int prevX, int prevY, int x, int y) {
        if(!inRange(x, y)) return false;
        if(step[x][y] <= step[prevX][prevY] + map[x][y]) return false;

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
/*
2
4
0100
1110
1011
1010
6
011001
010100
010011
101001
010101
111010
 */