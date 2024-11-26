package Programmers.BinarySearch;

import java.util.*;

public class Immigration {
    static class Solution {

        public long solution(int n, int[] times) {
            Arrays.sort(times);
            long answer = 0;
            long left = 1;
            long right = (long)times[times.length-1] * n; // 60

            while (left <= right) {
                long mid = (left + right) / 2; // 중간 시간
                long peopleProcessed = 0;

                // mid 시간 동안 처리 가능한 총 인원 계산
                for (int time : times) {
                    peopleProcessed += mid / time;
                    if (peopleProcessed >= n) break; // n명을 초과하면 더 계산할 필요 없음
                }

                if (peopleProcessed >= n) {
                    answer = mid; // 더 작은 시간을 탐색
                    right = mid - 1;
                } else {
                    left = mid + 1; // 시간을 더 늘림
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(6, new int[]{7, 10}) == 28);
    }
}
