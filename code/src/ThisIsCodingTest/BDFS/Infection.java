package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Infection {
    static class Virus {
        int type, second;

        public Virus(int type, int second) {
            this.type = type;
            this.second = second;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, K, S, X, Y;
    private static Virus[][] board;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static List<List<Pair>> virusPos = new ArrayList<>();

    public static boolean canGo(int x, int y, int k, int s) {
        if (x < 0 || x >= N || y < 0 || y >= N) return false;
        if(board[x][y].type != 0 && board[x][y].second < s) return false;
        if(board[x][y].type != 0 && board[x][y].type < k) return false;
        if(board[x][y].type == k) return false;

        return true;
    }

    public static boolean canGo2(int x, int y, int k, int s) {
        if (x < 0 || x >= N || y < 0 || y >= N) return false;
        if (board[x][y].type != 0 && board[x][y].second < s) return false;
        if (board[x][y].second == s && board[x][y].type < k) return false;

        return true;
    }

    public static void Infect(int s) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j].type != 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    for (int idx = 0; idx < 4; idx++) {
                        int nextX = i + dx[idx];
                        int nextY = j + dy[idx];
                        if (canGo(nextX, nextY, board[i][j].type, s)) {
                            board[nextX][nextY] = new Virus(board[i][j].type, s);
                            visited[nextX][nextY] = true;
                        }
                    }
                }
            }
        }
    }
    

    public static void main(String[] args) throws IOException {

//        prev();
        oct30();
    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new Virus[N][N];
        for (int i = 0; i <= K; i++) {
            virusPos.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = new Virus(Integer.parseInt(st.nextToken()), 0);
                if (board[i][j].type != 0) {
                    virusPos.get(board[i][j].type).add(new Pair(i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= K; i++) {
            for (Pair cur : virusPos.get(i)) {
                System.out.println("i: " + i + " x: " + cur.x + " y: " + cur.y);
                virus(cur.x, cur.y);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j].type + " ");
            }
            System.out.println();
        }

        System.out.println("board[X][Y].type = " + (board[X - 1][Y - 1].second == S ? board[X - 1][Y - 1].type : 0));

    }

    private static void virus(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x, y));
        int type = board[x][y].type;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            for (int idx = 0; idx < 4; idx++) {
                int nextX = cur.x + dx[idx];
                int nextY = cur.y + dy[idx];
                if (canGo2(nextX, nextY, type, board[cur.x][cur.y].second + 1)) {
                    board[nextX][nextY] = new Virus(type, board[cur.x][cur.y].second + 1);
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new Virus[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = new Virus(Integer.parseInt(st.nextToken()), 0);
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 1; i <= S; i++) {
            visited = new boolean[N][N];
            Infect(i);
//            for(int k = 0; k < N; k++){
//                for(int j = 0; j < N; j++){
//                    System.out.print(board[k][j].type + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }


        System.out.println("board[X][Y] = " + board[X][Y].type);
    }
}

/*
3 3
1 0 2
0 0 0
3 0 0
2 3 2
--- 3

3 3
1 0 2
0 0 0
3 0 0
1 2 2
--- 0
 */
