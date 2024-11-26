package Programmers.BinarySearch;

import java.util.*;

public class SteppingStones {
    static class Solution {
        public int solution(int distance, int[] rocks, int n) {
            int answer = 0;
            Arrays.sort(rocks);

            int left = 1;
            int right = distance;

            // 바위의 최솟값을 이분탐색
            while(left <= right) {
                int mid = (left + right) / 2;
                int deleteRocks = 0;
                int prevRock = 0;

                for(int rock: rocks) {
                    if (Math.abs(rock - prevRock) < mid) {
                        deleteRocks++;
                    } else {
                        prevRock = rock;
                    }
                }

                if (distance - prevRock < mid) {
                    deleteRocks++;
                }

                if (deleteRocks > n) {
                    right = mid - 1;
                } else if (deleteRocks <= n) {
                    left = mid + 1;
                    answer = mid;
                }
            }

            return answer;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(25	, new int[]{2, 14, 11, 21, 17}, 2) == 4);
    }

}
