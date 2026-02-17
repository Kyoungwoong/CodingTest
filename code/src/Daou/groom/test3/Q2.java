package Daou.groom.test3;

import java.util.*;

public class Q2 {

    public static boolean func_a(List<String> arr, String s) {
        return arr.contains(s);
    }

    public static boolean func_b(String s) {
        int length = s.length();
        for (int i = 0; i < length / 2; i++) {
            if (s.charAt(i) != s.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static String func_c(List<String> palindromes, int k) {
        Collections.sort(palindromes);
        if (palindromes.size() < k) {
            return "NULL";
        } else {
            return palindromes.get(k - 1);
        }
    }

    public static String solution(String s, int k) {
        List<String> palindromes = new ArrayList<>();
        int length = s.length();

        for (int start_idx = 0; start_idx < length; start_idx++) {
            for (int cnt = 1; cnt < length - start_idx + 1; cnt++) {
                String sub_s = s.substring(start_idx, start_idx + cnt);
                if (func_b(sub_s)) {
                    if (!func_a(palindromes, sub_s)) {
                        palindromes.add(sub_s);
                    }
                }
            }
        }

        String answer = func_c(palindromes, k);
        return answer;
    }

    public static void main(String[] args) {
        String s1 = "abcba";
        int k1 = 4;
        String ret1 = solution(s1, k1);

        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        String s2 = "ccddcc";
        int k2 = 7;
        String ret2 = solution(s2, k2);

        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
