package SWExpert.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1812 {
    static class Tile implements Comparable<Tile> {

        int min, max;

        public Tile(int a, int b) {
            this.min = Math.min(a, b);
            this.max = Math.max(a, b);
        }

        @Override
        public int compareTo(Tile o) {
            return Integer.compare(o.min, this.min);
        }
    }
    private static int tc, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Integer[] length = new Integer[N];
            for (int i = 0; i < N; i++) {
                int b = Integer.parseInt(st.nextToken());
                length[i] = b;
            }
            Arrays.sort(length, Collections.reverseOrder());
            PriorityQueue<Tile> pq = new PriorityQueue<>();
            int ans = 1;
            pq.add(new Tile(M, M));
            for (int i = 0; i < N; i++) {
                Tile tile = pq.poll();
                int len = (1 << length[i]);
                if (tile.min >= len) {
                    pq.add(new Tile(tile.max - len, tile.min));
                    pq.add(new Tile(tile.min - len, len));
                } else {
                    pq.add(tile);
                    pq.add(new Tile(M - len, len));
                    pq.add(new Tile(M, M - len));
                    ans++;
                }
            }

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
/*
5
1 2
1
2 5
2 2
3 6
1 2 1
7 277
1 3 6 3 8 2 6
4 6
2 2 2 2
 */
