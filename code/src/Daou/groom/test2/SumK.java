package Daou.groom.test2;

import java.util.*;

public class SumK {
    static boolean[] visited;
    static int answer = 0;

    public static int solution(int[] arr, int K) {

        // 여기에 코드를 작성하세요.
        visited = new boolean[arr.length];
        dfs(0, arr.length, 0, 0, K, arr);

        return answer;
    }

    private static void dfs(int cur, int limit, int select, int num, int K, int[] arr) {
        if (select == 3) {
            if (num % K == 0) {
                answer++;
            }
            return;
        }

        for (int i = cur; i < limit; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            dfs(i + 1, limit, select + 1, num + arr[i], K, arr);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int K = 3;

        int ret = solution(arr, K);
        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
