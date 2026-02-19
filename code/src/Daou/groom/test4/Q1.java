package Daou.groom.test4;

import java.util.*;

public class Q1 {

    static List<String> words = new ArrayList<>();

    static void create_words(int lev, String s) {
        char[] VOWELS = {'A', 'E', 'I', 'O', 'U'};
        words.add(s);
        for (int i = 0; i < 5; i++) {
            if (lev < 5) {
                create_words(lev + 1, s + VOWELS[i]);
            }
        }
    }

    public static int solution(String word) {
        words = new ArrayList<>();
        int answer = 0;
        create_words(0, "");
        for (int idx = 0; idx < words.size(); idx++) {
            if (word.equals(words.get(idx))) {
                answer = idx;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String word1 = "AAAAE";
        int ret1 = solution(word1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        String word2 = "AAAE";
        int ret2 = solution(word2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
