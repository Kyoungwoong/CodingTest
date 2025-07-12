package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1949 {
    static class Horn {
        int x, y, height;

        public Horn(int x, int y) {
            this.x = x;
            this.y = y;
            this.height = 0;
        }

        public Horn(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
    private static int tc, N, K, ans;
    private static int[][] mountain;
    private static List<Horn> maxHorns;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            mountain = new int[N][N];
            maxHorns = new ArrayList<>();
            int max = -1;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    mountain[i][j] = Integer.parseInt(st.nextToken());
                    if (mountain[i][j] > max) {
                        maxHorns = new ArrayList<>();
                        max = mountain[i][j];
                        maxHorns.add(new Horn(i, j));
                    } else if (mountain[i][j] == max) {
                        maxHorns.add(new Horn(i, j));
                    }
                }
            }

            ans = Integer.MIN_VALUE;
            for (Horn h : maxHorns) {
                boolean[][] visited = new boolean[N][N];
                dfs(h.x, h.y, 1, false, visited);
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y, int len, boolean cut, boolean[][] visited) {
        ans = Math.max(ans, len);
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!inRange(nx, ny) || visited[nx][ny]) continue;

            if (mountain[nx][ny] < mountain[x][y]) {
                dfs(nx, ny, len + 1, cut, visited);
            } else if (!cut && mountain[nx][ny] - K < mountain[x][y]) {
                int original = mountain[nx][ny];
                mountain[nx][ny] = mountain[x][y] - 1; // 깎는다
                dfs(nx, ny, len + 1, true, visited);
                mountain[nx][ny] = original; // 원상 복구
            }
        }

        visited[x][y] = false;
    }

    private static void dfs(int x, int y, int length, int cutCount, boolean[][] visited) {
        ans = Math.max(ans, length);
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny) || visited[nx][ny]) continue;

            if (mountain[nx][ny] < mountain[x][y]) {
                dfs(nx, ny, length + 1, cutCount, visited);
            } else {
                // 깎을 수 있는 경우
                if (cutCount < K) {
                    int original = mountain[nx][ny];
                    // 제한 없이 깎을 수 있으므로 그냥 현재보다 1 작게
                    mountain[nx][ny] = mountain[x][y] - 1;
                    dfs(nx, ny, length + 1, cutCount + 1, visited);
                    mountain[nx][ny] = original;
                }
            }
        }

        visited[x][y] = false;
    }


    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
/*
10
5 1
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
3 2
1 2 1
2 1 2
1 2 1
5 2
9 3 2 3 2
6 3 1 7 5
3 4 8 9 9
2 3 7 7 7
7 6 5 5 8
4 4
8 3 9 5
4 6 8 5
8 1 5 1
4 9 5 5
4 1
6 6 1 7
3 6 6 1
2 4 2 4
7 1 3 4
5 5
18 18 1 8 18
17 7 2 7 2
17 8 7 4 3
17 9 6 5 16
18 10 17 13 18
6 4
12 3 12 10 2 2
13 7 13 3 11 6
2 2 6 5 13 9
1 12 5 4 10 5
11 10 12 8 2 6
13 13 7 4 11 7
7 3
16 10 14 14 15 14 14
15 7 12 2 6 4 9
10 4 11 4 6 1 1
16 4 1 1 13 9 14
3 12 16 14 8 13 9
3 4 17 15 12 15 1
6 6 13 6 6 17 12
8 5
2 3 4 5 4 3 2 1
3 4 5 6 5 4 3 2
4 5 6 7 6 5 4 3
5 6 7 8 7 6 5 4
6 7 8 9 8 7 6 5
5 6 7 8 7 6 5 4
4 5 6 7 6 5 4 3
3 4 5 6 5 4 3 2
8 2
5 20 15 11 1 17 10 14
1 1 11 16 1 14 7 5
17 2 3 4 5 13 19 20
6 18 5 16 6 7 8 5
10 4 5 4 9 2 10 16
2 7 16 5 8 9 10 11
12 19 18 8 7 11 15 12
1 20 18 17 16 15 14 13

 */
