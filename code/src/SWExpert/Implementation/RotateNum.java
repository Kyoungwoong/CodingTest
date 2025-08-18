package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RotateNum {
    private static int tc, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] origin = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    origin[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 회전 결과 저장
            int[][] rot90 = rotate(origin);
            int[][] rot180 = rotate(rot90);
            int[][] rot270 = rotate(rot180);

            sb.append("#").append(t).append("\n");

            for (int i = 0; i < N; i++) {
                // 90도 회전된 행 출력
                for (int j = 0; j < N; j++) {
                    sb.append(rot90[i][j]);
                }
                sb.append(" ");
                // 180도 회전된 행 출력
                for (int j = 0; j < N; j++) {
                    sb.append(rot180[i][j]);
                }
                sb.append(" ");
                // 270도 회전된 행 출력
                for (int j = 0; j < N; j++) {
                    sb.append(rot270[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int[][] rotate(int[][] arr) {
        int[][] result = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                result[row][col] = arr[N - 1 - col][row];
            }
        }
        return result;
    }
}
