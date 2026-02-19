package Daou.groom.test4;

import java.util.ArrayList;
import java.util.List;

public class Q6 {
    public static int power(int base, int exponent) {
        int val = 1;
        for (int i = 0; i < exponent; i++) {
            val *= base;
        }
        return val;
    }

    public static List<Integer> solution(int k) {
        List<Integer> answer = new ArrayList<>();

        int bound = power(10, k);
//        System.out.println(bound); // 10의 K제곱부터

        for (int i = bound / 10; i < bound; i++) {
            // 100부터 ~ 999까지
            int current = i;
            int calculated = 0;

            while (current != 0) {
                int num = current % 10;
                current /= 10;
                calculated += power(num, k);
            }

            if (calculated == i) {
                answer.add(i);
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 3;
        List<Integer> ret = solution(k);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
