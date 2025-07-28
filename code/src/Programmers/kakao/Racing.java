package Programmers.kakao;

import java.util.*;

public class Racing {
    public static void main(String[] args) {

        System.out.println(Solution.solution(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(Solution.solution(new int[][] {{0,0,0,0,0,0,0,1},
                                                            {0,0,0,0,0,0,0,0},
                                                            {0,0,0,0,0,1,0,0},
                                                            {0,0,0,0,1,0,0,0},
                                                            {0,0,0,1,0,0,0,1},
                                                            {0,0,1,0,0,0,1,0},
                                                            {0,1,0,0,0,1,0,0},
                                                            {1,0,0,0,0,0,0,0}}));
        System.out.println(Solution.solution(new int[][] {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}}));
    }
    /**
     * 1. N X N 격자
     * 2. 0과 1로 채워져 있음
     *   1. 0은 칸이 비어있음
     *   2. 1은 칸이 벽으로 채워져 있음
     * 3. (0, 0)에서 출발하여 (N-1, N-1)
     *   1. 중간에 끊기지 않게 경주로를 건설
     * 4. 경주로는 상하좌우로 인접한 두 빈칸을 연결할 수 있음.
     *   1. 벽이 있을 경우 연결 못함.
     * 5. 인접한 두 빈칸을 (상, 하), (좌, 우)로 연결하면 직선도로
     *   1. 직선도로는 100원
     * 6. 직선도로가 서로 직각으로 만나는 지점 코너
     *   1. 코너는 500원이 추가로
     * 7. 최소 비용 계산
     */
    static class Solution {
        private static int len;
        private static int[][] map;
        private static boolean[][] visited;
        private static int answer = Integer.MAX_VALUE;
        private static int[] dx = {-1, 0, 1, 0};
        private static int[] dy = {0, 1, 0, -1};

        public static int solution(int[][] board) {
            init(board);

            dfs(0, 0, 0, true);

            return answer;
        }

        private static void dfs(int x, int y, int fee, boolean updownFlag) {
            if (x == len-1 && y == len - 1) {
                answer = Math.min(answer, fee);
                return;
            }

//            System.out.println("x: " + x + " y: " + y +"\t fee: " + fee + " flag: " + (updownFlag ? "updown" : "leftright"));

            for (int dir = 0; dir < 4; dir++) {
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];

                if (canGo(nextX, nextY)) {
                    visited[nextX][nextY] = true;

                    if (x == 0 && y == 0) {
                        if (dir == 1 || dir == 3) {
                            updownFlag = false;
                        } else {
                            updownFlag = true;
                        }
                        dfs(nextX, nextY, fee + 100, updownFlag);
                    } else {
                        if (updownFlag && (dir == 1 || dir == 3)) {
                            dfs(nextX, nextY, fee + 600, false);
                        } else if(!updownFlag && (dir == 0 || dir == 2)) {
                            dfs(nextX, nextY, fee + 600, true);
                        } else {
                            dfs(nextX, nextY, fee + 100, updownFlag);
                        }
                    }
                    visited[nextX][nextY] = false;
                }
            }
        }

        private static boolean canGo(int x, int y) {
            if (!inRange(x, y)) return false;
            if (visited[x][y] || map[x][y] == 1) return false;

            return true;
        }

        private static boolean inRange(int x, int y) {
            return 0 <= x && x < len && 0 <= y && y < len;
        }

        private static void init(int[][] board) {
            answer = Integer.MAX_VALUE;

            len = board.length;

            map = new int[len][len];
            for(int i = 0; i < len; i++) {
                for(int j = 0; j < len; j++) {
                    map[i][j] = board[i][j];
                }
            }

            visited = new boolean[len][len];
            visited[0][0] = true;
        }
    }
}
