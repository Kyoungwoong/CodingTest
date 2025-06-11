package Programmers.BruteForce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dict {
    private static String word = "AAAAE"; // 6
//    private static String word = "AAAE"; // 10
//    private static String word = "I"; // 1563
//    private static String word = "EIO"; // 1189

    private static char[] m = {'A', 'E', 'I', 'O', 'U'};
    private static List<String> list = new ArrayList<>();
    private static Map<String, Integer> map = new HashMap<>();
    private static int idx = 1;

    public static void main(String[] args) {
        flash("", 0);
//        int answer = 0;
//        for (String std : list) {
//            answer++;
//            if (std.equals(word)) {
//                System.out.println("answer = " + answer);
//                return;
//            }
//        }
        System.out.println("answer = " + map.get(word));
    }

    public static void flash(String current, int size) {
        if (size > 5) {
            return;
        }

        if (!current.isEmpty()) {
//            list.add(current);
            map.put(current, idx++);
        }

        for (int i = 0; i < 5; i++) {
            flash(current + m[i], size + 1);
        }
    }
}
