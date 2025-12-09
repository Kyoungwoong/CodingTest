package liveCodingTest.Queue;

public interface MyQueue<T> {
    void offer(T element); // 큐 뒤에 추가 (enqueue)
    T poll();              // 앞에서 꺼내기 (dequeue)
    T peek();              // 앞 요소 조회 (제거하지 않음)
    boolean isEmpty();
    int size();
}
