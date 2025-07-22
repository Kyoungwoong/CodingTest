package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FKiller {
    private static int tc, N, M;
    private static int[][] flies;
    private static int[][][] d = {
            {{-1, 0}, {0, 1}, {1, 0}, {0, -1}},
            {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            flies = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    flies[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int num = spreadPlus();
            num = Math.max(num, spreadX());
            sb.append("#").append(t).append(" ").append(num).append("\n");
        }
        System.out.println(sb);
    }

    private static int spreadPlus() {
        int[][] dir = d[0];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = flies[i][j];

                for (int idx = 0; idx < 4; idx++) {
                    int nx = i + dir[idx][0];
                    int ny = j + dir[idx][1];
                    int cnt = 1;
                    while (inRange(nx, ny) && cnt < M) {
                        sum += flies[nx][ny];
                        nx = nx + dir[idx][0];
                        ny = ny + dir[idx][1];
                        cnt++;
                    }
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private static int spreadX() {
        int[][] dir = d[1];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = flies[i][j];

                for (int idx = 0; idx < 4; idx++) {
                    int nx = i + dir[idx][0];
                    int ny = j + dir[idx][1];
                    int cnt = 1;
                    while (inRange(nx, ny) && cnt < M) {
                        sum += flies[nx][ny];
                        nx = nx + dir[idx][0];
                        ny = ny + dir[idx][1];
                        cnt++;
                    }
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
