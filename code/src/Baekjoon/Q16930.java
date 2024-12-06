package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q16930 {
    // https://www.acmicpc.net/problem/16930
    static class Pair {
        int x, y, time;

        public Pair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int N, M, K;
    static char[][] gym;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static int bfs(int x1, int y1, int x2, int y2) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x1, y1, 0));
        visited[x1][y1] = true;

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            if (cur.x == x2 && cur.y == y2) {
                return cur.time;
            }

            for (int dir = 0; dir < 4; dir++) {

                int dirX = cur.x - x2;
                int dirY = cur.y - y2;
                if ((dirX < 0 && dirY > 0) && !(dir == 2 || dir == 3)) {
                    continue;
                } else if ((dirX > 0 && dirY < 0) && !(dir == 0 || dir == 1)) {
                    continue;
                }

                for (int step = 1; step <= K; step++) {
                    int nx = cur.x + dx[dir] * step;
                    int ny = cur.y + dy[dir] * step;

                    if (!inRange(nx, ny) || gym[nx][ny] == '#') break;

                    if (visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    queue.add(new Pair(nx, ny, cur.time + 1));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gym = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            gym[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs(x1, y1, x2, y2));
    }
}
