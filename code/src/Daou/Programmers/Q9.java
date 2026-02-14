package Daou.Programmers;

import java.util.*;

public class Q9 {
    public int solution(int[] arr, int K) {
        // 여기에 코드를 작성해주세요.
        int answer = Integer.MAX_VALUE;
        int len = arr.length;
        Arrays.sort(arr);
        for (int i = 0; i <= len - K; i++) {
            answer = Math.min(answer, arr[i + K - 1] - arr[i]);
        }
        return answer;
    }

    // 아래는 테스트케이스 출력을 해보기 위한 main 메소드입니다.
    public static void main(String[] args) {
        Q9 sol = new Q9();
        int[] arr = {4, 6, 9, 9, 11, 19};
        int K = 4;
        int ret = sol.solution(arr, K);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution 메소드의 반환 값은 " + ret + "입니다.");

        int[] arr2 = {1, 3, 5, 8, 11, 16, 23, 27};
        K = 6;
        ret = sol.solution(arr2, K);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution2 메소드의 반환 값은 " + ret + "입니다.");

        int[] arr3 = {1, 2, 4, 1000, 1583, 5834, 10000};
        K = 4;
        ret = sol.solution(arr3, K);

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("solution3 메소드의 반환 값은 " + ret + "입니다.");
    }
}