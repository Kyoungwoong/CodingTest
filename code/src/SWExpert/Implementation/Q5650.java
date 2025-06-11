package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q5650 {
    static int N;
    static int[][] map;
//    static List<int[]>[] wormholes = new ArrayList[11];
    static Map<Integer, int[][]> wormholes;
    static int maxScore;
    static final int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            maxScore = 0;

//            for (int i = 6; i <= 10; i++) wormholes[i] = new ArrayList<>();
            wormholes = new HashMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6 && map[i][j] <= 10) {
                        if (wormholes.containsKey(map[i][j])) {
                            wormholes.get(map[i][j])[1] = new int[]{i, j};
                        } else {
                            wormholes.put(map[i][j], new int[2][2]);
                            wormholes.get(map[i][j])[0] = new int[]{i, j};
                        }
//                        wormholes[map[i][j]].add(new int[]{i, j});
                    }
                }
            }

            for (int x = 0; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (map[x][y] == 0) {
                        for (int d = 0; d < 4; d++) {
                            maxScore = Math.max(maxScore, simulate(x, y, d));
                        }
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(maxScore).append("\n");
        }

        System.out.print(sb);
    }

    static int simulate(int sx, int sy, int dir) {
        int x = sx;
        int y = sy;
        int d = dir;
        int score = 0;

        while (true) {
            x += dx[d];
            y += dy[d];

            if (x < 0 || x >= N || y < 0 || y >= N) {
                d = (d + 2) % 4;
                score++;
                continue;
            }

            if (map[x][y] == -1 || (x == sx && y == sy)) break;

            if (map[x][y] >= 1 && map[x][y] <= 5) {
                d = changeDir(map[x][y], d);
                score++;
            } else if (map[x][y] >= 6 && map[x][y] <= 10) {
                int[] a = wormholes.get(map[x][y])[0];
                int[] b = wormholes.get(map[x][y])[1];
                if (x == a[0] && y == a[1]) {
                    x = b[0]; y = b[1];
                } else {
                    x = a[0]; y = a[1];
                }
            }
        }

        return score;
    }

    static int changeDir(int block, int dir) {
        switch (block) {
            case 1:
                return new int[]{2, 3, 1, 0}[dir];
            case 2:
                return new int[]{1, 3, 0, 2}[dir];
            case 3:
                return new int[]{3, 2, 0, 1}[dir];
            case 4:
                return new int[]{2, 0, 3, 1}[dir];
            case 5:
                return (dir + 2) % 4;
        }
        return dir;
    }
}


/*
5
10
0 1 0 3 0 0 0 0 7 0
0 0 0 0 -1 0 5 0 0 0
0 4 0 0 0 3 0 0 2 2
1 0 0 0 1 0 0 3 0 0
0 0 3 0 0 0 0 0 6 0
3 0 0 0 2 0 0 1 0 0
0 0 0 0 0 1 0 0 4 0
0 5 0 4 1 0 7 0 0 5
0 0 0 0 0 1 0 0 0 0
2 0 6 0 0 4 0 0 0 4
6
1 1 1 1 1 1
1 1 -1 1 1 1
1 -1 0 -1 1 1
1 1 -1 1 1 1
1 1 1 1 1 1
1 1 1 1 1 1
8
0 0 0 3 0 0 0 0
0 0 2 0 0 5 0 0
0 0 5 0 3 0 0 0
0 0 1 1 0 0 0 4
0 0 0 0 0 0 0 0
0 0 0 0 0 0 5 0
0 0 4 0 0 3 1 0
2 0 0 4 3 4 0 0
10
0 4 0 0 0 0 4 0 5 0
0 0 0 0 0 0 0 0 3 0
0 0 0 5 6 0 0 0 0 2
3 0 0 1 0 0 1 4 0 2
2 0 0 0 0 5 0 0 5 0
0 0 1 0 2 0 0 0 5 0
0 0 0 0 0 0 6 1 0 0
0 0 1 0 2 0 2 4 0 0
0 0 0 0 0 0 2 0 0 0
2 0 0 0 0 0 0 3 0 0
20
0 0 1 0 0 0 0 3 0 3 0 0 0 4 0 0 1 0 4 0
0 1 2 3 3 0 0 0 0 0 0 0 0 5 0 0 0 0 5 0
0 0 0 0 0 0 0 0 0 5 0 0 0 5 0 4 0 0 0 0
4 0 0 0 0 0 0 4 5 0 0 0 3 0 0 0 3 0 0 0
0 0 0 3 0 4 1 0 3 0 0 0 0 4 0 0 0 2 0 3
2 2 0 0 0 0 0 0 0 0 0 0 4 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0 0 0 5 0 5 0 0 0 3 4
0 0 5 0 -1 5 0 0 5 2 0 0 0 4 2 0 0 3 0 0
0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0
2 0 0 0 0 3 0 0 3 3 3 0 0 1 0 0 2 0 0 0
1 5 0 5 0 0 0 0 5 4 5 0 0 0 0 4 2 4 0 0
0 4 0 0 0 1 3 0 0 0 0 0 1 0 3 0 0 2 0 0
0 0 0 0 0 0 3 0 1 0 0 1 0 0 0 0 0 3 4 0
0 4 0 4 0 0 0 0 0 0 0 2 0 0 0 3 0 0 0 2
0 5 0 0 0 4 1 5 0 0 0 2 0 0 0 0 0 0 0 0
0 0 0 5 0 0 1 2 0 0 0 3 1 2 5 0 0 0 0 0
0 4 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0 0 0 4 0 0 0 0 4 0 0 0 0 0 0 1 4 0 2 0
0 0 1 0 0 0 0 0 3 0 0 0 0 0 0 0 5 0 0 0
0 0 0 0 0 0 0 5 0 4 0 0 0 0 0 2 0 0 2 0

 */