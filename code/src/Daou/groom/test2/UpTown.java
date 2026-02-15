package Daou.groom.test2;

import java.util.*;

public class UpTown {

    public static int solution(int[] arr, int arrLen) {
        int answer = 1;

        int[] dp = new int[arrLen + 1];

        dp[1] = 1;
        for (int i = 2; i <= arrLen; i++) {
            if (arr[i - 1] > arr[i - 2]) {
                dp[i] = dp[i - 1] + 1;
                answer = Math.max(answer, dp[i]);
            } else {
                dp[i] = 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] arr = {3, 1, 2, 4, 5, 1, 2, 2, 3, 4};
        int ret = solution(arr, 10);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
