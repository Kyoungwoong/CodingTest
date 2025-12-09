package liveCodingTest.Queue;

public class MyCircularQueue<T> implements MyQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] store;
    private int front;
    private int rear;
    private int size;

    @SuppressWarnings("unchecked")
    public MyCircularQueue() {
        this.store = (T[]) new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @Override
    public void offer(T element) {
        ensureCapacity();

        store[rear] = element;
        rear = (rear + 1) % store.length;
        size++;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }

        T value = store[front];
        store[front] = null; // 메모리 누수 방지
        front = (front + 1) % store.length;
        size--;

        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("queue is empty");
        }
        return store[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == store.length) {
            int newCapacity = store.length * 2;
            @SuppressWarnings("unchecked")
            T[] newStore = (T[]) new Object[newCapacity];

            // front부터 size만큼 순서대로 재배치
            for (int i = 0; i < size; i++) {
                newStore[i] = store[(front + i) % store.length];
            }

            store = newStore;
            front = 0;
            rear = size;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int idx = front;

        for (int i = 0; i < size; i++) {
            sb.append(store[idx]);
            if (i < size - 1) sb.append(", ");
            idx = (idx + 1) % store.length;
        }
        sb.append("]");
        return sb.toString();
    }
}
