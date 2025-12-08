package liveCodingTest.Stack;

import java.util.Arrays;

public class MyArrayStack<T> implements MyStack<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] store;
    private int top;

    @SuppressWarnings("unchecked")
    public MyArrayStack() {
        this.store = (T[]) new Object[DEFAULT_SIZE];
        this.top = -1;
    }

    private void ensureCapacity() {
        if (top == store.length - 1) {
            int newCapacity = store.length * 2;
            store = Arrays.copyOf(store, newCapacity);
        }
    }

    @Override
    public void push(T element) {
        ensureCapacity();
        store[++top] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("empty stack");
        }
        T removedItem = store[top];
        /**
         * 가비지 컬렉터 입장에서 “아직 사용 중인 것”처럼 보여서
         * 오래 사는 스택에서는 메모리 누수(정확히는 loitering) 가 될 수 있어요.
         */
        store[top] = null; // 메모리 누수 방지
        top--;
        return removedItem;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("empty stack");
        }
        return store[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }
}
