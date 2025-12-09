package liveCodingTest.Backtracking.permutation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Q15649 {
    static int N, M;
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();

        permutation(0, new ArrayList<>());
        System.out.println(sb.toString());
    }

    private static void permutation(int depth, List<Integer> list) {
        if (depth == M) {
            for (int item : list) {
                sb.append(item).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            list.add(i);
            permutation(depth + 1, list);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    private static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
    }
}
