package Baekjoon;

import java.io.*;
import java.util.*;

public class Q2504 {
    // https://www.acmicpc.net/problem/2504
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String brackets = br.readLine();

        Stack<Character> stack = new Stack<>();
        int result = 0;
        int temp = 1;

        for (int i = 0; i < brackets.length(); i++) {
            char current = brackets.charAt(i);

            if (current == '(') {
                stack.add(current);
                temp *= 2;
            } else if (current == '[') {
                stack.add(current);
                temp *= 3;
            } else if (current == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    result = 0;
                    break;
                }

                if (brackets.charAt(i - 1) == '(') {
                    result += temp;
                }
                stack.pop();
                temp /= 2;
            } else if (current == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    result = 0;
                    break;
                }
                if (brackets.charAt(i - 1) == '[') {
                    result += temp;
                }
                stack.pop();
                temp /= 3;
            }
            System.out.println("current: " + current + " temp: " + temp + " result: " + result);
        }

        if (!stack.isEmpty()) {
            result = 0;
        }

        System.out.println(result);
    }
}

