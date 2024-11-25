package Programmers.DP;

public class Operations {

    static class Solution {
        public int solution(String[] arr) {
            int n = arr.length / 2 + 1; // 숫자의 개수
            int[][] dpMax = new int[n][n];
            int[][] dpMin = new int[n][n];

            // 숫자 초기화
            for (int i = 0; i < n; i++) {
                dpMax[i][i] = Integer.parseInt(arr[i * 2]);
                dpMin[i][i] = Integer.parseInt(arr[i * 2]);
            }

            // 구간 길이
            for (int len = 1; len < n; len++) {
                for (int i = 0; i < n - len; i++) {
                    int j = i + len;
                    dpMax[i][j] = Integer.MIN_VALUE;
                    dpMin[i][j] = Integer.MAX_VALUE;

                    // i부터 j까지 연산자에 대한 분할 계산
                    for (int k = i; k < j; k++) {
                        String operator = arr[k * 2 + 1];
                        int maxLeft = dpMax[i][k];
                        int minLeft = dpMin[i][k];
                        int maxRight = dpMax[k + 1][j];
                        int minRight = dpMin[k + 1][j];

                        if (operator.equals("+")) {
                            dpMax[i][j] = Math.max(dpMax[i][j], maxLeft + maxRight);
                            dpMin[i][j] = Math.min(dpMin[i][j], minLeft + minRight);
                        } else if (operator.equals("-")) {
                            dpMax[i][j] = Math.max(dpMax[i][j], maxLeft - minRight);
                            dpMin[i][j] = Math.min(dpMin[i][j], minLeft - maxRight);
                        }
                    }
                }
            }

            return dpMax[0][n - 1]; // 전체 구간의 최댓값 반환
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new String[]{"1", "-", "3", "+", "5", "-", "8"}) == 1);
        System.out.println(solution.solution(new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"}) == 3);
    }

}
