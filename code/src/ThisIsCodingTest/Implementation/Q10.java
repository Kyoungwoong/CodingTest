package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q10 {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int N, M;
    private static int[][] key, lock, temp;

    public static void rotate() {
        int[][] temp = new int[M][M];  // 회전한 결과를 저장할 배열
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < M; i++) {
                temp[j][M - i - 1] = key[i][j];
            }
        }
        key = temp;
    }

    public static boolean isPossible(int x, int y) {
        int[][] newLock = new int[2 * N + M][2 * N + M];

        for (int i = 0; i < 2 * N + M; i++) {
            for (int j = 0; j < 2 * N + M; j++) {
                newLock[i][j] = lock[i][j];
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (key[i][j] == 1) {
                    newLock[x + i][y + j] = 1;
                }

            }
        }

        for (int i = N; i < M + N; i++) {
            for (int j = N; j < M + N; j++) {
                if (newLock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
//        prev();
        oct30();
    }

    private static void oct30() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());
        key = new int[M][M];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                key[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        N = Integer.parseInt(br.readLine());
        lock = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lock[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(attemptUnlock());
    }

    private static boolean attemptUnlock() {
        int size = M + 2 * N;
        for (int rotation = 0; rotation < 4; rotation++) {
            rotateKey();

            for (int startP = 0; startP <= size - M; startP++) {
                for (int startQ = 0; startQ <= size - M; startQ++) {
                    if (canUnlock(startP, startQ)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void rotateKey() {
        temp = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][M - 1 - i] = key[i][j];
            }
        }
        key = temp;
    }

    private static boolean canUnlock(int startP, int startQ) {
        int[][] board = new int[N + 2 * M][N + 2 * M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[M + i][M + j] = lock[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (key[i][j] == 1) {
                    board[startP + i][startQ + j] += key[i][j];
                }
            }
        }

        for (int i = M; i < M + N; i++) {
            for (int j = M; j < M + N; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        key = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                key[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        M = Integer.parseInt(br.readLine());
        lock = new int[2 * N + M][2 * N + M];
        for (int i = N; i < N + M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = N; j < N + M; j++) {
                lock[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for (int idx = 0; idx < 3; idx++) {
            temp = new int[N][N];
            for (int i = 0; i < N + M; i++) {
                for (int j = 0; j < N + M; j++) {
                    if (isPossible(i, j)) {
                        System.out.println(true);
                        System.exit(0);
                    }
                }
            }
            rotate();
        }
        System.out.print(false);
    }
}
/*
3
0 0 0
1 0 0
0 1 1
3
1 1 1
1 1 0
1 0 1
--- true

3
0 0 0
1 0 0
0 1 1
3
1 0 1
1 1 0
1 0 1
--- false
 */
