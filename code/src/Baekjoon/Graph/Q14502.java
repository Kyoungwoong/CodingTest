package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.

연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.
 */
public class Q14502 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, M, maxSafeArea;
    private static int[][] lab;
    private static List<Pair> virus;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];
        visited = new boolean[N][M];
        virus = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 2) {
                    virus.add(new Pair(i, j));
                }
            }
        }

        // 벽 세개 세우기
        for (int i = 0; i < N * M - 2; i++) {
            if (lab[i / M][i % M] != 0) {
                continue;
            }
            lab[i / M][i % M] = 1;
            for (int j = i + 1; j < N * M - 1; j++) {
                if (lab[j / M][j % M] != 0) {
                    continue;
                }
                lab[j / M][j % M] = 1;
                for (int k = j + 1; k < N * M; k++) {
                    if (lab[k / M][k % M] != 0) {
                        continue;
                    }
                    lab[k / M][k % M] = 1;
//                    System.out.println("first: " + (i / M) + ", " + (i % M) +"\t" +
//                            "second: " + (j / M) + ", " + (j % M) +"\t" +
//                            "third: " + (k / M) + ", " + (k % M));
                    bfs();
                    lab[k / M][k % M] = 0;
                }
                lab[j / M][j % M] = 0;
            }
            lab[i / M][i % M] = 0;
        }

        System.out.println(maxSafeArea);
    }

    private static void getMaxSafeArea(int[][] lab) {
        int safeArea = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && lab[i][j] == 0) {
                    safeArea += bfs(i, j, lab);
                }
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safeArea);
//        System.out.println(safeArea);
    }

    private static int bfs(int x, int y, int[][] lab) {
        int count = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextX = cur.x + dx[dir];
                int nextY = cur.y + dy[dir];
                if (canGo(nextX, nextY, lab)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs() {
        visited = new boolean[N][M];
        int[][] copyLab = new int[N][M];
        for (int i = 0; i < N; i++) {
            copyLab[i] = Arrays.copyOf(lab[i], M);
        }

        Queue<Pair> q = new LinkedList<>();
        for (Pair p : virus) {
            q.add(p);
            visited[p.x][p.y] = true;
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nextX = cur.x + dx[dir];
                int nextY = cur.y + dy[dir];
                if (canGo(nextX, nextY, copyLab)) {
                    copyLab[nextX][nextY] = 2;
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                System.out.print(copyLab[i][j] + " ");
//            }
//            System.out.println();
//        }

        // 안전영역 크기 구하기
        getMaxSafeArea(copyLab);
    }

    private static boolean canGo(int x, int y, int[][] arr) {
        if (!inRange(x, y)) return false;
        if (arr[x][y] != 0 || visited[x][y]) return false;
        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
/*
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0
---
27
 */
