package Programmers.Hash;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Telephone {
    private static HashSet<String> prefix = new HashSet<>();
//    private static String[] phone_book = {"119", "97674223", "1195524421"}; // false
//    private static String[] phone_book = {"123","456","789"}; // true
    private static String[] phone_book = {"12","123","1235","567","88"}; // false
    public static void main(String[] args) {
        Arrays.sort(phone_book, (a, b) -> Integer.compare(a.length(), b.length()));
        boolean answer = true;
        int n = phone_book.length;

        for(int i = 0; i < n; i++) {
            prefix.add(phone_book[i]);
        }

        for(int i = 0 ; i < n; i++) {
            int len = phone_book[i].length();
            for(int j = 0; j < len; j++) {
                if(prefix.contains(phone_book[i].substring(0, j))){
                    answer = false;
                    break;
                }
            }
        }
        System.out.println("answer = " + answer);
    }
}
