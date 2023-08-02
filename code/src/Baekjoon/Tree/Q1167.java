package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node {
    int dest, cost;

    public Node(int dest, int cost) {
        this.dest = dest;
        this.cost = cost;
    }
}

public class Q1167 {
    private static int V, last, max = -1;
    private static boolean[] visited;
    private static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void dfs(int s, int cost) {
        if (cost > max) {
            max = cost;
            last = s;
        }

        visited[s] = true;

        for (int i = 0; i < graph.get(s).size(); i++) {
            Node dest = graph.get(s).get(i);
            if (!visited[dest.dest]) {
                dfs(dest.dest, cost + dest.cost);
                visited[dest.dest] = true;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        V = Integer.parseInt(br.readLine());
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int std = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int dest = Integer.parseInt(st.nextToken());
                if (dest == -1) {
                    break;
                }
                int dist = Integer.parseInt(st.nextToken());
                graph.get(std).add(new Node(dest, dist));
            }
        }

        visited = new boolean[V + 1];
        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(last, 0);

        System.out.println(max);
    }
}
