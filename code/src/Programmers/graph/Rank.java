package Programmers.graph;

import java.util.*;

public class Rank {

    static class Solution {
        List<List<Integer>> wins = new ArrayList<>();
        List<List<Integer>> loses = new ArrayList<>();

        public int solution(int n, int[][] results) {
            int answer = 0;

            for(int i = 0; i <= n; i++) {
                wins.add(new ArrayList<>());
                loses.add(new ArrayList<>());
            }

            for(int[] result: results) {
                int win = result[0];
                int lose = result[1];
                wins.get(win).add(lose);
                loses.get(lose).add(win);
            }

            for(int i = 1; i <= n; i++) {
                boolean[] visited = new boolean[n + 1];
                Queue<Integer> q = new LinkedList<>();

                visited[i] = true;
                q.add(i);
                while(!q.isEmpty()) {
                    int win = q.poll();
                    for(int lose: wins.get(win)) {
                        if (visited[lose]) continue;
                        visited[lose] = true;
                        q.add(lose);
                    }
                }

                q.add(i);
                while(!q.isEmpty()) {
                    int lose = q.poll();
                    for(int win: loses.get(lose)) {
                        if (visited[win]) continue;
                        visited[win] = true;
                        q.add(win);
                    }
                }

                boolean flag = true;
                for(int j = 1; j <= n; j++) {
                    if(!visited[j]) {
                        flag = false;
                    }
                }
                if (flag) {
                    answer++;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}) == 2);

    }
}
