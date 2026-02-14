package Daou.Wikidocs;

import java.util.*;

public class Q4 {

    public static long solution(long num) {
        long answer = 0;

        // 여기에 코드를 작성해주세요
        num += 1;
        int pow = 0;
        while (num != 0) {
            long remain = num % 10;
            if (remain == 0) {
                answer = answer + (1 * (long) Math.pow(10, pow++));
            } else {
                answer = answer + (remain * (long) Math.pow(10, pow++));
            }
            num /= 10;
        }


        return answer;
    }

    public static void main(String[] args) {

        long num = 999_999_999_999_999_999L;
        long ret = solution(num);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");

        num = 9_950_000L;
        ret = solution(num);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}

