package Programmers.Hash;

import java.util.HashSet;

public class Ponketmon {
    private static HashSet<Integer> pokets = new HashSet<>();
//    private static Integer[] nums = {3,1,2,3}; // 2
//    private static Integer[] nums = {3,3,3,2,2,4}; // 3
    private static Integer[] nums = {3,3,3,2,2,2}; // 2
    public static void main(String[] args) {
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
