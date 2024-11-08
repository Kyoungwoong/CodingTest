package Programmers.stackqueue;

import java.util.Stack;

public class Stock {
    private static int[] prices = {1, 2, 3, 2, 3}; // return 4,3,1,1,0

    public static void main(String[] args) {
//        prev();
        int[] ans = nov8();
    }

    private static int[] nov8() {
        int len = prices.length;
        int[] ans = new int[len];
        Stack<Integer> stack = new Stack<>();

        // 각 시간의 인덱스를 스택에 저장
        for (int i = 0; i < len; i++) {
            // 현재 가격이 스택의 마지막 가격보다 낮으면, 하락 시간 계산
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int idx = stack.pop();
                System.out.println("idx = " + idx);
                ans[idx] = i - idx; // 하락 시간 계산
            }
            stack.push(i); // 현재 인덱스를 스택에 추가
        }
        System.out.println();

        // 남아 있는 인덱스들은 끝까지 가격이 떨어지지 않음
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            System.out.println("idx = " + idx);
            ans[idx] = len - 1 - idx;
        }

        return ans;
    }

    private static void prev() {
        int len = prices.length;
        int[] answer = new int[len];
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                answer[i]++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            System.out.println("answer[i] = " + answer[i]);
        }
    }
}
