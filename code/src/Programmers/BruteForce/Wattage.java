package Programmers.BruteForce;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        prev();
        System.out.println(nov11());
    }

    public static int[] parent;

    public static void init(int n) {
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    public static int findParent(int n) {
        if (parent[n] != n) {
            parent[n] = findParent(parent[n]);
        }
        return parent[n];
    }

    public static void union(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int nov11() {
        int answer = Integer.MAX_VALUE;
        int len = wires.length;
        for(int i = 0; i < len; i++) {
            // i는 끊는
            parent = new int[n+1];
            init(n);

            for(int j = 0; j < len; j++) {
                if(i == j) {
                    continue;
                }
                if(findParent(wires[j][0]) != findParent(wires[j][1])) {
                    union(wires[j][0], wires[j][1]);
                }
            }

            // 각 송전탑의 최상위 부모 노드 갱신 및 개수 세기
            Map<Integer, Integer> connect = new HashMap<>();
            for (int j = 1; j <= n; j++) {
                int root = findParent(j);
                connect.put(root, connect.getOrDefault(root, 0) + 1);
            }

            // 두 개의 그룹으로 나뉘었는지 확인하고 차이 계산
            if (connect.size() == 2) {
                List<Integer> counts = new ArrayList<>(connect.values());
                int diff = Math.abs(counts.get(0) - counts.get(1));
                answer = Math.min(answer, diff);
            }

        }
        return answer;
    }

    private static void prev() {
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
