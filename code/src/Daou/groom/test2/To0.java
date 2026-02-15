package Daou.groom.test2;

import java.util.*;

public class To0 {

    public static String solution(String s) {

        s = s + "#";
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0' && s.charAt(i + 1) != '0') {
                answer.append('0');
            } else if(s.charAt(i) != '0') {
                answer.append('1');
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {

        String s = "101100011100";
        String ret = solution(s);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}

