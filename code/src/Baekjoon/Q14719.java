package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q14719 {
    // https://www.acmicpc.net/problem/14719
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

//        int[][] block = new int[H][W];
//        st = new StringTokenizer(br.readLine());
//        for (int i = 0; i < W; i++) {
//            int wall = Integer.parseInt(st.nextToken());
//            for (int j = 1; j <= wall; j++) {
//                block[H-j][i] = 1;
//            }
//        }
//
//        int ans = 0;
//        for (int i = 0; i < H; i++) {
//            int first = -1;
//            for (int j = 0; j < W; j++) {
//                if (block[i][j] == 1) {
//                    if (first == -1) {
//                        first = j;
//                    } else {
//                        ans += (j - first - 1);
//                        first = j;
//                    }
//                }
//            }
//        }
//
//        System.out.println(ans);

        int[] heights = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int totalWater = 0;

        // 각 칸에서 왼쪽과 오른쪽의 최대 높이를 찾음
        for (int i = 1; i < W - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;

            // 현재 칸 기준 왼쪽 최대 높이
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, heights[j]);
            }

            // 현재 칸 기준 오른쪽 최대 높이
            for (int j = i; j < W; j++) {
                rightMax = Math.max(rightMax, heights[j]);
            }

            // 현재 칸에서 고일 수 있는 물의 양 계산
            totalWater += Math.max(0, Math.min(leftMax, rightMax) - heights[i]);
        }

        System.out.println(totalWater);
    }
}
