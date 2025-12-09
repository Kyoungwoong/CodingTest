package liveCodingTest.Queue;

import java.util.Arrays;

public class MyArrayQueue<T> implements MyQueue<T> {
    private final int DEFAULT_SIZE = 10;
    private T[] store;
    private int size;

    public MyArrayQueue() {
        store = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == store.length) {
            store = Arrays.copyOf(store, store.length * 2);
        }
    }

    @Override
    public void offer(T element) {
        ensureCapacity();
        store[size++] = element;
    }

    @Override
    public T poll() {
        if (size == 0) {
            throw new IllegalStateException("empty Queue");
        }
        T polledItem = store[0];
        for (int i = 1; i < size; i++) {
            store[i - 1] = store[i];
        }
        store[size - 1] = null;
        size--;
        return polledItem;
    }

    @Override
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("empty Queue");
        }
        return store[0];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(store[i]).append(" ");
        }
        return sb.toString();
    }
}
