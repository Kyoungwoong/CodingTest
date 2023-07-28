package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Virus {
    int type, second;

    public Virus(int type, int second) {
        this.type = type;
        this.second = second;
    }

}

public class Infection {
    private static int N, K, S, X, Y;
    private static Virus[][] board;
    private static boolean[][] visited;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static boolean canGo(int x, int y, int k, int s) {
        if (x < 0 || x >= N || y < 0 || y >= N) return false;
        if(board[x][y].type != 0 && board[x][y].second < s) return false;
        if(board[x][y].type != 0 && board[x][y].type < k) return false;
        if(board[x][y].type == k) return false;

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
