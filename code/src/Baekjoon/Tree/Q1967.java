package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q1967 {

    private static int N, max = Integer.MIN_VALUE;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> distance = new ArrayList<>();

    public static int dfs(int s, int cost) {
        visited[s] = true;

        for (int i = 0; i < tree.get(s).size(); i++) {
            Node dest = tree.get(s).get(i);
            if (!visited[dest.dest]) {
                distance.get(s).add(dfs(dest.dest, dest.cost));
            }
        }

        if (distance.get(s).size() == 0) {
            return cost;
        }

        Collections.sort(distance.get(s), Collections.reverseOrder());
        return cost + distance.get(s).get(0);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
            distance.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree.get(p).add(new Node(c, d));
        }

        visited = new boolean[N + 1];
        dfs(1, 0);

        for (int i = 1; i <= N; i++) {
            int sum = 0;
//            for (int j = 0; j < distance.get(i).size(); j++) {
////                System.out.print(distance.get(i).get(j) + " ");
//                sum += distance.get(i).get(j);
//            }
            if (distance.get(i).size() >= 2) {
                sum += distance.get(i).get(1) + distance.get(i).get(0);
            } else if (distance.get(i).size() == 1) {
                sum += distance.get(i).get(0);
            }
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
