package Programmers.BFSDFS;

import java.util.ArrayList;
import java.util.List;

public class Network {
//    private static int n = 3;
//    private static int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}; // 2

    private static int n = 3;
    private static int[][] computers = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}; // 1

    private static boolean[] visited;
    private static int answer = 0;

    public static boolean connect(int start) {
        int[] connection = computers[start];
        boolean flag = false;
        if (!visited[start]) {
            flag = true;
        }

        for (int i = 0; i < n; i++) {
            if (i != start && connection[i] == 1 && !visited[i]) {
                visited[i] = true;
                flag = true;
                connect(i);
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        prev();
        int ans = nov20();
    }

    public static void dfs(int n, int start, int[][] computers) {
        visited[start] = true;
        for(int i = 0; i < n; i++) {
            if (i == start) continue;
            if (!visited[i] && computers[start][i] == 1) {
                dfs(n, i, computers);
            }
        }
    }

    private static int nov20() {
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(n, i, computers);
                answer++;
            }
        }

        return answer;
    }

    private static void prev() {
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (connect(i)) {
                answer++;
            }
        }

        System.out.println("answer = " + answer);
    }
}
