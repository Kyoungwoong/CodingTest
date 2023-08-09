package Baekjoon.TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2252 {
    private static int N, M;
    private static int[] arr;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static StringBuilder sb = new StringBuilder();
    private static Queue<Integer> q = new LinkedList<>();

    public static void topologySort() {
        while (!q.isEmpty()) {
            int num = q.poll();
            sb.append(num + " ");
            for (int i = 0; i < graph.get(num).size(); i++) {
                int next = graph.get(num).get(i);
                arr[next]--;
                if (arr[next] == 0 && !visited[next]) {
                    q.add(next);
                }
            }
        }
    }

    public static void push() {
        for (int i = 1; i <= N; i++) {
            if(arr[i] == 0 && !visited[i]){
                visited[i] = true;
                q.add(i);
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

        push();

        topologySort();

        System.out.println(sb);
    }
}
