package Programmers.stackqueue;

import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class IHateSameNumber {
//    private static int[] arr = new int[]{1, 1, 3, 3, 0, 1, 1};
    private static int[] arr = new int[]{4, 4, 4, 3, 3};

    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        for (int num : arr) {
            if (!stack.isEmpty()) {
                if (stack.peek() != num) {
                    stack.add(num);
                }
            } else {
                stack.add(num);
            }
        }
        int[] resultArray = new int[stack.size()];  // 스택 크기만큼의 배열 생성
        for (int i = 0; i < stack.size(); i++) {
            resultArray[i] = stack.get(i);  // Integer를 int로 변환하여 배열에 저장
        }

        // 배열 출력
        for (int num : resultArray) {
            System.out.println("num = " + num);
        }
    }
}
