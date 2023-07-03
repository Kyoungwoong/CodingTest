package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Route implements Comparable<Route>{
    int x, y, distance;

    public Route(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Route r) {
        return this.distance - r.distance;
    }
}

public class Mars {
    private static int N, T, MAX_VALUE = Integer.MAX_VALUE;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] route;
    private static int[][] result;
    private static PriorityQueue<Route> pq;

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            pq = new PriorityQueue<>();
            N = Integer.parseInt(br.readLine());
            result = new int[N][N];
            route = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    route[i][j] = Integer.parseInt(st.nextToken());
                    result[i][j] = MAX_VALUE;
                }
            }

            int x = 0, y = 0;
            result[x][y] = route[x][y];
            pq.offer(new Route(0, 0, route[x][y]));

            while (!pq.isEmpty()) {
                Route r = pq.poll();
                int dist = r.distance;
                x = r.x;
                y = r.y;

                // 방문한 적이 있는 x, y인지.
                if (result[x][y] < dist) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (isRange(nx, ny)) {
                        int cost = dist + route[nx][ny];
                        if (cost < result[nx][ny]) {
                            result[nx][ny] = cost;
                            pq.offer(new Route(nx, ny, cost));
                        }
                    }
                }
            }

            sb.append(result[N - 1][N - 1] + "\n");
        }

        System.out.println(sb);
    }
}
/*
3
3
5 5 4
3 9 1
3 2 7
5
3 7 2 0 1
2 8 0 9 1
1 2 1 8 1
9 8 9 2 0
3 6 5 1 5
7
9 0 5 1 1 5 3
4 1 2 1 6 5 3
0 7 6 1 6 8 5
1 1 7 8 3 2 3
9 4 0 7 6 4 1
5 8 3 2 4 8 3
7 4 8 4 8 3 4
---
20
19
36
 */
