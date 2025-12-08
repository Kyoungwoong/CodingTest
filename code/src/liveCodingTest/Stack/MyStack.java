package liveCodingTest.Stack;

public interface MyStack<T> {
    void push(T element);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}
