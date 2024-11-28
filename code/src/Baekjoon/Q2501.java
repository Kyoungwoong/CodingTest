package Baekjoon;

import java.util.ArrayList;
import java.util.List;

public class Q2501 {
    // https://www.acmicpc.net/problem/2501
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(6, 3) == 3);
        System.out.println(solution.solution(25, 4) == 0);
        System.out.println(solution.solution(2735, 1) == 1);
    }

    static class Solution {
        private int[] numbers;
        private List<Integer> factors;

        public int solution(int N, int K) {
            factors = new ArrayList<>();
            numbers = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                if (N % i == 0) {
                    factors.add(i);
                }
            }

            if (factors.size() >= K) {
                return factors.get(K - 1);
            }
            return 0;
        }
    }
}
