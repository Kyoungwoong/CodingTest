package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14499 {
    static class Standard {
        int top, right, front;

        public Standard() {
            this.top = 1;
            this.right = 3;
            this.front = 5;
        }

        private void change(int dir) {
            int temp = this.top;
            if (dir == 1) {
                this.top = this.right;
                this.right = 7 - temp;
            } else if (dir == 0) {
                this.top = 7 - this.right;
                this.right = temp;
            } else if (dir == 3) {
                this.top = 7 - this.front;
                this.front = temp;
            } else if (dir == 2) {
                this.top = this.front;
                this.front = 7 - temp;
            }
        }
    }

    private static int N, M, x, y, K;
    private static int[][] map;
    private static List<Integer> commands = new ArrayList<>();
    private static Map<Integer, Integer> dice = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commands.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= 6; i++) {
            dice.put(i, 0);
        }
        dice.put(6, map[x][y]);

        Standard dicePos = new Standard();
        StringBuilder sb = new StringBuilder();
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        for (int i = 0; i < K; i++) {
            int command = commands.get(i) - 1;
            int nextX = x + dx[command];
            int nextY = y + dy[command];

            if (inRange(nextX, nextY)) {
                dicePos.change(command);
                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = dice.get(7 - dicePos.top);
                } else {
                    dice.put(7 - dicePos.top, map[nextX][nextY]);
                    map[nextX][nextY] = 0;
                }
                x = nextX;
                y = nextY;
                sb.append(dice.get(dicePos.top)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
