package Programmers.DP;

public class Thief {
    static class Solution {

        public int solution(int[] money) {
            int n = money.length;
            /*
             * 첫번째 집을 포함하고 마지막 집을 빼는 경우
             * n까지 고려했을 때의 돈의 최댓값
             */
            int[] dp_includeFirst = new int[n];
            dp_includeFirst[0] = money[0];
            dp_includeFirst[1] = Math.max(money[1], money[0]);
            for(int i = 2; i < n-1; i++) {
                dp_includeFirst[i] = Math.max(money[i] + dp_includeFirst[i-2], dp_includeFirst[i-1]);
            }

            /*
             * 첫번째 집을 제외하고 마지막 집을 선택하는 경우
             * n까지 고려했을 때의 돈의 최댓값
             */
            int[] dp_excludeFirst = new int[n];
            dp_excludeFirst[0] = 0;
            dp_excludeFirst[1] = money[1];
            for(int i = 2; i < n; i++) {
                dp_excludeFirst[i] = Math.max(money[i] + dp_excludeFirst[i-2], dp_excludeFirst[i-1]);
            }

            return Math.max(dp_excludeFirst[n-1], dp_includeFirst[n-2]);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1, 2, 3, 1}) == 4);
    }
}
