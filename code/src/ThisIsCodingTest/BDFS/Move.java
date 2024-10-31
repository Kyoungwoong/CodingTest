package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Move {
    static class Pos{
        int x, y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int N, L, R;
    private static int[][] land;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static ArrayList<Pos> arr;
    private static Queue<Pos> q;

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int idx = 0; idx < 4; idx++) {
                    int nextX = i + dx[idx];
                    int nextY = j + dy[idx];
                    if (isRange(nextX, nextY)) {
                        int diff = land[i][j] - land[nextX][nextY];
                        if(L <= diff && diff <= R){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void bfs(int x, int y) {
        q = new LinkedList();
        arr = new ArrayList<>();

        q.add(new Pos(x, y));
        arr.add(new Pos(x, y));
        visited[x][y] = true;
        int sum = land[x][y];

        while(!q.isEmpty()){
            Pos cur = q.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if(isRange(nextX, nextY) && !visited[nextX][nextY]) {
                    int diff = Math.abs(land[cur.x][cur.y] - land[nextX][nextY]);
                    if(L <= diff && diff <= R) {
                        sum += land[nextX][nextY];
                        q.offer(new Pos(nextX, nextY));
                        arr.add(new Pos(nextX, nextY));
                        visited[nextX][nextY] = true;
                    }
                }
            }
        }

        if (arr.size() > 1) {
            int avg = sum / arr.size();
            for (int i = 0; i < arr.size(); i++) {
                land[arr.get(i).x][arr.get(i).y] = avg;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        land = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (check()) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++){
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(land[i][j] + " ");
//                }
//                System.out.println();
//            }
            cnt++;
        }
        System.out.println("cnt = " + cnt);
    }
}

/*
2 20 50
50 30
20 40
--- 1

2 40 50
50 30
20 40
--- 0

2 20 50
50 30
30 40
--- 1

3 5 10
10 15 20
20 30 25
40 22 10
--- 2

4 10 50
10 100 20 90
80 100 60 70
70 20 30 40
50 20 100 10
--- 3
 */
