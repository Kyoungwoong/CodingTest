package ThisIsCodingTest.ShortPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hide {
    private static int N, M;
    private static ArrayList<ArrayList<Integer>> barn = new ArrayList<>();
    private static boolean[] visited;
    private static int[] d;

    public static int getSmallestNode() {
        int min = (int)1e9;
        int min_idx = -1;
        for (int i = 1; i < N; i++) {
            if (min > d[i] && !visited[i]) {
                min = d[i];
                min_idx = i;
            }
        }
        return min_idx;
    }

    public static void find() {
        // 시작 노드에 대해서 초기화.
        for (int i = 0; i < barn.get(0).size(); i++) {
            d[barn.get(0).get(i)] = 1;
        }

        // 시작 노드 외 수행
        int last = 1;
        for (int i = 0; i < N - 1; i++) {
            int cur = getSmallestNode();
            visited[cur] = true;
            for (int j = 0; j < barn.get(cur).size(); j++) {
                int cost = last + 1;
                if (cost < d[barn.get(cur).get(j)]) {
                    d[barn.get(cur).get(j)] = cost;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[N];
        visited = new boolean[N];
        Arrays.fill(d, (int)1e9);
        d[0] = 0;
        visited[0] = true;

        for (int i = 0; i < N; i++) {
            barn.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            barn.get(a).add(b);
            barn.get(b).add(a);
        }

        find();

        for (int i = 0; i < N; i++) {
            System.out.print(d[i] + " ");
        }
    }
}
/*
6 7
3 6
4 3
3 2
1 3
1 2
2 4
5 2
---
4 2 3
 */
