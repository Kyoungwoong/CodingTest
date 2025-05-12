package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2636 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int OUT = 2, CHEESE = 1;
    private static int N, M, remain = 0;
    private static int[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Set<Integer> cheeseSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int cheeseCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == CHEESE) {
                    cheeseCnt++;
                    cheeseSet.add(i * M + j);
                }
            }
        }

        int time = 0;
        while (cheeseSet.size() > 0) {
            findOuter();
            melt();
            time++;
        }
        System.out.println(time + "\n" + remain);
    }

    private static void melt() {
        List<Integer> removeCheese = new ArrayList<>();
        for (int pos : cheeseSet) {
            int x = pos / M;
            int y = pos % M;
            boolean check = false;

            for (int idx = 0; idx < 4; idx++) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];
                if (inRange(nx, ny) && map[nx][ny] == OUT) {
                    check = true;
                }
                if (check) {
                    break;
                }
            }

            if (check) {
                map[x][y] = 0;
                removeCheese.add(pos);
            }
        }

        if (cheeseSet.size() == removeCheese.size()) {
            remain = removeCheese.size();
        }

        cheeseSet.removeAll(removeCheese);
    }

    private static void findOuter() {
        boolean[][] visited = new boolean[N][M];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        visited[0][0] = true;
        map[0][0] = OUT;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int idx = 0; idx < 4; idx++) {
                int nx = cur.x + dx[idx];
                int ny = cur.y + dy[idx];
                if (canGo(nx, ny, visited)) {
                    visited[nx][ny] = true;
                    map[nx][ny] = OUT;
                    q.add(new Pair(nx, ny));
                }
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y, boolean[][] visited) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == CHEESE) return false;
        return true;
    }
}
