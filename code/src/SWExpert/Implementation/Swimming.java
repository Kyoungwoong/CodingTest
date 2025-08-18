package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Swimming {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int tc, N, ans;
    private static int[][] sea, step;
    private static Pair start, end;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            sea = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    sea[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            end = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            ans = solve(start);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(step[i][j] + " ");
                }
                System.out.println();
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int solve(Pair pair) {
        step = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(step[i], Integer.MAX_VALUE);
        }
        Queue<Pair> q = new LinkedList<>();
        q.add(pair);
        step[pair.x][pair.y] = sea[pair.x][pair.y];

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int time = step[cur.x][cur.y];

            for (int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if (inRange(nextX, nextY) && sea[nextX][nextY] != 1) {
                    int nextTime = time + 1;

                    if (sea[nextX][nextY] == 2) {
                        int wait = (3 - (nextTime % 3)) % 3;
                        nextTime += wait;
                    }

                    if (nextTime < step[nextX][nextY]) {
                        step[nextX][nextY] = nextTime;
                        q.add(new Pair(nextX, nextY));
                    }
                }
            }
        }
        return step[end.x][end.y] == Integer.MAX_VALUE ? -1 : step[end.x][end.y];
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
/*
1
5
0 0 0 0 0
0 0 0 1 0
2 2 0 1 0
2 2 1 1 0
0 0 0 0 0
3 0
1 0
 */
