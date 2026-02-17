package Daou.groom.test3;

import java.util.ArrayList;
import java.util.List;

public class Q6 {
    public static int solution(int n) {
        int answer = 0;

        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        for (int i = 3; i <= n; i += 2) {
            boolean is_prime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    is_prime = false;
                    break;
                }
            }
            if (is_prime) {
                primes.add(i);
            }
        }

        int prime_len = primes.size();
        for (int i = 0; i < prime_len - 2; i++) {
            for (int j = i + 1; j < prime_len - 1; j++) {
                for (int k = j + 1; k < prime_len; k++) {
                    if (primes.get(i) + primes.get(j) + primes.get(k) == n) {
                        answer += 1;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n1 = 33;
        int ret1 = solution(n1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int n2 = 9;
        int ret2 = solution(n2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
