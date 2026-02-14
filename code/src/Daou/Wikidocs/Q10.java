package Daou.Wikidocs;

import java.util.*;

public class Q10 {

    public static int solution(int[] prices) {
        int answer = Integer.MIN_VALUE;

        // 여기에 코드를 작성하세요.
        int minPrice = prices[0];
        int len = prices.length;
        for (int i = 1; i < len; i++) {
            answer = Math.max(answer, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3};
        System.out.println("solution 함수의 반환 값은 " + solution(prices1) + " 입니다.");

        int[] prices2 = {3, 1};
        System.out.println("solution 함수의 반환 값은 " + solution(prices2) + " 입니다.");
    }
}
