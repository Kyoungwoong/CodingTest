package Baekjoon.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11660 {

    private static int N, M;
    private static int[][] sum;

    public static int getSum(int x1, int y1, int x2, int y2) {
        return sum[x2][y2]     - sum[x1 - 1][y2] -
                sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N+1][N+1];
        sum = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= N; i++)
            for(int j = 1; j <= N; j++)
                sum[i][j] = sum[i - 1][j] +
                        sum[i][j - 1] -
                        sum[i - 1][j - 1] +
                        arr[i][j];

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= M; i++){
            int max = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            max = Math.max(max, getSum(x1,y1,x2,y2));
            sb.append(max + "\n");
        }
        System.out.println(sb);
    }
}
