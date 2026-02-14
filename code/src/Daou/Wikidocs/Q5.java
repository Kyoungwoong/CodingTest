package Daou.Wikidocs;

import java.util.*;

public class  Q5 {

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static boolean inRange(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private static boolean canGo(int x, int y, int[][] tornado, int n) {
        if (!inRange(x, y, n)) return false;
        if (tornado[x][y] != 0) return false;
        return true;
    }

    private static boolean canGo(int x, int y, int[][] tornado) {
        for (int dir = 0; dir < 4; dir++) {
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];
            if (inRange(nextX, nextY, tornado.length) && tornado[nextX][nextY] == 0) {
                return true;
            }
        }
        return false;
    }

    public static int solution(int n) {
        int answer = 0;

        int[][] tornado = new int[n][n];
        int x = 0, y = 0, dir = 1;
        for (int idx = 1; idx <= n*n; idx++) {
            if (x == y) answer += idx;
            tornado[x][y] = idx;

            int nx = x + dx[dir], ny = y + dy[dir];
            if (!canGo(nx, ny, tornado, n)) dir = (dir + 1) % 4;

            x += dx[dir];
            y += dy[dir];
        }

        return answer;
    }

    public static void main(String[] args) {

        int n1 = 3;
        int ret1 = solution(n1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int n2 = 2;
        int ret2 = solution(n2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
