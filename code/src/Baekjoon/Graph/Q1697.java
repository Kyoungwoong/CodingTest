package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1697 {
    private static int N, K, ans = Integer.MAX_VALUE;

    public static void find(int cur) {
        int[] arr = new int[100001];
        Arrays.fill(arr, Integer.MAX_VALUE);
        boolean[] visited = new boolean[100001];
        int[] dir = {-1, 1};
        Queue<Integer> q = new LinkedList<>();
        arr[cur] = 0;
        visited[cur] = true;
        q.add(cur);
        while (!q.isEmpty()) {
            int present = q.poll();
            int s = arr[present];
            for (int i = 0; i < 3; i++) {
                int next = 0;
                if (i == 0) {
                    next = present * 2;
                } else {
                    next = present + dir[i-1];
                }
                if (next < 0 || next > 100000) {
                    continue;
                }
                if (!visited[next]) {
                    q.add(next);
                    arr[next] = s + 1;
                    visited[next] = true;
                }
                if (next == K) {
                    System.out.println(s + 1);
                    System.exit(0);
                }
            }


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.println(0);
            System.exit(0);
        }
        find(N);
        System.out.println(ans);
    }
}
