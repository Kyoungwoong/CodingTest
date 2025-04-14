package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q14503 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] room;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int initX, initY, dir;
    private static int cleanedCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(br.readLine());
        initX = Integer.parseInt(st.nextToken());
        initY = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleanRoom(initX, initY, dir);

        System.out.println(cleanedCount);
    }

    public static void cleanRoom(int x, int y, int dir) {
        if (room[x][y] == 0) {
            room[x][y] = 2; // 청소 완료 표시
            cleanedCount++;  // ✅ 청소한 칸 개수 증가
        }

        // ✅ 4방향 탐색
        for (int i = 0; i < 4; i++) {
            dir = (dir + 3) % 4; // ✅ 반시계 방향 회전
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            if (inRange(nextX, nextY) && room[nextX][nextY] == 0) { // ✅ 청소되지 않은 공간 발견
                cleanRoom(nextX, nextY, dir);
                return;
            }
        }

        // ✅ 4방향 모두 청소되어 있다면 후진
        int backX = x + dx[(dir + 2) % 4];
        int backY = y + dy[(dir + 2) % 4];

        // ✅ 후진 가능하면 이동
        if (inRange(backX, backY) && room[backX][backY] != 1) {
            cleanRoom(backX, backY, dir);
        }
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) {
            return false;
        }
        if (visited[x][y] || room[x][y] == 1) {
            return false;
        }
        return true;
    }
}
