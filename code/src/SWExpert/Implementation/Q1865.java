package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q1865 {
    private static int tc, N;
    private static double ans;
    private static List<List<Double>> prob;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine());
            prob = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                prob.add(new ArrayList<>());
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    prob.get(i).add(Double.parseDouble(st.nextToken()) / 100.0);
                }
            }

            ans = 0.0;
            solve(0, 1.0, new boolean[]{false, false, false});

            sb.append(String.format("#%d %.6f", t, ans));
        }
        System.out.println(sb);
    }

    private static void solve(int pos, double mul, boolean[] visited) {
        if (mul <= ans) {
            return;
        }
        if (pos == N) {
            ans = Math.min(ans, mul);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            solve(pos + 1, mul * prob.get(pos).get(i), visited);
            visited[i] = false;
        }
    }
}
