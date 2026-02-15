package Daou.groom.test2;

import java.util.*;

public class Change {

    public static int solution(int money) {

        int[] coin = {10, 50, 100, 500, 1000, 5000, 10000, 50000};
        int counter = 0;
        int idx = coin.length - 1;

        while (money > 0) {
            counter += (money / coin[idx]);   // 빈칸 ①
            money %= coin[idx];     // 빈칸 ②
            idx -= 1;       // 빈칸 ③
        }

        return counter;
    }

    public static void main(String[] args) {

        int money = 2760;
        int ret = solution(money);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
