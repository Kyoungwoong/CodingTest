package Programmers.stackqueue;

import java.util.Stack;

public class CorrectBracket {
//    private static String s = "()()"; // true
//    private static String s = "(())()"; // treu
//    private static String s = ")()("; // false
    private static String s = "(()("; // false

    private static Stack<Character> validator = new Stack<>();
    public static void main(String[] args) {
        int len = s.length();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                validator.add('(');
            } else {
                if (validator.isEmpty()) {
                    System.out.println(false);
                    return;
                } else {
                    validator.pop();
                }
            }
        }

        if (validator.isEmpty()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }

    }
}
