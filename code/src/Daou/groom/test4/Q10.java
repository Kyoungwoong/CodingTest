package Daou.groom.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q10 {
    private static boolean[] isPrime;
    private static final int SIZE = 1_000_000;
    private static List<Integer> primeNumber;

    private static void init() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < SIZE; i++) {
            if (!isPrime[i]) continue;
            primeNumber.add(i);
            for (int j = i + i; j < SIZE; j += i) {
                isPrime[j] = false;
            }
        }
    }

    public static int solution(int a, int b) {
        int answer = 0;
        isPrime = new boolean[SIZE];
        primeNumber = new ArrayList<>();
        init();

        // 여기에 코드를 작성해주세요.
        for (int p : primeNumber) {
            long sq = 1L * p * p;
            if (sq > b) break;

            if (a <= sq && sq <= b) answer++;

            long cu = sq * p; // p^3
            if (a <= cu && cu <= b) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int a = 6;
        int b = 30;
        int ret = solution(a, b);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
