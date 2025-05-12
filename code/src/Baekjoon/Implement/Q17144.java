package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Thread.sleep;

public class Q17144 {

    static class Wind {
        Pair stdUpPair, stdDownPair;
        Pair upPair = null, downPair = null;
        int[][] upD = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] downD = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int upDir = 0, downDir = 0;

        public void setUpPair(Pair upPair) {
            this.upPair = upPair;
            this.stdUpPair = new Pair(upPair.x, upPair.y);
        }

        public void setDownPair(Pair downPair) {
            this.downPair = downPair;
            this.stdDownPair = new Pair(downPair.x, downPair.y);
        }

        private void init() {
//            upPair = new Pair(stdUpPair.x, stdDownPair.y);
//            downPair = new Pair(stdDownPair.x, stdDownPair.y);
            upDir = 0;
            downDir = 0;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int R, C, T, micro;
    private static Wind wind;
    private static int[][] room;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        wind = new Wind();
        room = new int[R][C];
        micro = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    if (wind.upPair == null) {
                        wind.setUpPair(new Pair(i, j));
                    } else {
                        wind.setDownPair(new Pair(i, j));
                    }
                } else {
                    micro += room[i][j];
                }
            }
        }

        while (T-- > 0) {
            // 미세먼지 확산
            spread();

            // 공기 청정기 순환
            circulate();

//            sleep(500);
//
//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(room[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("========================");
        }

        System.out.println(micro);
    }

    private static void circulate() throws InterruptedException {
        Pair upPair = wind.stdUpPair;

        int upX = upPair.x - 1;
        int upY = upPair.y;
        micro -= room[upX][upY];
        while (upX != upPair.x || upY != upPair.y) {
            room[upX][upY] = 0;
            int nX = upX + wind.upD[wind.upDir][0];
            int nY = upY + wind.upD[wind.upDir][1];
            if (nX == upPair.x && nY == upPair.y) {
                break;
            }
            if (!inRange(nX, nY) || nX > upPair.x) {
                wind.upDir = (wind.upDir + 1) % 4;
                nX = upX + wind.upD[wind.upDir][0];
                nY = upY + wind.upD[wind.upDir][1];
            }
//            sleep(500);
//            System.out.println("dir: " + wind.upDir +
//                    ") upX: " + upX + " upY: " + upY +
//                    " nX: " + nX + " nY: " + nY);

            room[upX][upY] = room[nX][nY];
            upX = nX;
            upY = nY;
        }

        Pair downPair = wind.stdDownPair;
        int downX = downPair.x + 1;
        int downY = downPair.y;
        micro -= room[downX][downY];
        while (downX != downPair.x || downY != downPair.y) {
            room[downX][downY] = 0;
            int nX = downX + wind.downD[wind.downDir][0];
            int nY = downY + wind.downD[wind.downDir][1];
            if (nX == downPair.x && nY == downPair.y) {
                break;
            }
            if (!inRange(nX, nY) || nX < downPair.x) {
                wind.downDir = (wind.downDir + 1) % 4;
                nX = downX + wind.downD[wind.downDir][0];
                nY = downY + wind.downD[wind.downDir][1];
            }
//            sleep(500);
//            System.out.println("dir: " + wind.downDir +
//                    ") downX: " + downX + " downY: " + downY +
//                    " nX: " + nX + " nY: " + nY);

            room[downX][downY] = room[nX][nY];
            downX = nX;
            downY = nY;
        }

        wind.init();
    }


    private static void spread() {
        int[][] add = new int[R][C];
        for (int x = 0; x < R; x++) {
            for (int y = 0; y < C; y++) {
                if (room[x][y] >= 5) {
                    int amount = room[x][y] / 5;
                    int count = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (canGo(nx, ny)) {
                            add[nx][ny] += amount;
                            count++;
                        }
                    }

                    room[x][y] -= amount * count;
                }
            }
        }

        // 확산된 미세먼지를 원본에 반영
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] += add[i][j];
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (room[x][y] == -1) return false;
        return true;
    }
}

/*
6 6 1
0 1 2 3 4 5
0 0 0 0 0 0
-1 1 2 3 4 5
-1 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0

 */