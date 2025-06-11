package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q1767 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int tc, N, maxCore, minWire, ans;
    private static int[][] room;
    private static boolean[][] visited;
    private static List<Pair> cores = new ArrayList<>();
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine().trim());
            room = new int[N][N];
            visited = new boolean[N][N];
            ans = Integer.MAX_VALUE;
            cores = new ArrayList<>();
            maxCore = 0;
            minWire = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());

                    if (room[i][j] == 1) {
                        cores.add(new Pair(i, j));
                    }

//                    if (room[i][j] == 1 &&
//                            (i != 0 || i != N - 1 || j != 0 || j != N - 1)) {
//                        cores.add(new Pair(i, j));
//                    }
                }
            }

            dfs(0, 0, 0);
            sb.append("#").append(t).append(" ").append(minWire).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int idx, int coreConnected, int wireLength) {
        if (idx == cores.size()) {
            if (coreConnected > maxCore) {
                maxCore = coreConnected;
                minWire = wireLength;
            } else if (coreConnected == maxCore) {
                minWire = Math.min(minWire, wireLength);
            }
            return;
        }

        Pair core = cores.get(idx);
        int x = core.x;
        int y = core.y;

        for (int d = 0; d < 4; d++) {
            int nx = x;
            int ny = y;
            int len = 0;
            boolean canConnect = true;

            while (true) {
                nx += dx[d];
                ny += dy[d];

                if (!inRange(nx, ny)) break;
                if (room[nx][ny] != 0) {
                    canConnect = false;
                    break;
                }
                len++;
            }

            if (canConnect) {
                nx = x;
                ny = y;
                for (int i = 0; i < len; i++) {
                    nx += dx[d];
                    ny += dy[d];
                    room[nx][ny] = 2;
                }
                dfs(idx + 1, coreConnected + 1, wireLength + len);
                nx = x;
                ny = y;
                for (int i = 0; i < len; i++) {
                    nx += dx[d];
                    ny += dy[d];
                    room[nx][ny] = 0;
                }
            }
        }
        dfs(idx + 1, coreConnected, wireLength); // 연결하지 않는 경우
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || room[x][y] != 0) return false;

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
/*
3
7
0 0 1 0 0 0 0
0 0 1 0 0 0 0
0 0 0 0 0 1 0
0 0 0 0 0 0 0
1 1 0 1 0 0 0
0 1 0 0 0 0 0
0 0 0 0 0 0 0
9
0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 1
1 0 0 0 0 0 0 0 0
0 0 0 1 0 0 0 0 0
0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 1 0 0
0 0 0 1 0 0 0 0 0
0 0 0 0 0 0 0 1 0
0 0 0 0 0 0 0 0 1
11
0 0 1 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 1
0 0 0 1 0 0 0 0 1 0 0
0 1 0 1 1 0 0 0 1 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 1 0 0
0 0 0 0 0 0 1 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0
 */