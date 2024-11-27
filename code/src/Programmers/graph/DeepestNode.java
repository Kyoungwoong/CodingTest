package Programmers.graph;

import java.util.*;

public class DeepestNode {

    static class Solution {
        boolean[] visited;
        int[] step;
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        public void bfs() {
            while(!q.isEmpty()) {
                int src = q.poll();
                for(int desc: graph.get(src)) {
                    if (!visited[desc]) {
                        visited[desc] = true;
                        step[desc] = step[src] + 1;
                        q.add(desc);
                    }
                }
            }
        }

        public void init(int n) {
            visited = new boolean[n + 1];
            step = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            q.add(1);
            visited[1] = true;
            step[1] = 1;
        }

        public int solution(int n, int[][] edge) {
            int answer = 1;

            init(n);

            for(int[] vertex: edge) {
                int src = vertex[0];
                int desc = vertex[1];
                graph.get(src).add(desc);
                graph.get(desc).add(src);
            }

            bfs();

            Arrays.sort(step);
            int max = Integer.MIN_VALUE;
            for(int i = 1; i <= n; i++) {
                if (max < step[i]) {
                    answer = 1;
                    max = Math.max(max, step[i]);
                } else if (max == step[i]) {
                    answer++;
                }
                // System.out.print(step[i] + " ");
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}) == 3);
    }
}
