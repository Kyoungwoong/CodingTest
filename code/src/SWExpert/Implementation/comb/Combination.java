package SWExpert.Implementation.comb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Combination {
    static class Solution {
        private static int size;
        private static List<String> arr = new ArrayList<>();

        public static void solution(String[] list, int limit) {
            size = list.length;

            dfs(0, 0, list, limit);
        }

        private static void dfs(int cnt, int start, String[] list, int limit) {
            if (cnt == limit) {
                for (String s : arr) {
                    System.out.print(s + " ");
                }
                System.out.println();
                return;
            }

            for (int i = start; i < size; i++) {
                arr.add(list[i]);
                dfs(cnt + 1, i + 1, list, limit);
                arr.remove(arr.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Solution.solution(new String[] {"a", "b", "c", "d", "e"}, 3);
    }
}
