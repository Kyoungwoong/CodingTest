package liveCodingTest.List;

public class MyLinkedList<T> implements MyList<T> {

    private Node head = null, tail = null;
    private int size = 0;
    static class Node<T> {
        T item;
        Node next = null;

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
        Node<T> newNode = new Node(element);
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
        Node<T> newNode = new Node(element);
        if (index == 0) {
            // head 인 경우
            newNode.next = head;
            head = newNode;
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
        if (index >= this.size) {
            return null;
        }

        Node<T> curNode = head;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.item;
    }

    @Override
    public T remove(int index) {
        if (index >= this.size) {
            return null;
        }

        T removedItem = null;
        if (index == 0) {
            removedItem = (T) head.item;
            head = head.next;
        } else {
            Node<T> curNode = head;
            for (int i = 0; i < index - 1; i++) {
                curNode = curNode.next;
            }
            removedItem = (T) curNode.next.item;
            curNode.next = curNode.next.next;
        }
        this.size--;
        return removedItem;
    }

    @Override
    public boolean remove(T element) {
        Node<T> curNode = head;
        int removedSize = 0;
        while (!curNode.equals(tail)) {
            if (curNode.next.item.equals(element)) {
                // 다음게 같을 때
                curNode.next = curNode.next.next;
                removedSize++;
            } else {
                curNode = curNode.next;
            }
        }
        if (head.item.equals(element)) {
            head = head.next;
            removedSize++;
        }
        this.size -= removedSize;
        return true;
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
