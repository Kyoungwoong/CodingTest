package Programmers.Hash;

import java.util.HashMap;

public class Marathon {
    private static HashMap<String, Integer> names = new HashMap<>();
//    private static String[] participant = {"leo", "kiki", "eden"};
//    private static String[] completion = {"eden", "kiki"}; // leo

//    private static String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//    private static String[] completion = {"josipa", "filipa", "marina", "nikola"}; //"vinko"

    private static String[] participant = {"mislav", "stanko", "mislav", "ana"};
    private static String[] completion = {"stanko", "ana", "mislav"}; // "mislav"

    public static void main(String[] args) {
        for (String name : participant) {
            names.put(name, names.getOrDefault(name, 0) + 1);
        }

        for (String name : completion) {
            if (names.get(name) == 1) {
                names.remove(name);
            } else {
                names.put(name, names.get(name) - 1);
            }
        }

        for (String name : names.keySet()) {
            System.out.println("name = " + name);
        }

    }
}
