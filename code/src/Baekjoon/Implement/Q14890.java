package Baekjoon.Implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14890 {
    private static int N, L;
    private static int[][] stairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        stairs = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                stairs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        // 가로 방향
        for (int i = 0; i < N; i++) {
            if (canMakeRoad(stairs[i])) ans++;
        }

        // 세로 방향
        for (int j = 0; j < N; j++) {
            int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = stairs[i][j];
            }
            if (canMakeRoad(col)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean canMakeRoad(int[] line) {
        boolean[] installed = new boolean[N];  // 경사로 설치 여부

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            if (diff == 0) continue; // 같은 높이

            // 높이 차이가 1보다 크면 경사로 불가능
            if (Math.abs(diff) > 1) return false;

            if (diff == 1) { // 올라가는 경사로
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || line[j] != line[i] || installed[j]) return false;
                    installed[j] = true;
                }
            } else if (diff == -1) { // 내려가는 경사로
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || line[j] != line[i + 1] || installed[j]) return false;
                    installed[j] = true;
                }
            }
        }
        return true;
    }
}
