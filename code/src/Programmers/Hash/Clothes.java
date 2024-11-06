package Programmers.Hash;

import java.util.HashMap;
import java.util.Map;

public class Clothes {
    private static final HashMap<String, Integer> matches = new HashMap<>();
    private static String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}; // 5
//    private static String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}; // 3

    public static void main(String[] args) {
//        prev();
        int ans = nov6();
    }

    private static int nov6() {
        // 옷을 항목마다 다 입는게 아니라 하나만 입어도 됨.
        int len = clothes.length;
        Map<String, Integer> matches = new HashMap<>();

        for(int i = 0; i < len; i++) {
            String item = clothes[i][0];
            String kind = clothes[i][1];
            matches.put(kind, matches.getOrDefault(kind, 0) + 1);
        }
        int answer = 1;

        for(String key: matches.keySet()) {
            answer *= matches.get(key) + 1;
        }

        // 전부 다 안입는 경우 제외
        return answer - 1;
    }

    private static void prev() {
        int answer = 1;
        int len = clothes.length;
        for(int i = 0; i < len; i++) {
            String kind = clothes[i][1];
            matches.put(kind, matches.getOrDefault(kind, 0) + 1);
        }

        for(String key: matches.keySet()) {
            answer *= matches.get(key) + 1;
        }

        System.out.println("answer = " + (answer - 1));
    }
}
