package Baekjoon.TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1766 {
    private static int N, M;
    private static int[] arr;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    private static PriorityQueue<Integer> q = new PriorityQueue<>();

    public static void topologySort() {
        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0 && !visited[i]) {
                q.add(i);
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int num = q.poll();
//                System.out.println(num);
            sb.append(num + " ");
            for (int j = 0; j < graph.get(num).size(); j++) {
                int next = graph.get(num).get(j);
                arr[next]--;
                if (arr[next] == 0 && !visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[B]++;
            graph.get(A).add(B);
        }

        topologySort();

        System.out.println(sb);
    }
}
/*
5 3
4 1
3 1
5 3
-> 5 3 4 1 2

5 4
5 4
4 3
3 2
2 1
5 4 3 2 1


4 2
4 2
3 1
3 1 4 2

5 4
4 1
5 1
2 5
3 5
2 3 4 5 1

6 7
5 6
5 2
2 4
4 3
2 1
6 1
1 3
4 3 5 2 6 1

6 7
5 6
5 2
2 4
4 3
2 1
6 1
1 3
4 3 5 2 6 1

5 3
4 1
3 1
5 3
-> 5 3 4 1 2

 */
