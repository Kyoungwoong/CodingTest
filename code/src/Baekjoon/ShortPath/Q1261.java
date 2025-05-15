package Baekjoon.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 0-1 BFS
// BFS인데 가중치로 앞에 넣냐, 뒤에 넣냐. 그러면서 Queue에 들어가는게 적어짐.
// 근데 조건만 잘 넣으면 BFS로도 풀 수 있음.

public class Q1261 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, M;
    private static int[][] miro;
    private static int[][] step;

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        miro = new int[N][M];
        step = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            Arrays.fill(step[i], Integer.MAX_VALUE);
            for (int j = 0; j < M; j++) {
                miro[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(step[N-1][M-1]);
    }

    private static void bfs() {
//        Deque<Pair> q = new ArrayDeque<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        step[0][0] = 0;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int idx = 0; idx < 4; idx++) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                /**
                 * 0 -> 0
                 * 0 -> 1 case 1
                 * 1 -> 0 case 2
                 * 1 -> 1 case 1
                 */
                if (inRange(nx, ny)) {
                    int cost = step[x][y] + miro[nx][ny];
                    if (step[nx][ny] > cost) {
                        step[nx][ny] = cost;
                        if (miro[nx][ny] == 1) {
                            q.add(new Pair(nx, ny));
//                            q.addLast(new Pair(nx, ny));
                        } else {
                            q.add(new Pair(nx, ny));
//                            q.addFirst(new Pair(nx, ny));
                        }
                    }
                }
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(step[i][j] + " ");
            }
            System.out.println();
        }
    }
}
