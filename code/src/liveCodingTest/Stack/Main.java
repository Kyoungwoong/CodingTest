package liveCodingTest.Stack;

public class Main {
    public static void main(String[] args) {

        // ==========================
        // 1. ArrayStack 기본 테스트
        // ==========================
        System.out.println("===== ArrayStack Test =====");

        MyStack<Integer> stack = new MyArrayStack<>();

        System.out.println("[초기 상태]");
        System.out.println("isEmpty: " + stack.isEmpty());
        System.out.println("size   : " + stack.size());
        System.out.println();

        System.out.println("[push 3번: 10, 20, 30]");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("size   : " + stack.size());
        System.out.println("peek   : " + stack.peek()); // 30 예상
        System.out.println("isEmpty: " + stack.isEmpty());
        System.out.println();

        System.out.println("[pop 1번]");
        Integer popped = stack.pop();
        System.out.println("pop    : " + popped);       // 30 예상
        System.out.println("size   : " + stack.size()); // 2 예상
        System.out.println("peek   : " + stack.peek()); // 20 예상
        System.out.println();

        System.out.println("[모두 pop]");
        while (!stack.isEmpty()) {
            System.out.println("pop    : " + stack.pop());
        }
        System.out.println("size   : " + stack.size());
        System.out.println("isEmpty: " + stack.isEmpty());
        System.out.println();

        System.out.println("[빈 스택에서 pop/peek 예외 테스트]");
        try {
            stack.pop();
        } catch (RuntimeException e) { // 네 구현에 맞게 EmptyStackException/IllegalStateException 등으로 바꿔도 됨
            System.out.println("빈 스택 pop 예외 발생: " + e);
        }

        try {
            stack.peek();
        } catch (RuntimeException e) {
            System.out.println("빈 스택 peek 예외 발생: " + e);
        }

        System.out.println();
    }
}
/**
 ===== ArrayStack Test =====
 [초기 상태]
 isEmpty: true
 size   : 0

 [push 3번: 10, 20, 30]
 size   : 3
 peek   : 30
 isEmpty: false

 [pop 1번]
 pop    : 30
 size   : 2
 peek   : 20

 [모두 pop]
 pop    : 20
 pop    : 10
 size   : 0
 isEmpty: true

 [빈 스택에서 pop/peek 예외 테스트]
 빈 스택 pop 예외 발생: java.lang.IllegalStateException: Stack is empty
 빈 스택 peek 예외 발생: java.lang.IllegalStateException: Stack is empty

 ===== MinStack Test =====

 */