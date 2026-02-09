package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1952 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int M, N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        boolean[][] visited = new boolean[M][N];

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int dir = 1, ans = 0;

        int x = 0, y = 0;
        visited[x][y] = true;   // 시작 칸 방문

        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (canGo(nx, ny, visited)) {
                // 현재 방향으로 전진 가능 → 이동
                x = nx;
                y = ny;
                visited[x][y] = true;
            } else {
                // 현재 방향으로 불가 → 시계방향 회전 후 갈 수 있으면 회전 1회 카운트
                int ndir = (dir + 1) % 4;
                int nx2 = x + dx[ndir];
                int ny2 = y + dy[ndir];

                if (canGo(nx2, ny2, visited)) {
                    dir = ndir;   // 실제로 회전해서
                    ans++;        // 회전 횟수 증가
                    x = nx2;      // 한 칸 이동까지 수행
                    y = ny2;
                    visited[x][y] = true;
                } else {
                    // 회전해도 못 가면 끝
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    private static boolean canGo(int x, int y, boolean[][] visited) {
        if (!inRange(x, y)) {
            return false;
        }

        if (visited[x][y]) return false;

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < M && 0 <= y && y < N;
    }
}
