package Programmers.Sort;

import java.util.Arrays;

public class HIndex {
//    public static int[] citations = {3, 0, 6, 1, 5}; // 3
    public static int[] citations = {0, 5, 6, 7, 8}; // 4
//    public static int[] citations = {0, 1, 2, 3, 5, 5, 5, 6}; // 4
//    public static int[] citations = {0,0,0}; // 0
//    public static int[] citations = {4, 4, 4};

    public static void main(String[] args) {
        Arrays.sort(citations);
        int len = citations.length;
        int l = 0, r = citations[len -1], answer = 0;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (mid == 0 || (len >= mid && citations[len - mid] >= mid)) { // h편 이상일 때 => l을 증가
                answer = mid;
                l = mid + 1;
            } else { // h편 이하일 때 => r을 감소
                r = mid -1;
            }
        }

        System.out.println("h = " + answer);
    }
}
