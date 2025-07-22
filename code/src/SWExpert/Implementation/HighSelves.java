package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HighSelves {
    private static int tc, N, B, ans;
    private static int[] heights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            heights = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                heights[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(heights);

            ans = Integer.MAX_VALUE;
            // nC0 ~~ nCn까지 구해야 되구나
            for (int limit = 1; limit <= N; limit++) {
                solve(0, 0, 0, limit);
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int start, int sum, int depth, int limit) {
        if (depth == limit) {
            if (sum >= B) {
                ans = Math.min(ans, sum - B);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            solve(i + 1, sum + heights[i], depth + 1, limit);
        }
    }
}
/*
1
5 16
3 1 3 5 6
 */