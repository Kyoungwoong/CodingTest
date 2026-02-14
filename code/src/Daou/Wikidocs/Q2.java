package Daou.Wikidocs;

import java.util.*;

public class Q2 {

    public static String funcA(String str, int length) {
        StringBuilder padZero = new StringBuilder();
        int padSize = length - str.length();   // 빈칸 ①R

        for (int i = 0; i < padSize; i++) {
            padZero.append("0");
        }

        return padZero.toString() + str;
    }

    public static int solution(String binaryA, String binaryB) {

        int maxLength = Math.max(binaryA.length(), binaryB.length());

        binaryA = funcA(binaryA, maxLength);
        binaryB = funcA(binaryB, maxLength);

        int hammingDistance = 0;

        for (int i = 0; i < maxLength; i++) {
            if (binaryA.charAt(i) != binaryB.charAt(i)) {   // 빈칸 ②
                hammingDistance++;
            }
        }

        return hammingDistance;
    }

    public static void main(String[] args) {

        String binaryA = "10010";
        String binaryB = "110";

        int ret = solution(binaryA, binaryB);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
