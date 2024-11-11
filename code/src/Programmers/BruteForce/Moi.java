package Programmers.BruteForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Moi {
//    public static int[] answers = {1, 2, 3, 4, 5}; // [1]
    public static int[] answers = {1, 3, 2, 4, 2}; // [1,2,3]

    static class Rank implements Comparable<Rank> {
        int idx, correct;

        public Rank(int idx, int correct) {
            this.idx = idx;
            this.correct = correct;
        }

        @Override
        public int compareTo(Rank o) {
            if (this.correct != o.correct) {
                return o.correct - this.correct;
            }
            return this.idx - o.idx;
        }
    }

    public static void main(String[] args) {
//        prev();
        int[] ans = nov11();
    }

    private static int[] nov11() {
        int[][] omr = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] correctCount = new int[3]; // 정답 수를 저장할 배열

        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (omr[j][i % omr[j].length] == answers[i]) {
                    correctCount[j]++;
                }
            }
        }

        // 최대 정답 수 찾기
        int maxCorrect = Arrays.stream(correctCount).max().getAsInt();

        // 최대 정답 수와 일치하는 사람을 리스트에 추가
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (correctCount[i] == maxCorrect) {
                answer.add(i + 1); // 사람 번호는 1부터 시작하므로 i + 1
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void prev() {
        int[][] omr = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        Rank[] correct = new Rank[3];
        for (int i = 0; i < 3; i++) {
            correct[i] = new Rank(i + 1, 0);
        }
        int len = answers.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 3; j++) {
                int repeat = omr[j].length;
                if (omr[j][i % repeat] == answers[i]) {
                    correct[j].correct++;
                }
            }
        }

        Arrays.sort(correct);

        List<Integer> answer = new ArrayList<>();
        int max = correct[0].correct;
        for (Rank rank : correct) {
            if (max == rank.correct) {
                System.out.println("rank.idx = " + rank.idx + " " + rank.correct);
                answer.add(rank.idx);
            }
        }

        answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
