package Baekjoon.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q1991 {
    private static int N;
    private static char[][] graph;
    private static StringBuilder sb = new StringBuilder();

    public static void preVisit(char s) {
        if (s == '.') {
            return;
        }
        sb.append(s);
        preVisit(graph[s - 'A'][0]);
        preVisit(graph[s - 'A'][1]);
    }

    public static void midVisit(char s) {
        if (s == '.') {
            return;
        }
        midVisit(graph[s - 'A'][0]);
        sb.append(s);
        midVisit(graph[s - 'A'][1]);
    }

    public static void postVisit(char s) {
        if (s == '.') {
            return;
        }
        postVisit(graph[s - 'A'][0]);
        postVisit(graph[s - 'A'][1]);
        sb.append(s);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new char[N + 1][2];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                graph[i][j] = '.';
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = st.nextToken().charAt(0) - 'A';
            char l = st.nextToken().charAt(0);
            char r = st.nextToken().charAt(0);
            graph[p][0] = l;
            graph[p][1] = r;
        }

        preVisit('A');
        sb.append("\n");
        midVisit('A');
        sb.append("\n");
        postVisit('A');

        System.out.println(sb);
    }
}
