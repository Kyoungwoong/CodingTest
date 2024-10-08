package Programmers.BFSDFS;

import java.util.ArrayList;
import java.util.List;

public class ChangeWord {
//    private static String begin = "hit";
//    private static String target = "cog";
//    private static String[] words = {"hot", "dot", "dog", "lot", "log", "cog"}; // 4

    private static String begin = "hit";
    private static String target = "cog";
    private static String[] words = {"hot", "dot", "dog", "lot", "log"}; // 0

    private static List<Integer> list;
    private static boolean[] visited;
    private static int len, answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        boolean flag = false;
        for (String word : words) {
            if (word.equals(target)) {
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("0 = " + 0);
            return;
        }

        len = words.length;
        list = new ArrayList<>();
        visited = new boolean[len];

        init(0, 0);

        System.out.println("answer = " + answer);
    }

    public static boolean canChange(String s1, String s2) {
        int strLength = s1.length();
        int diff = 0;
        for (int i = 0; i < strLength; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff == 2) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void init(int num, int size) {
        if (size == len) {
            String word = begin;
            int change = 0;
            for (int ber : list) {
                if (canChange(word, words[ber])) {
                    change++;
                    word = words[ber];
                    if (word.equals(target)) {
                        answer = Math.min(answer, change);
                    }
                } else {
                    return;
                }
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(i);

                init(num, size + 1);

                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
