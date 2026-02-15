package Daou.groom.test2;

import java.util.*;

public class Password {

    public static boolean solution(String password) {

        int length = password.length();

        for (int i = 0; i < length - 2; i++) {
            int firstCheck = password.charAt(i + 1) - password.charAt(i);
            int secondCheck = password.charAt(i + 2) - password.charAt(i + 1);

            if (firstCheck == secondCheck && (firstCheck == 1 || firstCheck == -1)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String password1 = "cospro890";
        boolean ret1 = solution(password1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        String password2 = "cba323";
        boolean ret2 = solution(password2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
