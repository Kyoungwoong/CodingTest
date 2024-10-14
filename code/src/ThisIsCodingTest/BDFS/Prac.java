package ThisIsCodingTest.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prac {
    /*
    8
     2 3 8
     1 7
     1 4 5
     3 5
     3 4
     7
     2 6 8
     1 7
     */
    private static int N;
    private static boolean[] visited;
    private static List<List<Integer>> list = new ArrayList<>();

    public static void dfs(int start) {
        if (list.get(start).size() == 0 || visited[start]) {
            return;
        }
        visited[start] = true;
        System.out.print(start + " ");
        for (int adj : list.get(start)) {
            dfs(adj);
        }
    }
    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");
            for (int next : list.get(cur)) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                q.add(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(1);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(1);
    }
}
