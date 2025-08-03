package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q3503 {
    private static int tc, N;
    private static Integer[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            heights = new Integer[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(heights, Collections.reverseOrder());

            int idx = 0;
            int[] ans = new int[N];
            Arrays.fill(ans, -1);
            ans[idx] = heights[idx];
            idx++;
            for (int i = 1; i < N; i++) {
                int num = heights[i];
                if (ans[N - idx] == -1 && ans[idx] == -1) {
                    ans[N - idx] = num;
                } else if (ans[idx] == -1) {
                    ans[idx] = num;
                    idx++;
                }
            }

            for (int i = 0; i < N; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();

            int maxDiff = 0;
            for (int i = 0; i < N; i++) {
                int diff = Math.abs(ans[i] - ans[(i + 1) % N]);
                maxDiff = Math.max(maxDiff, diff);
            }
            sb.append("#").append(t).append(" ").append(maxDiff).append("\n");
        }
        System.out.println(sb);
    }
}

/*
3
7
13 10 12 11 10 11 12
5
2 4 5 7 9
8
6 6 6 6 6 6 6 6
 */