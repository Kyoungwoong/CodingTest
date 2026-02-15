package Daou.groom.test2;

import java.util.*;

public class Rule {

    public static int[] solution(int[] arr) {

        int left = 0;
        int right = arr.length - 1;
        int idx = 0;

        int[] answer = new int[arr.length];

        while (left <= right) {
            if (idx % 2 == 0) {
                answer[idx] = arr[left];
                left++;
            } else {
                answer[idx] = arr[right];
                right--;
            }
            idx++;
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] ret = solution(arr);

        System.out.println("solution 함수의 반환 값은 " + Arrays.toString(ret) + " 입니다.");
    }
}
