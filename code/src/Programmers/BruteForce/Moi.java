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
