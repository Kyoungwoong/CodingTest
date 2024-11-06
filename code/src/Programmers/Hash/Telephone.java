package Programmers.Hash;

import java.util.*;

public class Telephone {
    private static HashSet<String> prefix = new HashSet<>();
//    private static String[] phone_book = {"119", "97674223", "1195524421"}; // false
//    private static String[] phone_book = {"123","456","789"}; // true
    private static String[] phone_book = {"12","123","1235","567","88"}; // false
    public static void main(String[] args) {
//        prev();
        boolean ans = nov6();
    }

    static class PhoneBook {
        // 트라이 노드 클래스 정의
        static class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isEndOfNumber;
        }

        // 루트 노드
        private TrieNode root = new TrieNode();

        // 번호를 트라이에 삽입
        private boolean insert(String number) {
            TrieNode current = root;
            for (char c : number.toCharArray()) {
                current.children.putIfAbsent(c, new TrieNode());
                current = current.children.get(c);

                // 번호의 중간에서 이미 끝인 경우 접두어 조건 위반
                if (current.isEndOfNumber) {
                    return false;
                }
            }

            // 번호가 완전히 끝난 경우를 표시
            current.isEndOfNumber = true;

            // 현재 노드에 자식이 있는 경우 접두어 조건 위반
            return current.children.isEmpty();
        }

        public boolean isConsistent(String[] phoneBook) {
            for (String number : phoneBook) {
                if (!insert(number)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean nov6() {
        // 이거 시간초과
//        Arrays.sort(phone_book, (a, b) -> b.length() - a.length());
//        Set<String> phones = new HashSet<>();
//
//        for (String phone: phone_book) {
//            int len = phone.length();
//            for (String saveNum: phones) {
//                System.out.println(saveNum + " length: " + saveNum.length() + " " + phone + " length: " + phone.length());
//                if (saveNum.length() >= phone.length() && saveNum.substring(0, len).equals(phone)) {
//                    return false;
//                }
//            }
//            phones.add(phone);
//        }
//        return true;

        PhoneBook phoneBookChecker = new PhoneBook();
        return phoneBookChecker.isConsistent(phone_book);
    }

    private static void prev() {
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
