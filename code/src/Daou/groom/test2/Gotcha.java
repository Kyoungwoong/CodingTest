package Daou.groom.test2;

import java.util.*;

public class Gotcha {

    public static int funcA(int n) {
        int ret = 1;
        while (n > 0) {
            ret *= 10;
            n -= 1;
        }
        return ret;
    }

    public static int funcB(int n) {
        int ret = 0;
        while (n > 0) {
            ret += 1;
            n /= 10;
        }
        return ret;
    }

    public static int funcC(int n) {
        int ret = 0;
        while (n > 0) {
            ret += n % 10;
            n /= 10;
        }
        return ret;
    }

    public static int solution(int num) {

        int nextNum = num;

        while (true) {
            nextNum += 1;

            int length = funcB(nextNum);   // 빈칸 ①

            // 자리수가 짝수
            if (length % 2 == 1) {
                continue;
            }

            int divisor = funcA(length / 2);  // 빈칸 ②

            int front = nextNum / divisor;
            int back = nextNum % divisor;

            int frontSum = funcC(front); // 빈칸 ③
            int backSum = funcC(back);  // 빈칸 ④

            if (frontSum == backSum) {
                break;
            }
        }

        return nextNum - num;
    }

    public static void main(String[] args) {

        int num1 = 1;
        int ret1 = solution(num1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int num2 = 235386;
        int ret2 = solution(num2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
