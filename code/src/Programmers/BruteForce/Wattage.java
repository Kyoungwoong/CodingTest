package Programmers.BruteForce;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Wattage {
//    private static int n = 9;
//    private static int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}; // 3

//    private static int n = 4;
//    private static int[][] wires = {{1, 2}, {2, 3}, {3, 4}}; // 0

    private static int n = 7;
    private static int[][] wires = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}; // 1

    private static List<List<Integer>> tops;
    private static boolean[] visited;
    private static int std = 0;

    public static void main(String[] args) {
        int answer = Integer.MAX_VALUE;
        int len = wires.length;
        for (int i = 0; i < len; i++) {
            tops = new ArrayList<>();
            for (int k = 0; k <= n; k++) {
                tops.add(new ArrayList<>());
            }
            visited = new boolean[n + 1];
            std = 1;

            for (int j = 0; j < len; j++) {
                if (i != j) {
                    int v1 = wires[j][0];
                    int v2 = wires[j][1];
                    tops.get(v1).add(v2);
                    tops.get(v2).add(v1);
                }
            }

            int connect = find(1);
            if (n - connect < connect) {
                connect = n - connect;
            }

            answer = Math.min(answer, (n - 2*connect));
        }

        System.out.println("answer = " + answer);
    }

    public static int find(int start) {
        visited[start] = true;
        List<Integer> wire = tops.get(start);
        for (int num : wire) {
            if (!visited[num]) {
                std++;
                visited[num] = true;
                find(num);
            }
        }
        return std;
    }
}
