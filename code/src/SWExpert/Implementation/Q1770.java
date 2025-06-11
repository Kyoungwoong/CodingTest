package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1770 {
    private static int N = 3;
    private static int[][] blocks = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        blocks = new int[N][N];
//
//        StringTokenizer st;
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < N; j++) {
//                blocks[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }

        // 오른쪽으로 회전 90 -> 180 -> 270 -> 360
        int[][] clockRotate = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(blocks[i], 0, clockRotate[i], 0, N);
        }
        for (int idx = 1; idx <= 4; idx++) {
            int[][] temp = new int[N][N];
            System.out.println("========================" + (90 * idx));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = clockRotate[i][j];
                    int row = j;
                    int col = N - 1 - i;
                    temp[row][col] = num;
                }
            }
            clockRotate = temp.clone();
            printArray(clockRotate);
        }

        // 왼쪽으로 회전 -90 -> -180 -> -270 -> -360
        int[][] unClockRotate = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(blocks[i], 0, unClockRotate[i], 0, N);
        }
        for (int idx = 1; idx <= 4; idx++) {
            int[][] temp = new int[N][N];
            System.out.println("========================" + (-90 * idx));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int num = unClockRotate[i][j];
                    int row = N - 1 - j;
                    int col = i;
                    temp[row][col] = num;
                }
            }
            unClockRotate = temp;
            printArray(unClockRotate);
        }

        // 좌우 반전
        int[][] leftRight = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = blocks[i][j];
                leftRight[i][N - j - 1] = num;
            }
        }
        printArray(leftRight);

        // 상하 반전
        int[][] upDown = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = blocks[i][j];
                upDown[N - i - 1][j] = num;
            }
        }
        printArray(upDown);
    }

    private static void printArray(int[][] array) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(array[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }
}
/*
4
3 2 2 2
1 2 2 3
1 3 1 3
2 2 3 1
 */
