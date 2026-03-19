package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Q15654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] numbers;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, 0, new ArrayList<>());
        System.out.println(sb.toString());
    }

    private static void dfs(int pos, int selectedCount, List<Integer> list) {
        if (selectedCount == M) {
            for (int number : list) {
                sb.append(number).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(numbers[i]);
            dfs(i + 1, selectedCount + 1, list);

            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);
    }
}
