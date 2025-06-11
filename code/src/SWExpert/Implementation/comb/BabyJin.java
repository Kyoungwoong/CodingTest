package SWExpert.Implementation.comb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BabyJin {
    static class Solution {
        private static int size;
        private static boolean ans;

        public static boolean solution(String[] list) {
            size = list.length;
            ans = false;

            boolean[] visited = new boolean[size];
            dfs(0, 0, list, visited);
            return ans;
        }

        private static void dfs(int cnt, int start, String[] list, boolean[] visited) {
            if (ans) return;
            if (cnt == 3) {
                // 선택된 3개, 선택 안된 3개로 나눔
                List<String> selected = new ArrayList<>();
                List<String> remain = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    if (visited[i]) selected.add(list[i]);
                    else remain.add(list[i]);
                }

                if ((isRun(selected) && isTriplet(remain))
                        || (isTriplet(selected) && isRun(remain))
                        || isRun(selected) && isRun(remain)
                        || isTriplet(selected) && isTriplet(remain)) {
                    ans = true;
                }
                return;
            }

            for (int i = start; i < size; i++) {
                visited[i] = true;
                dfs(cnt + 1, i + 1, list, visited);
                visited[i] = false;
            }
        }

        private static boolean isRun(List<String> nums) {
            List<Integer> intList = nums.stream().map(Integer::parseInt).sorted().toList();
            return intList.get(0) + 1 == intList.get(1) && intList.get(1) + 1 == intList.get(2);
        }

        private static boolean isTriplet(List<String> nums) {
            return nums.get(0).equals(nums.get(1)) && nums.get(1).equals(nums.get(2));
        }
    }

    public static void main(String[] args) throws IOException {
        String[] list = {"8", "7", "7", "2", "3", "7"};
        System.out.println(Solution.solution(list));
        String[] babyJin = {"1", "2", "3", "5", "5", "5"};
        System.out.println(Solution.solution(babyJin));
    }
}
