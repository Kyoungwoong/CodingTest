package Programmers.Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Marathon {
    private static HashMap<String, Integer> names = new HashMap<>();
//    private static String[] participant = {"leo", "kiki", "eden"};
//    private static String[] completion = {"eden", "kiki"}; // leo

//    private static String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
//    private static String[] completion = {"josipa", "filipa", "marina", "nikola"}; //"vinko"

    private static String[] participant = {"mislav", "stanko", "mislav", "ana"};
    private static String[] completion = {"stanko", "ana", "mislav"}; // "mislav"

    public static void main(String[] args) {
//        prev();
        String ans =  nov6();
    }

    private static String nov6() {
        Set<String> runners = new HashSet<>();

        for (String runner: participant) {
            if (!runners.contains(runner)) {
                runners.add(runner);
            } else {
                runners.remove(runner);
            }
        }

        for (String runner: completion) {
            if (runners.contains(runner)) {
                runners.remove(runner);
            } else {
                runners.add(runner);
            }
        }

        for (String ans: runners) {
            return ans;
        }
        return null;
    }

    private static void prev() {
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
