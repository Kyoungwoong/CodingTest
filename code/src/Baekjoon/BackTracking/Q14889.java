package Baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q14889 {
    public static int N, ans = Integer.MAX_VALUE;
    public static int[][] board;
    public static ArrayList<Integer> start = new ArrayList<>();

    public static int calc() {
        boolean[] remove = new boolean[N];
        for (int i = 0; i < N; i++) {
            remove[i] = true;
        }

        int sum = 0;
        for (int i = 0; i < N / 2; i++) {
            int std = start.get(i);
            remove[std] = false;
            for (int j = 0; j < N / 2; j++) {
                if (j == i) {
                    continue;
                }
                int k = start.get(j);
                sum += board[std][k];
            }
        }

        ArrayList<Integer> removed = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (remove[i]) {
                removed.add(i);
            }
        }

        for (int i = 0; i < N / 2; i++) {
            int std = removed.get(i);
            for (int j = 0; j < N / 2; j++) {
                if (j == i) {
                    continue;
                }
                int k = removed.get(j);
                sum -= board[std][k];
            }
        }

        return Math.abs(sum);
    }

    public static void selectTeam(int cnt, int idx) {
        if (cnt == N / 2) {
//            for (int i = 0; i < start.size(); i++) {
//                System.out.print(start.get(i) + " ");
//            }
//            System.out.println("calc: " + calc());
            ans = Math.min(ans, calc());

            return;
        }

        for (int i = idx; i < N; i++) {
            if(!start.contains(i)) {
                start.add(i);
                selectTeam(cnt + 1, i+1);
                start.remove(start.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        selectTeam(0, 0);
        System.out.println(ans);
    }
}
