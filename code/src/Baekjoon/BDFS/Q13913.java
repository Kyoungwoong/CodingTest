package Baekjoon.BDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q13913 {

    static int MAX = 100_001;
    static int[] dist = new int[MAX];
    static int[] prev = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        dist[N] = 0;
        prev[N] = -1;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : new int[]{now - 1, now + 1, now * 2}) {
                if (0 <= next && next < MAX && dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    prev[next] = now;
                    q.add(next);
                }
            }
        }

        // 출력
        System.out.println(dist[K]);
        List<Integer> path = new ArrayList<>();
        for (int i = K; i != -1; i = prev[i]) {
            path.add(i);
        }
        Collections.reverse(path);
        for (int x : path) System.out.print(x + " ");
    }
}
