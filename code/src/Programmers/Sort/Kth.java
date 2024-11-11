package Programmers.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kth {
    private static int[] array = {1, 5, 2, 6, 3, 7, 4};
    private static int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}; // [5,6,3]

    public static void main(String[] args) {
//        prev();
        int[] ans = nov11();
    }

    private static int[] nov11() {
        List<Integer> answerList = new ArrayList<>();

        for (int[] command: commands) {
            int i = command[0] - 1;
            int j = command[1];
            int k = command[2] - 1;
            int[] subArray = Arrays.copyOfRange(array, i, j);
            Arrays.sort(subArray);
            answerList.add(subArray[k]);
        }

        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void prev() {
        List<Integer> answer = new ArrayList<>();
        for (int[] cur : commands) {
            int i = cur[0], j = cur[1], k = cur[2];
            int[] splitArray = Arrays.copyOfRange(array, i - 1, j);

            Arrays.sort(splitArray);
            answer.add(splitArray[k - 1]);
        }

        System.out.println("answer = " + answer);
//        answer.stream().toArray();
    }
}
