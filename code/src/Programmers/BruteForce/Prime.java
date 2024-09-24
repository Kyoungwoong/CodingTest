package Programmers.BruteForce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prime {
//    private static String numbers = "011"; // 2
    private static String numbers = "17"; // 3

    private static Set<Integer> numberSet = new HashSet<>();

    public static void makeNumberToString(String prefix, String remaining) {
    }

    public static void makeNumberToList(int num, int cnt) {

    }

    public static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        makeNumberToString("", numbers);
        makeNumberToList(0, 0);

        // Set에서 소수 찾기
        int primeCount = 0;
        for (int num : numberSet) {
            if (isPrime(num)) {
                primeCount++;
            }
        }

        // 결과 출력
        System.out.println("Number of primes: " + primeCount);
    }
}
