package Daou.groom.test3;

import java.util.*;

public class Q1 {

    public static int[] func_a(int[] arr) {
        int n = arr.length;
        int[] ret = new int[n * 2];
        for (int i = 0; i < n; i++) {
            ret[i] = arr[i];
            ret[i + n] = arr[i];
        }
        return ret;
    }

    public static boolean func_b(int[] first, int[] second) {
        int MAX_NUMBER = 1001;
        int[] counter = new int[MAX_NUMBER];
        for (int i = 0; i < MAX_NUMBER; i++) counter[i] = 0;

        int len = Math.min(first.length, second.length);
        for (int i = 0; i < len; i++) {
            int f = first[i];
            int s = second[i];
            counter[f] += 1;
            counter[s] -= 1;
        }

        for (int c : counter) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean func_c(int[] first, int[] second) {
        int length = second.length;
        for (int i = 0; i < length; i++) {
            boolean same = true;
            for (int j = 0; j < length; j++) {
                if (first[i + j] != second[j]) {
                    same = false;
                    break;
                }
            }
            if (same) return true;
        }
        return false;
    }

    public static boolean solution(int[] arrA, int[] arrB) {
        if (arrA.length != arrB.length) {
            return false;
        }
        if (func_b(arrA, arrB)) {
            int[] arrA_temp = func_a(arrA);
            if (func_c(arrA_temp, arrB)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arrA1 = {1, 2, 3, 4};
        int[] arrB1 = {3, 4, 1, 2};
        boolean ret1 = solution(arrA1, arrB1);

        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int[] arrA2 = {1, 2, 3, 4};
        int[] arrB2 = {1, 4, 2, 3};
        boolean ret2 = solution(arrA2, arrB2);

        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
