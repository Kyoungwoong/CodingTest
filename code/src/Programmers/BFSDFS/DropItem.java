package Programmers.BFSDFS;

import java.util.LinkedList;
import java.util.Queue;

public class DropItem {
//    private static int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
//    private static int characterX = 1, characterY = 3, itemX = 7, itemY = 8; // 17

//    private static int[][] rectangle = {{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}};
//    private static int characterX = 9, characterY = 7, itemX = 6, itemY = 1; // 11

    private static int[][] rectangle = {{1,1,5,7}};
    private static int characterX = 1, characterY = 1, itemX = 4, itemY = 7; // 9

//    private static int[][] rectangle = {{2,1,7,5},{6,4,10,10}};
//    private static int characterX = 3, characterY = 1, itemX = 7, itemY = 10; // 15

//    private static int[][] rectangle = {{2,2,5,5},{1,3,6,4},{3,1,4,6}};
//    private static int characterX = 1, characterY = 4, itemX = 6, itemY = 3; // 10


    static class Pair {
        int x, y, cnt;

        public Pair(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    private static int answer = 0;
    private static final int SIZE = 101;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] map = new int[SIZE][SIZE];
    private static boolean[][] visited = new boolean[SIZE][SIZE];
    private static Pair item, character;
    private static Queue<Pair> q = new LinkedList<>();

    public static boolean inRange(int x, int y) {
        return 0 <= x && x <= SIZE - 1 && 0 <= y && y <= SIZE - 1;
    }

    public static void bfs() {
        visited[character.x][character.y] = true;
        q.add(character);

        while(!q.isEmpty()) {
            Pair cur = q.poll();

            if(cur.x == item.x && cur.y == item.y) {
                answer = cur.cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if(canGo(nx, ny)) {
                    q.add(new Pair(nx, ny, cur.cnt + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static boolean canGo(int x, int y) {
        if(!inRange(x, y)) return false;
        if(map[x][y] != 1 || visited[x][y]) return false;
        return true;
    }

    public static void main(String[] args) {
        int n = rectangle.length;
        // 도형 바깥쪽은 1, 안쪽은 2
        for(int i = 0; i < n; i++) {
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];
            for(int p = x1; p <= x2; p++) {
                for(int q = y1; q <= y2; q++) {
                    if(p == x1 || p == x2 || q == y1 || q == y2) {
                        if(map[p][q] == 0) {
                            map[p][q] = 1;
                        }
                    } else {
                        map[p][q] = 2;
                    }
                }
            }
        }

        item = new Pair(itemX, itemY, 0);
        character = new Pair(characterX, characterY, 0);

        bfs();

        System.out.println("answer = " + answer);
    }
}
