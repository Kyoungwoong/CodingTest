# 📘 Queue & Circular Queue 구현하기 (Java)

## 📌 공통 개요

이 과제에서는 **큐(Queue)** 자료구조를 두 가지 방식으로 직접 구현한다.

1. **일반 Queue** – 단순 배열 기반 구현 (비원형, non-circular)
2. **Circular Queue** – 배열 기반 **원형 큐(Circular Queue)** 구현

Java의 `Queue`, `LinkedList`, `ArrayDeque` 등의 라이브러리는 사용하지 않고,  
내부 배열을 직접 사용해 자료구조를 구현해야 한다.

두 구현 모두 공통 인터페이스 `MyQueue<T>`를 따른다.

---

## 🧩 공통 인터페이스: MyQueue\<T>

```java
public interface MyQueue<T> {
    void offer(T element); // 큐 뒤에 추가 (enqueue)
    T poll();              // 앞에서 꺼내기 (dequeue)
    T peek();              // 앞 요소 조회 (제거하지 않음)
    boolean isEmpty();
    int size();
}
```

## 🔹 문제 1: 배열 기반 Queue 구현 (`MyArrayQueue`)

### 요구사항

- `MyQueue<T>` 인터페이스를 구현하는 `MyArrayQueue<T>` 클래스를 작성한다.
- 내부적으로 `T[] store` 배열을 사용하여 큐를 구현한다.
- 간단한 구현을 위해 **`poll` 시 앞 요소들을 한 칸씩 왼쪽으로 당기는 방식**을 사용해도 된다.

### 설계 조건

- 초기 용량은 **10**.
- 요소 개수가 배열 크기를 초과하게 되면, **배열 크기를 2배로 확장**한다.
- `offer()` 는 큐의 **뒤(end)** 에 추가한다.
- `poll()` 은 큐의 **앞(front)** 에서 제거한다.
- `peek()` 은 큐의 **앞(front)** 요소를 반환하되 제거하지 않는다.
- 비어 있는 상태에서 `poll()` 또는 `peek()` 를 호출하면  
  **`IllegalStateException` 을 던진다.**

---

## 🔹 문제 2: 배열 기반 Circular Queue 구현 (`MyCircularQueue`)

### 요구사항

- `MyQueue<T>` 인터페이스를 구현하는 `MyCircularQueue<T>` 클래스를 작성한다.
- 내부적으로 `T[] store` 배열을 사용하여 **원형 큐(Circular Queue)** 를 구현한다.
- `front`, `rear`, `size` 를 이용해 순환 구조를 관리한다.

### 설계 조건

- 초기 용량은 **10**.
- 인덱스는 다음과 같이 순환한다.

```java
nextIndex = (currentIndex + 1) % capacity;
```

* 큐가 가득 찬 상태에서 offer() 를 호출하면:
  * 배열 크기를 2배로 확장하고 
  * 원소들을 0번 인덱스부터 연속되도록 재배치한 뒤 
  * `front = 0`, `rear = size`로 설정한다. 
* 비어 있는 상태에서 poll() 또는 peek() 를 호출하면 `IllegalStateException` 을 던진다.

--- 

## 🧪 테스트 (Main)

MyArrayQueue<Integer> 와 MyCircularQueue<Integer> 를 각각 생성하여
동일한 시나리오로 테스트한다.

### 테스트 시나리오

1. 초기 상태 출력 
  * isEmpty, size 등 
2. offer(10), offer(20), offer(30) 수행 후 상태 확인 
    * size 값 
    * peek() 결과 (맨 앞 요소)
    * 큐 내부 요소 순서 
3. poll() 한 번 수행 후 상태 확인 
    * 반환값 (10 예상)
    * size 값 
    * peek() 결과 
4. 추가로 offer(40), offer(50) 수행 후 상태 확인 
    * size 값 
    * peek() 결과 
    * 큐 내부 요소가 FIFO 순서로 유지되는지 확인 
5. 모두 poll() 하면서 요소가 FIFO 순서로 나오는지 확인 
    * 10, 20, 30, 40, 50 순서(상황에 따라 앞에서 이미 하나 뺀 경우는 그에 맞게)
6. 빈 상태에서 poll() / peek() 호출 시 예외 발생 여부 확인 
    * IllegalStateException 이 발생하는지 확인
  