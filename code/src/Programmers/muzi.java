// Don't place your source in a package
package Programmers;

import java.util.*;
// Please name your class Main

public class muzi {
    static class Node {
        int x, y, dir;
        // dir이 0이면 왼쪽에서 오른쪽
        // dir이 1이면 위에서 아래로
        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static int MOD = 20170805;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static int[][] s;
    static Queue<Node> q;
//    static int m = 3;
//    static int n = 3;
//    static int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
    static int m = 3;
    static int n = 6;
    static int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};

    public static void init(int m, int n) {
        s = new int[m][n];
        q = new LinkedList<>();
        q.add(new Node(0, 0, -1));
        s[0][0] = 1;
    }

    public static boolean inRange(int x, int y, int m, int n) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    public static void main(String[] args) {
        int answer = 1;
        System.out.println(answer);
        init(m, n);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.print("X: " + cur.x + " Y: " + cur.y + " s: " + s[cur.x][cur.y]);
            for (int i = 0; i < 2; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (inRange(nextX, nextY, m, n)) {
                    if (cityMap[nextX][nextY] == 0) {
                        if (s[nextX][nextY] == 0) {
                            q.add(new Node(nextX, nextY, i));
                        }
                        s[nextX][nextY] += s[cur.x][cur.y] % MOD;
                        // s[nextX][nextY] += (1) % MOD;
                    } else if (cityMap[nextX][nextY] == 2) {
                        if (cur.dir == i) {
                            if (s[nextX][nextY] == 0) {
                                q.add(new Node(nextX, nextY, i));
                            }
                            s[nextX][nextY] += s[cur.x][cur.y] % MOD;
                            // s[nextX][nextY] += (1) % MOD;
                        }
                    }

                    System.out.print("\t nextX: " + nextX + " nextY: " + nextY);
                }

            }
            System.out.println();
        }
        // end bfs

        System.out.println("ans: " + s[m-1][n-1]);
    }
}