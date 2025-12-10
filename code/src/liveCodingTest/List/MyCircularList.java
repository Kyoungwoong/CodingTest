package liveCodingTest.List;

public class MyCircularList<T> implements MyList<T> {
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> tail; // tail.next 가 head
    private int size;

    public MyCircularList() {
        this.tail = null;
        this.size = 0;
    }

    private Node<T> head() {
        return (tail == null) ? null : tail.next;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T element) {

    }

    public int size() {
        return size;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (tail == null) {
            tail = newNode;
            tail.next = tail; // 자기 자신을 가리킴
        } else {
            newNode.next = tail.next; // 기존 head
            tail.next = newNode;      // 새 head
        }
        size++;
    }

    public void addLast(T value) {
        addFirst(value);
        tail = tail.next; // 방금 넣은 노드를 tail로 이동
    }

    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }

        Node<T> prev = head();
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        Node<T> newNode = new Node<>(value);
        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> cur = head();
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty list");
        }

        Node<T> head = head();
        T removed = head.value;

        if (size == 1) {
            tail = null;
        } else {
            tail.next = head.next; // 새로운 head
        }
        size--;
        return removed;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("empty list");
        }

        if (size == 1) {
            T removed = tail.value;
            tail = null;
            size = 0;
            return removed;
        }

        Node<T> prev = head();
        while (prev.next != tail) {
            prev = prev.next;
        }

        T removed = tail.value;
        prev.next = tail.next; // head
        tail = prev;
        size--;
        return removed;
    }

    public T remove(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        Node<T> prev = head();
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        Node<T> target = prev.next;
        prev.next = target.next;
        size--;
        return target.value;
    }

    public boolean remove(T value) {
        if (isEmpty()) return false;

        Node<T> head = head();

        // head 제거
        if (equalsValue(head.value, value)) {
            removeFirst();
            return true;
        }

        Node<T> prev = head;
        Node<T> cur = head.next;

        while (cur != head) { // 원형이라 head까지 돌아오면 끝
            if (equalsValue(cur.value, value)) {
                if (cur == tail) {
                    // tail 제거
                    prev.next = cur.next;
                    tail = prev;
                } else {
                    prev.next = cur.next;
                }
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    private boolean equalsValue(T a, T b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    public void clear() {
        tail = null;
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    // 한 바퀴만 출력
    public void print() {
        if (isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        Node<T> head = head();
        Node<T> cur = head;
        do {
            System.out.print(cur.value + " ");
            cur = cur.next;
        } while (cur != head);
        System.out.println();
    }
}
