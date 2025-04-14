package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16236 {
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Shark {
        Pair pos;
        int size;
        int fishCount;

        public Shark(Pair pos) {
            this.pos = pos;
            this.size = 2;
            this.fishCount = 0;
        }
    }

    private static int N;
    private static int[][] ocean;
    private static Shark baby;
    private static int totalTime = 0;
    private static int[] dx = {-1, 0, 1, 0}; // 상, 좌, 우, 하 순서
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ocean = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ocean[i][j] = Integer.parseInt(st.nextToken());
                if (ocean[i][j] == 9) {
                    baby = new Shark(new Pair(i, j));
                    ocean[i][j] = 0; // 아기 상어 초기 위치는 빈 칸으로 설정
                }
            }
        }

        while (true) {
            Pair targetFish = findClosestFish();
            if (targetFish == null) break;

            // ✅ 물고기를 먹고 이동
            totalTime += bfs(targetFish);
            baby.pos = targetFish;
            baby.fishCount++;
            ocean[targetFish.x][targetFish.y] = 0;

            if (baby.fishCount == baby.size) {
                baby.size++;
                baby.fishCount = 0;
            }
        }

        System.out.println(totalTime);
    }

    public static Pair findClosestFish() {
        boolean[][] visited = new boolean[N][N];
        Queue<Pair> q = new LinkedList<>();
        int[][] dist = new int[N][N];

        q.add(baby.pos);
        visited[baby.pos.x][baby.pos.y] = true;
        Pair targetFish = null;
        int minDistance = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && ocean[nextX][nextY] <= baby.size) {
                    visited[nextX][nextY] = true;
                    dist[nextX][nextY] = dist[cur.x][cur.y] + 1;
                    q.add(new Pair(nextX, nextY));

                    if (ocean[nextX][nextY] > 0 && ocean[nextX][nextY] < baby.size) {
                        if (dist[nextX][nextY] < minDistance) {
                            minDistance = dist[nextX][nextY];
                            targetFish = new Pair(nextX, nextY);
                        } else if (dist[nextX][nextY] == minDistance) { // 거리가 같다면 위쪽-왼쪽 우선
                            if (nextX < targetFish.x || (nextX == targetFish.x && nextY < targetFish.y)) {
                                targetFish = new Pair(nextX, nextY);
                            }
                        }
                    }
                }
            }
        }

        return targetFish;
    }

    public static int bfs(Pair target) {
        boolean[][] visited = new boolean[N][N];
        Queue<Pair> q = new LinkedList<>();
        int[][] dist = new int[N][N];

        q.add(baby.pos);
        visited[baby.pos.x][baby.pos.y] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            if (cur.x == target.x && cur.y == target.y) {
                return dist[cur.x][cur.y]; // ✅ 목표 물고기까지 도달한 경우 거리 반환
            }

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if (inRange(nextX, nextY) && !visited[nextX][nextY] && ocean[nextX][nextY] <= baby.size) {
                    visited[nextX][nextY] = true;
                    dist[nextX][nextY] = dist[cur.x][cur.y] + 1;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }

        return 0;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}