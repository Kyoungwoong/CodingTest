package Daou.Wikidocs;

import java.util.*;

public class Q7 {

    public static List<Integer> solution(int[] arrA, int[] arrB) {

        int arrAIdx = 0;
        int arrBIdx = 0;
        int arrALen = arrA.length;
        int arrBLen = arrB.length;

        List<Integer> answer = new ArrayList<>();

        while (arrAIdx < arrALen && arrBIdx < arrBLen) {   // 빈칸 ①
            if (arrA[arrAIdx] < arrB[arrBIdx]) {
                answer.add(arrA[arrAIdx]);
                arrAIdx++;
            } else {
                answer.add(arrB[arrBIdx]);
                arrBIdx++;
            }
        }

        while (arrAIdx < arrALen) {   // 빈칸 ②
            answer.add(arrA[arrAIdx]);
            arrAIdx++;
        }

        while (arrBIdx < arrBLen) {   // 빈칸 ③
            answer.add(arrB[arrBIdx]);
            arrBIdx++;
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] arrA = {-2, 3, 5, 9};
        int[] arrB = {0, 1, 5};

        List<Integer> ret = solution(arrA, arrB);

        System.out.println("solution 함수의 반환 값은 " + ret + " 입니다.");
    }
}
