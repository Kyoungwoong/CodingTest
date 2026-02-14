package Daou.Wikidocs;

import java.util.*;

public class Q8 {

    public static List<Integer> solution(int N, int[] votes) {

        int[] voteCounter = new int[N + 1];

        for (int v : votes) {
            voteCounter[v]++;
        }

        int maxVal = 0;
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, voteCounter[i]);
        }

        List<Integer> answer = new ArrayList<>();

        for (int idx = 1; idx <= N; idx++) {
            if (voteCounter[idx] == maxVal) {
                answer.add(idx);
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int N1 = 5;
        int[] votes1 = {1, 5, 4, 3, 2, 5, 2, 5, 5, 4};
        // 1: 1      2: 2       3: 1        4: 2        5: 4
        List<Integer> ret1 = solution(N1, votes1);
        System.out.println("solution 함수의 반환 값은 " + ret1 + " 입니다.");

        int N2 = 4;
        int[] votes2 = {1, 3, 2, 3, 2};
        List<Integer> ret2 = solution(N2, votes2);
        System.out.println("solution 함수의 반환 값은 " + ret2 + " 입니다.");
    }
}
