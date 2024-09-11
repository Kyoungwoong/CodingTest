package Programmers.Hash;

import java.util.HashMap;

public class Clothes {
    private static final HashMap<String, Integer> matches = new HashMap<>();
    private static String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}; // 5
//    private static String[][] clothes = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}}; // 3

    public static void main(String[] args) {
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
