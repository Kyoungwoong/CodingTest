package Programmers;

import java.util.Stack;

public class Move110 {
    public static void main(String[] args) {
        String[] s = {"1110", "100111100", "0111111010"};
        int len = s.length;
        String[] answer = new String[len];

        for(int i = 0; i < len; i++) {
            int zeroIdx = -1;
            Stack<Character> stack = new Stack<>();
            String str = s[i];
            int n = str.length();
            int count = 0;

            for (int j = 0; j < n; j++) {
                stack.push(str.charAt(j));

                if (stack.size() >= 3) {
                    char first = stack.pop();
                    if (first != '0') {
                        stack.push(first);
                        continue;
                    }
                    char second = stack.pop();
                    if (second != '1') {
                        stack.push(second);
                        stack.push(first);
                        continue;
                    }
                    char third = stack.pop();
                    if (third != '1') {
                        stack.push(third);
                        stack.push(second);
                        stack.push(first);
                        continue;
                    }
                    count++;
                }
            } // 110 갯수
            int size = stack.size();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < size; j++) {
                char cur = stack.pop();
                if (cur == '0' && zeroIdx == -1) {
                    zeroIdx = j;
                }
                sb.insert(0, cur);
            }
            if (zeroIdx == -1) {
                zeroIdx = 0;
            }
            while (count-- > 0) {
                sb.insert(zeroIdx, "110");
            }
            answer[i] = sb.toString();
            System.out.println(answer[i]);
        }
    }
}

