package liveCodingTest.List;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {

    private int CAPACITY = 10;
    private T[] store;
    private int size = 0;

    public MyArrayList() {
        this.store = (T[]) new Object[this.CAPACITY];
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
        if (this.CAPACITY == this.size) {
            // 사이즈가 넘으면 배열 크기 더 길게
            this.CAPACITY *= 2;
            T[] nextStore = (T[]) new Object[this.CAPACITY];
            // Arrays.copyOf(store, nextStore, 0, this.size);
            for (int i = 0; i < this.size; i++) {
                nextStore[i] = store[i];
            }
            this.store = nextStore;
        }
        // 추가가 되면 그냥 추가
        this.store[this.size] = element;
        this.size++;
    }

    @Override
    public void add(int index, T element) {
        // 사이즈 확인
        if (this.CAPACITY == this.size) {
            this.CAPACITY *= 2;
            T[] nextStore = (T[]) new Object[this.CAPACITY];
            for (int i = 0; i < this.size; i++) {
                nextStore[i] = store[i];
            }
            this.store = nextStore;
        }

        this.size++;
        for (int i = this.size - 1; i >= 0; i--) {
            if (index == i) {
                this.store[i] = element;
                break;
            } else {
                this.store[i] = this.store[i - 1];
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        return this.store[index];
    }

    @Override
    public T remove(int index) {
        T removedItem = get(index);
        if (removedItem != null) {
            for (int i = index; i < this.size; i++) {
                this.store[i] = this.store[i + 1];
            }
            this.store[this.size] = null;
            this.size--;
        }
        return removedItem;
    }

    @Override
    public boolean remove(T element) {
        int removeSize = 0;
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.store[i] == element) {
                for (int j = i + 1; j < this.size - 1; j++) {
                    this.store[j - 1] = this.store[j];
                }
                removeSize++;
                this.store[this.size - removeSize] = null;
            }
        }
        this.size -= removeSize;
        return false;
    }

    @Override
    public void clear() {
        this.size = 0;
        Arrays.fill(this.store, null);
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.store[i] + " ");
        }
        System.out.println();
    }
}
