package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1012 {
    private static int N, M, K;
    private static Queue<Pair> q = new LinkedList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] board;
    private static boolean[][] visited;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (board[x][y] == 0 || visited[x][y]) return false;
        return true;
    }

    public static void bfs(int row, int col) {
        visited[row][col] = true;
        q.add(new Pair(row, col));

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            board = new int[M][N];
            visited = new boolean[M][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[a][b] = 1;
            }

            int cnt = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        cnt++;
                        bfs(i, j);
                    }
                }
            }
            sb.append(cnt + "\n");
        }
        System.out.println(sb);

    }
}
