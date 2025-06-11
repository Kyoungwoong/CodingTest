package SWExpert.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1251 {

    static class Node implements Comparable<Node> {
        int idx;
        double d;

        public Node(int idx, double d) {
            this.idx = idx;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.d, o.d);
        }
    }

    private static int testCase, N;
    private static long ans;
    private static double[] x, y;
    private static double E;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= testCase; t++) {
            N = Integer.parseInt(br.readLine());
            x = new double[N];
            y = new double[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                x[i] = Double.parseDouble(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                y[i] = Double.parseDouble(st.nextToken());
            }

            E = Double.parseDouble(br.readLine());

            ans = cal();

            sb.append("#").append(t).append(" ").append(ans);
            if (t != testCase) {
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long cal() {
        boolean[] visited = new boolean[N];
        double[] d = new double[N];
        Arrays.fill(d, Double.MAX_VALUE);
        d[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(0, 0));

        double sum = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curIdx = cur.idx;
            double curD = cur.d;

            if (visited[curIdx]) continue;

            visited[curIdx] = true;
            sum += curD;
            count++;
            if (count == N) break;

            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;

                double LPow = (x[curIdx] - x[i]) * (x[curIdx] - x[i]) +
                        (y[curIdx] - y[i]) * (y[curIdx] - y[i]);

                if (d[i] > LPow) {
                    d[i] = LPow;
                    pq.add(new Node(i, LPow));
                }
            }
        }

        return Math.round(sum * E);
    }
}
/*
10
2
0 0
0 100
1.0
4
0 0 400 400
0 100 0 100
1.0
6
0 0 400 400 1000 2000
0 100 0 100 600 2000
0.3
9
567 5 45674 24 797 29 0 0 0
345352 5464 145346 54764 5875 0 3453 4545 123
0.0005
 */
