package Programmers;

import java.util.*;

public class Order {
    //  코스 요리, 횟수                         코스 길이
    static HashMap<String, Integer>[] courses = new HashMap[11];
    static String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
    static int[] course = {2, 3, 4};

//    static String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//    static int[] course = {2, 3, 5};

    //    static String[] orders = {"XYZ", "XWY", "WXA"};
//    static int[] course = {2, 3, 4};

    public static void main(String[] args) {
        List<String> answerList = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            courses[i] = new HashMap<>();
        }

        int len = orders.length;
        String name;
        for(int i = 0; i < len; i++) {
            // orders[i]의 모든거 넣기.
            find(orders[i], i+1);
        }

        for(int i = 1; i <= len; i++) {
            System.out.print("length: " + i + ": ");
            for(Map.Entry<String, Integer> map : courses[i].entrySet()) {
                System.out.print(map.getKey() + " ");
            }
            System.out.println();
        }

        // course


        // 리스트를 배열로 변환
        String[] answer = answerList.toArray(new String[answerList.size()]);

        // 정렬
        Arrays.sort(answer);

        for(String ans: answer) {
            System.out.print(ans + " ");
        }
    }

    public static void find(String name, int idx) {
        int len = name.length();
        String std;
        for(int i = 0; i < len; i++) {
            for(int j = i; j < len; j++) {
                std = name.substring(i, j);
                courses[idx].put(std, courses[idx].getOrDefault(std, 0) + 1);
            }
        }
    }
}

