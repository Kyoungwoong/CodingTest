package Daou.groom.test3;

import java.util.ArrayList;
import java.util.List;

public class Q7 {
    public static List<Integer> solution(int k) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= k; i++) {
            int square_num = i * i;
            int divisor = 1;

            while (square_num / divisor != 0) {
                int front = square_num / divisor;
                int back = square_num % divisor;
                divisor *= 10;

                if (back != 0 && front != 0) {
                    if (front + back == i) {
                        answer.add(i);
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int k = 500;
        List<Integer> ret = solution(k);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
