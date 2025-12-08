package liveCodingTest.List;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] store;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.store = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity() {
        if (size == store.length) {
            int newCapacity = store.length * 2;
            store = Arrays.copyOf(store, newCapacity);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        store[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        ensureCapacity();

        // 뒤에서부터 한 칸씩 밀기
        for (int i = size; i > index; i--) {
            store[i] = store[i - 1];
        }
        store[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        return this.store[index];
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }

        T removedItem = store[index];

        // 앞으로 당기기
        for (int i = index; i < size - 1; i++) {
            store[i] = store[i + 1];
        }

        store[size - 1] = null;
        size--;

        return removedItem;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? store[i] == null : element.equals(store[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(this.store, 0, size, null); // size까지만 비워도 충분
        this.size = 0;
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.store[i] + " ");
        }
        System.out.println();
    }
}
