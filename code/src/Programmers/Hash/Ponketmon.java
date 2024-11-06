package Programmers.Hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Ponketmon {
    private static HashSet<Integer> pokets = new HashSet<>();
//    private static Integer[] nums = {3,1,2,3}; // 2
//    private static Integer[] nums = {3,3,3,2,2,4}; // 3
    private static Integer[] nums = {3,3,3,2,2,2}; // 2
    public static void main(String[] args) {
//        prev();
        int ans = nov6();
    }

    private static int nov6() {
        Map<Integer, Integer> ponkets = new HashMap<>();
        for (int num: nums) {
            ponkets.put(num, ponkets.getOrDefault(num, 0) + 1);
        }

        int halfN = nums.length / 2;
        if (halfN <= ponkets.size()) {
            return halfN;
        } else {
            return ponkets.size();
        }
    }

    private static void prev() {
        for (Integer pocketmon : nums) {
            if (!pokets.contains(pocketmon)) {
                pokets.add(pocketmon);
            }
        }
        int len = nums.length / 2;

        if (len <= pokets.size()) {
            System.out.println("len = " + len);
        } else {
            System.out.println("nums.length = " + pokets.size());
        }

    }
}
