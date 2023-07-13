package ThisIsCodingTest.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q10 {
    private static int N, M;
    private static int[][] key, lock, temp;

    public static void rotate() {
        // 열을 기준
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                temp[j][N-i-1] = key[i][j];
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
