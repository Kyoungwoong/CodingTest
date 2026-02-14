package Daou.Wikidocs;

import java.util.*;

public class Q6 {
    static class Knight {
        int row, col;
        int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
        int canGoCount = 0;

        public Knight(String pos) {
            col = pos.charAt(0) - 'A';
            row = pos.charAt(1) - '1';
//            System.out.println(row + " " + col);
            countRoad();
        }

        private void countRoad() {
            for (int dir = 0; dir < 8; dir++) {
                int nextX = row + dx[dir];
                int nextY = col + dy[dir];
                if (canGo(nextX, nextY)) {
                    canGoCount++;
                }
            }
        }

        private boolean canGo(int x, int y) {
            return 0 <= x && x < 8 && 0 <= y && y < 8;
        }
    }

    public static int solution(String pos) {
        int answer = 0;

        Knight k = new Knight(pos);
        return k.canGoCount;
    }

    public static void main(String[] args) {

        String pos = "A7";
        int ret = solution(pos);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
