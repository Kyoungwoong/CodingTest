package groom.data_structure;

import java.io.*;
import java.util.*;

public class Fire {
    static class Pair {
        int x, y;
        public Pair (int x, int y) {
            this.x = x;
            this.y= y;
        }
    }
    private static int R, C;
    private static boolean[][] visited;
    private static char[][] map;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static List<Pair> start;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
    public static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == '#') return false;
        return true;
    }
    public static void main(String[] args) throws Exception {
        input();

        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < start.size(); i++) {
            q.add(start.get(i));
            visited[start.get(i).x][start.get(i).y] = true;
        }

        int ans = 0;
        boolean flag = false;
        while (!q.isEmpty()) {
            Queue<Pair> next = new LinkedList<>();
            while (!q.isEmpty()) {
                Pair cur = q.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nextX = cur.x + dx[dir];
                    int nextY = cur.y + dy[dir];
                    if (canGo(nextX, nextY)) {
                        if (map[nextX][nextY] == '&') {
                            System.out.println(ans);
                            System.exit(0);
                        }
                        next.add(new Pair(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }
            ans++;
            q = next;
        }
        System.out.println(-1);
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        start = new ArrayList<>();
        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == '@') {
                    start.add(new Pair(r, c));
                }
            }
        }
    }
}
