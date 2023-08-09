package Baekjoon.TopologySort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q3665 {
    private static int N;
    private static int[] previous;
    private static int[] indegree;
    private static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int N = Integer.parseInt(br.readLine()); // 5
            graph = new ArrayList<>();
            previous = new int[N + 1];
            indegree = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <= N; i++) {
                if (i == 0) {
                    graph.add(new ArrayList<>());
                }else {
                    previous[i] = Integer.parseInt(st.nextToken()); // 5 4 3 2 1
                    graph.add(new ArrayList<>());
                }
            }
            for (int i = 1; i <= N; i++) {
                int start = previous[i];
                for (int j = i + 1; j <= N; j++) {
                    graph.get(start).add(previous[j]);
                    indegree[previous[j]]++;
                }
            }
            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 2
                int b = Integer.parseInt(st.nextToken()); // 4
                if (graph.get(a).contains(b)) {
                    graph.get(a).remove((Integer) b);
                    graph.get(b).add(a);
                    indegree[a]++;
                    indegree[b]--;
                } else {
                    graph.get(b).remove((Integer) a);
                    graph.get(a).add(b);
                    indegree[a]--;
                    indegree[b]++;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            int cnt = 0;
            boolean check = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    cnt++;
                    q.add(i);
                }
            }

            if (cnt > 1) {
                sb.append("?");
            } else {
                for (int i = 1; i <= N; i++) {
                    if (q.isEmpty()) {
                        System.out.println("IMPOSSIBLE\n");
                        check = true;
                        break;
                    }

                    int prev = q.poll();
                    sb.append(prev + " ");
                    for (int j = 0; j < graph.get(prev).size(); j++) {
                        int post = graph.get(prev).get(j);
                        indegree[post]--;
                        if (indegree[post] == 0) {
                            q.add(post);
                        }
                    }
                }
                if(check) continue;
                System.out.println(sb);
            }
        }

    }
}
/*
1
5
1 4 3 5 2
2
1 2
1 3
1 4
 */
