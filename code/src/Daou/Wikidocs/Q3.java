package Daou.Wikidocs;

import java.util.*;

public class Q3 {

    public static int funcA(int numA, int numB, char exp) {
        if (exp == '+') return numA + numB;
        else if (exp == '-') return numA - numB;
        else return numA * numB; // exp == '*'
    }

    public static int funcB(String exp) {
        for (int i = 0; i < exp.length(); i++) {
            char value = exp.charAt(i);
            if (value == '+' || value == '-' || value == '*') {
                return i;
            }
        }
        return -1;
    }

    public static int[] funcC(String exp, int idx) {
        int numA = Integer.parseInt(exp.substring(0, idx));
        int numB = Integer.parseInt(exp.substring(idx + 1));
        return new int[]{numA, numB};
    }

    public static int solution(String expression) {
        int expIndex = funcB(expression);  // 빈칸 ①
        int[] nums = funcC(expression, expIndex);    // 빈칸 ②
        int result = funcA(nums[0], nums[1], expression.charAt(expIndex));    // 빈칸 ③
        return result;
    }

    public static void main(String[] args) {
        String expression = "123+12";
        int ret = solution(expression);
        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
