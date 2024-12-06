package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Q17086 {
    // https://www.acmicpc.net/problem/17086
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int[][] sea, safeArea;
    private static List<Pair> sharks = new ArrayList<>();

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y, int step) {
        if(!inRange(x, y)) return false;
        if(sea[x][y] == 1 || step >= safeArea[x][y]) return false;
        return true;
    }

    public static void bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
//            System.out.println("x: " + cur.x + " y: " + cur.y);

            for (int i = 0; i < 8; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY, safeArea[cur.x][cur.y] + 1)) {
                    safeArea[nextX][nextY] = safeArea[cur.x][cur.y] + 1;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sea = new int[N][M];
        safeArea = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(safeArea[i], Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] == 1) {
                    sharks.add(new Pair(i, j));
                    safeArea[i][j] = 0;
                }
            }
        }

        for (Pair shark : sharks) {
            bfs(shark.x, shark.y);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = Math.max(ans, safeArea[i][j]);
            }
        }
        System.out.println(ans);
    }
}
