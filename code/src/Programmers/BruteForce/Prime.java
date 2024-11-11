package Programmers.BruteForce;

import java.util.*;

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
//        prev();
        nov11();
    }

    private static boolean[] isPrime;
    private static Set<Integer> uniqueNumbers = new HashSet<>();

    public static void initPrimeArray(int max) {
        isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j <= max; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    public static void findCombinations(String prefix, String str) {
        if (!prefix.isEmpty()) {
            int num = Integer.parseInt(prefix);
            if (isPrime[num]) {
                uniqueNumbers.add(num);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            findCombinations(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1));
        }
    }

    private static int nov11() {
        int maxNum = (int) Math.pow(10, numbers.length());
        initPrimeArray(maxNum);

        // 모든 조합을 탐색
        findCombinations("", numbers);

        return uniqueNumbers.size();
    }

    private static void prev() {
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
