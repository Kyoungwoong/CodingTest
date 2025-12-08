package liveCodingTest.Stack;

public class MyArrayStack<T> implements MyStack<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] store;
    private int top;

    public MyArrayStack() {
        this.store = (T[]) new Object[DEFAULT_SIZE];
        top = -1;
    }

    @Override
    public void push(T element) {
        if (top == store.length - 1) {
            T[] newStore = (T[]) new Object[store.length * 2];
            for (int i = 0; i < store.length; i++) {
                newStore[i] = store[i];
            }
            store = newStore;
        }
        store[++top] = element;
    }

    @Override
    public T pop() {
        if (top == -1) {
            throw new IllegalStateException("no item");
        }

        T removedItem = store[top--];
        return removedItem;
    }

    @Override
    public T peek() {
        if (top == -1) {
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
        return (top + 1);
    }
}
