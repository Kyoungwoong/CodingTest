package liveCodingTest.List;

public interface MyList<T> {

    /**
     * 현재 저장된 요소 개수 반환
     * @return
     */
    public int size();

    /**
     * 리스트가 비어있느 지 여부
     * @return
     */
    public boolean isEmpty();

    /**
     * 리스트 끝에 요소 추가
     * @param element
     */
    void add(T element);

    /**
     * 특정 위치에 요소 삽입
     */
    void add(int index, T element);

    /**
     * 인덱스 위치의 요소 반환
     */
    T get(int index);

    /**
     * 해당 위치의 요소 삭제 후 반환
     */
    T remove(int index);

    /**
     * 동일한 요소를 찾아 삭제
     */
    boolean remove(T element);

    /**
     * 모든 요소 삭제
     */
    void clear();

    void print();
}
