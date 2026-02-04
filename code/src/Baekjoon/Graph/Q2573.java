package Baekjoon.Graph;

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
    private static int n, m;
    private static int[][] sea;
    private static List<Pair> ice;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
//    private static Map<Integer, Integer> ice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        sea = new int[n][m];

//        ice = new HashMap<>();
        ice = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
                if (sea[i][j] != 0) {
//                    ice.put(i, j);
                    ice.add(new Pair(i, j));
                }
            }
        }

        for (int i = 0; ; i++) {
//            System.out.println(i);
//            for (int row = 0; row < n; row++) {
//                for (int col = 0; col < m; col++) {
//                    System.out.print(sea[row][col] + " ");
//                }
//                System.out.println();
//            }
            // 빙산이 분리되지 않고 다 녹음
            if (ice.size() == 0) {
                System.out.println(0);
                break;
            }
            // 빙산이 분리되었는지 확인
            if (checkSplit()) {
                System.out.println(i);
                break;
            }

            // 빙산 녹음
            // 빙산 복사
            int[][] nextYear = new int[n][m];
            List<Pair> nextIce = new ArrayList<>();
            for (Pair p : ice) {
                int count = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = p.x + dx[dir];
                    int nextY = p.y + dy[dir];
                    if (inRange(nextX, nextY) && sea[nextX][nextY] == 0) {
                        count++;
                    }
                }
                if (sea[p.x][p.y] > count) {
                    nextIce.add(p);
                    nextYear[p.x][p.y] = sea[p.x][p.y] - count;
                }
            }
            sea = nextYear;
            ice = nextIce;
        }
    }

    private static boolean checkSplit() {
        Pair start = ice.get(0);
        boolean[][] visited = new boolean[n][m];
        visited[start.x][start.y] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        int count = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nextX = cur.x + dx[dir];
                int nextY = cur.y + dy[dir];
                if (inRange(nextX, nextY) && (!visited[nextX][nextY] && sea[nextX][nextY] != 0)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                    count++;
                }
            }
        }

        // 같지 않으면 분리되었다.
        return count != ice.size();
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
/*
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
---
2
 */