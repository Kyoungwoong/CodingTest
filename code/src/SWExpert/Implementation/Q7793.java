package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q7793 {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int tc, N, M, ans;
    private static char[][] map;
    private static int[][] step;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static Pair angle, su;
    private static List<Pair> demons;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];
            step = new int[N][M];
            visited = new boolean[N][M];
            demons = new ArrayList<>();
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'D') {
                        angle = new Pair(i, j);
                    } else if (map[i][j] == 'S') {
                        su = new Pair(i, j);
                    } else if (map[i][j] == '*') {
                        demons.add(new Pair(i, j));
                    }
                }
            }

            int answer = bfs();
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("===========");
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < M; j++) {
//                    System.out.print(step[i][j] + " ");
//                }
//                System.out.println();
//            }

            sb.append("#").append(t).append(" ")
                    .append(answer == -1 ? "GAME OVER" : answer)
                    .append("\n");
        }
        System.out.println(sb);
    }

    static int bfs() {
        Queue<Pair> suyeonQ = new LinkedList<>();
        Queue<Pair> demonQ = new LinkedList<>();
        suyeonQ.add(su);
        demonQ.addAll(demons);

        while (!suyeonQ.isEmpty()) {
            // 1. 악마 먼저 퍼짐
            int demonSize = demonQ.size();
            for (int i = 0; i < demonSize; i++) {
                Pair d = demonQ.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = d.x + dx[dir];
                    int ny = d.y + dy[dir];
                    if (canGo(nx, ny) && map[nx][ny] != 'D') {
                        map[nx][ny] = '*';
                        demonQ.offer(new Pair(nx, ny));
                    }
                }
            }

            // 2. 수연이 이동
            int suSize = suyeonQ.size();
            for (int i = 0; i < suSize; i++) {
                Pair s = suyeonQ.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = s.x + dx[dir];
                    int ny = s.y + dy[dir];
                    if (canGo(nx, ny)) {
                        if (map[nx][ny] == '*') {
                            continue;
                        } else if (map[nx][ny] == 'D') {
                            return step[s.x][s.y] + 1;
                        }
                        visited[nx][ny] = true;
                        step[nx][ny] = step[s.x][s.y] + 1;
                        suyeonQ.offer(new Pair(nx, ny));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean canGo(int x, int y) {
        if (!inRange(x, y)) return false;
        if (visited[x][y] || map[x][y] == 'X' || map[x][y] == '*') return false;

        return true;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}

/*
2
5 3
D*S
.X.
.X.
.X.
...
5 3
D*S
...
.X.
.X.
...
 */