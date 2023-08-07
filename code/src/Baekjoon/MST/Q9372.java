package Baekjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Q9372 {

    private static class Plane {
        int src, dest;

        public Plane(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }
    private static int N, M, ans = Integer.MAX_VALUE;
    private static HashSet<Integer> remain;
    private static ArrayList<ArrayList<Integer>> roads;

    public static void travel(int start, int cnt) {
        if (!remain.contains(start)) {
            return;
        }

        System.out.println("travel start: " + start + " cnt: " + cnt + " hash size: " + remain.size());

        remain.remove(start);

        if (remain.size() == 1) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 0; i < roads.get(start).size(); i++) {
            travel(roads.get(start).get(i), cnt + 1);
            remain.add(start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            roads = new ArrayList<>();
            remain = new HashSet<>();
            for (int i = 0; i <= N; i++) {
                roads.add(new ArrayList<>());
                remain.add(i);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                roads.get(s).add(e);
                roads.get(e).add(s);
            }

            ans = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                travel(i, 0);
            }
            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }
}
