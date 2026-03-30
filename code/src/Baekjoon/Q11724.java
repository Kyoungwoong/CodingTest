package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q11724 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        input();
        int group = solve();
        System.out.println(group);
    }

    private static int solve() {
        int result = 0;

        for (int start = 1; start <= N; start++) {
            if (!visited[start]) {
                result++;
                visited[start] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(start);

                while (!q.isEmpty()) {
                    int cur = q.poll();

                    for (int desc : graph.get(cur)) {
                        if (!visited[desc]) {
                            visited[desc] = true;
                            q.add(desc);
                        }
                    }
                }
            }
        }

        return result;
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int src = Integer.parseInt(st.nextToken());
            int desc = Integer.parseInt(st.nextToken());

            graph.get(src).add(desc);
            graph.get(desc).add(src);
        }
    }
}
