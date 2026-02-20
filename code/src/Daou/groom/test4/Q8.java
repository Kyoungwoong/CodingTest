package Daou.groom.test4;

import java.util.Arrays;

public class Q8 {
    private static int answer = Integer.MAX_VALUE, cur = 0;
    private static boolean[] visited;

    public static int solution(int[] card, int n) {
        int len = card.length;
        answer = Integer.MAX_VALUE;
        visited = new boolean[len];

        Arrays.sort(card);
        dfs(0, 0, n, len, card);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void dfs(int selectedCnt, int num, int target, int limit, int[] card) {
        if (selectedCnt == limit) {
            System.out.println(num);
            cur++;
            if (target == num) {
                answer = Math.min(answer, cur);
            }
            return;
        }

        for (int i = 0; i < limit; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(selectedCnt + 1, num * 10 + card[i], target, limit, card);
            visited[i] = false;
//            dfs(selectedCnt, num, target, limit, card);
        }
    }

    public static void main(String[] args) {
        int[] card1 = {1, 2, 1, 3};
        int n1 = 1312;
        int ret1 = solution(card1, n1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int[] card2 = {1, 1, 1, 2};
        int n2 = 1122;
        int ret2 = solution(card2, n2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
