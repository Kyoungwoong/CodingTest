package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q5656 {
    static class Pair {
        int x, y, boundary;

        public Pair(int x, int y, int boundary) {
            this.x = x;
            this.y = y;
            this.boundary = boundary;
        }
    }

    private static int tc, N, W, H, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static int[] crash;
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
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            crash = new int[N];
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0, map);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int pos, int[][] cur) {
        if (depth == N) {
            int block = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (cur[i][j] != 0) {
                        block++;
                    }
                }
            }

            ans = Math.min(ans, block);
            return;
        }

        for (int i = 0; i < W; i++) {
            int[][] copy = copyMap(cur);
            breakBlock(i, copy);
            dfs(depth + 1, pos + 1, copy);
        }
    }

    // 아래로 떨어지기.
    private static void dropDown(int[][] copy) {
        for (int col = 0; col < W; col++) {
            int cur = H - 1;
            int prev = H - 2;

            while (cur >= 0 && prev >= 0) {
                while (cur >= 0 && copy[cur][col] != 0) {
                    cur--;
                }

                prev = cur - 1;
                while (prev >= 0 && copy[prev][col] == 0) {
                    prev--;
                }

                if (prev < 0) break;

                copy[cur][col] = copy[prev][col];
                copy[prev][col] = 0;

                cur--;
            }
        }
    }

    // 연쇄적으로 깨트리기
    private static void breakBlock(int col, int[][] copy) {
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            if (copy[i][col] != 0) {
                q.add(new Pair(i, col, copy[i][col]));
                break;
            }
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            copy[cur.x][cur.y] = 0;

            for (int idx = 0; idx < 4; idx++) {
                int nx = cur.x, ny = cur.y;
                for (int it = 1; it < cur.boundary; it++) {
                    nx = nx + dx[idx];
                    ny = ny + dy[idx];
                    if (inRange(nx, ny)) {
                        if (copy[nx][ny] > 1) {
                            q.add(new Pair(nx, ny, copy[nx][ny]));
                        }
                        copy[nx][ny] = 0;
                    }
                }
            }
        }

        dropDown(copy);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < H && 0 <= y && y < W;
    }

    private static int[][] copyMap(int[][] src) {
        int[][] copy = new int[H][W];
        for (int i = 0; i < H; i++) {
            System.arraycopy(src[i], 0, copy[i], 0, W);
        }
        return copy;
    }

    private static void anotherDropDown() {
        for (int col = 0; col < W; col++) {
            int writeRow = H - 1;
            for (int row = H - 1; row >= 0; row--) {
                if (map[row][col] != 0) {
                    map[writeRow][col] = map[row][col];
                    if (writeRow != row) {
                        map[row][col] = 0;
                    }
                    writeRow--;
                }
            }
            for (int row = writeRow; row >= 0; row--) {
                map[row][col] = 0;
            }
        }
    }
}
/*
2
3 10 10
0 0 0 0 0 0 0 0 0 0
1 0 1 0 1 0 0 0 0 0
1 0 3 0 1 1 0 0 0 1
1 1 1 0 1 2 0 0 0 9
1 1 4 0 1 1 0 0 1 1
1 1 4 1 1 1 2 1 1 1
1 1 5 1 1 1 1 2 1 1
1 1 6 1 1 1 1 1 2 1
1 1 1 1 1 1 1 1 1 5
1 1 7 1 1 1 1 1 1 1
2 9 10
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 1 0 0 0 0 0 0 0
1 1 0 0 1 0 0 0 0
1 1 0 1 1 1 0 1 0
1 1 0 1 1 1 0 1 0
1 1 1 1 1 1 1 1 0
1 1 3 1 6 1 1 1 1
1 1 1 1 1 1 1 1 1
 */

/*
2
5 5 5
0 0 0 0 0
1 0 1 0 0
0 0 1 1 1
1 1 0 1 1
1 1 1 1 1
5 7 7
0 0 0 0 0 0 1
1 0 1 0 0 1 1
0 0 0 1 1 1 1
1 1 1 1 1 0 1
1 1 1 1 1 0 1
0 1 1 1 1 1 1
1 1 1 1 1 1 1
 */