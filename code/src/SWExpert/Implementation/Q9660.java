package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9660 {
    private static int tc, N, MOD = 1000000007, ans = 0;
    private static List<Set<Integer>> hateNums;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            N = Integer.parseInt(br.readLine().trim());
            ans = 0;
            visited = new boolean[N + 1];
            hateNums = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                hateNums.add(new HashSet<>());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                hateNums.get(i).add(Integer.parseInt(st.nextToken()));
            }

            simulate(0);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void simulate(int idx) {
        if (idx == N) {
            ans = (ans + 1) % MOD;
            return;
        }

        Set<Integer> personHate = hateNums.get(idx);

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            if (personHate.contains(i)) continue;
            visited[i] = true;
            simulate(idx + 1);
            visited[i] = false;
        }
    }
}
/*
4
3
1 2 3
3
1 4 7
5
1 1 1 1 1
4
1 3 3 4
 */
