package Programmers.implementation;

import java.io.*;
import java.util.*;

public class Compression {

    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.solution("KAKAO"));
    }

    private static void print(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    static class Solution {
        static Map<String, Integer> dict = new HashMap<>();

        public static int[] solution(String msg) {
            List<Integer> answerList = new ArrayList<>();
            init();

            int len = msg.length();
            int addNum = 27;
            for(int t = 0; t < len; ) {
                // to find known Str
                // until to know
                int end = t + 1;
                while (end <= msg.length() && dict.containsKey(msg.substring(t, end))) {
                    end++;
                }

                String knownStr = msg.substring(t, end - 1);
                answerList.add(dict.get(knownStr));

                // 사전 등록
                if (end <= len) {
                    String newEntry = msg.substring(t, end);
                    dict.put(newEntry, addNum++);
                }

                t = end - 1;
            }

            return answerList.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }

        private static void init() {
            for(int i = 0; i < 26; i++) {
                dict.put(String.valueOf((char) ('A' + i)), i + 1);
            }
        }
    }
}
