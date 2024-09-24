package Programmers.BruteForce;

import java.util.ArrayList;
import java.util.List;

public class Firodo {
    private static int k = 80;
    private static int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}}; // 3

    private static int answer = 0;
    private static List<Integer> list = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) {
        // "최소 필요 피로도"는 해당 던전을 탐험하기 위해 가지고 있어야 하는 최소한의 피로도
        // "소모 피로도"는 던전을 탐험한 후 소모되는 피로도

        // 재귀로 순서를 매기고 ...
        int len = dungeons.length;
        visited = new boolean[len];

        for (int i = 0; i < len; i++) {
            init(len);
        }

        System.out.println("answer = " + answer);
    }

    private static void init(int size) {
        if (list.size() == size) {
            int test = k;
            boolean flag = true;
            for (int dungeon : list) {
                if (dungeons[dungeon][0] <= test) {
                    test -= dungeons[dungeon][1];
                } else {
                    flag = false;
                }
            }
            if (flag) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(i);

                init(size);

                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }
}

