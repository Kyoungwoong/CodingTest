package liveCodingTest.List;

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> head = null, tail = null;
    private int size = 0;
    static class Node<T> {
        T item;
        Node<T> next = null;

        public Node(T item) {
            this.item = item;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        this.size++;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {            // 처음 넣을 때 tail 처리
                tail = newNode;
            }
        } else if (index == size) {     // 맨 뒤에 추가
            tail.next = newNode;
            tail = newNode;
        } else {
            Node<T> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }
            newNode.next = curNode.next;
            curNode.next = newNode;
        }
        this.size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        Node<T> curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.item;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        T removedItem;
        if (index == 0) {
            removedItem = head.item;
            head = head.next;
            if (head == null) {     // list가 비어버린 경우
                tail = null;
            }
        } else {
            Node<T> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }
            Node<T> target = curNode.next;
            removedItem = target.item;
            curNode.next = target.next;

            if (target == tail) {   // 마지막 원소 삭제 시 tail 이동
                tail = curNode;
            }
        }
        this.size--;
        return removedItem;
    }

    @Override
    public boolean remove(T element) {
        if (head == null) {
            return false;
        }

        boolean removed = false;

        // head부터 같은 값 제거
        while (head != null &&
                (head.item == element || (head.item != null && head.item.equals(element)))) {
            head = head.next;
            size--;
            removed = true;
        }

        if (head == null) {
            tail = null;
            return removed;
        }

        Node<T> prev = head;
        Node<T> cur = head.next;

        while (cur != null) {
            if (cur.item == element || (cur.item != null && cur.item.equals(element))) {
                prev.next = cur.next;
                size--;
                removed = true;
                if (cur == tail) {      // tail 갱신
                    tail = prev;
                }
                cur = prev.next;
            } else {
                prev = cur;
                cur = cur.next;
            }
        }

        return removed;
    }

    @Override
    public void clear() {
        this.size = 0;
        head = tail = null;
    }

    public void print() {
        Node<T> curNode = head;
        for (int i = 0; i < this.size; i++) {
            System.out.print(curNode.item + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }
}
