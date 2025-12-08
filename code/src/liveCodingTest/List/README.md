# 📘 List 구현하기 (Java)

## 📌 개요
기본 자료구조 중 하나인 **리스트(List)**를 직접 구현한다.  
Java의 `ArrayList`, `LinkedList` 등을 사용하지 않고, **내부 구조를 직접 설계**하여 리스트 기능을 제공해야 한다.

본 과제는 **자바 문법 이해**, **객체 지향 설계 능력**, **자료구조 동작 원리 이해**를 평가하기 위해 만들어졌다.

---

## 🎯 요구사항

### 구현해야 하는 클래스
- `MyList` 인터페이스
- `MyArrayList` 클래스
- (선택) `MyLinkedList` 클래스

### ✔ 필수: `MyArrayList`
배열 기반으로 동작하는 리스트를 구현한다.

### ✔ 선택: `MyLinkedList`
단일 연결 리스트 기반으로 동작하는 리스트를 구현한다.  
※ 선택 과제지만 구현 시 가산점.

---

## 🧱 기능 요구사항

### 1) 기본 메서드 구현

아래 메서드들은 `MyList` 인터페이스에 선언하고,  
`MyArrayList`, `MyLinkedList`에서 각각 구현한다.

| 메서드 | 설명 |
|--------|------|
| `int size()` | 현재 저장된 요소 개수 반환 |
| `boolean isEmpty()` | 리스트가 비어있는지 여부 |
| `void add(T element)` | 리스트 끝에 요소 추가 |
| `void add(int index, T element)` | 특정 위치에 요소 삽입 |
| `T get(int index)` | 인덱스 위치의 요소 반환 |
| `T remove(int index)` | 해당 위치의 요소 삭제 후 반환 |
| `boolean remove(T element)` | 동일한 요소를 찾아 삭제 |
| `void clear()` | 모든 요소 삭제 |

---

## 📐 설계 및 구현 조건

### ✔ 1. 내부 배열 관리 (`MyArrayList`)
- 초기 용량(capacity)은 10으로 시작한다.
- 요소 추가 시 배열이 가득 차면 용량을 2배로 확장한다.
- 요소 삭제 시 배열을 축소하지는 않는다.
- 인덱스를 통한 접근은 O(1), 중간 삽입/삭제는 O(n)이 되어야 한다.

### ✔ 2. 연결 리스트 조건 (`MyLinkedList`) (선택)
- `Node` 내부 클래스를 생성하여 구현한다.
- `head`, `tail` 포인터를 유지한다.
- 중간 요소 접근(get)은 O(n) 시간 복잡도를 가진다.
- 삽입/삭제는 포인터 조작으로 구현한다.

### ✔ 3. 예외 처리
아래 상황에서는 `IndexOutOfBoundsException`을 발생시켜야 한다.
- 음수 인덱스 접근
- size 이상 인덱스 접근

### ✔ 4. 제네릭(Generic) 사용
- 리스트는 `T` 타입의 요소를 저장하도록 한다.
- 예: `MyList<String> list = new MyArrayList<>();`

---

## 🧪 테스트 요구사항

### 기본 동작 확인
- 요소 추가(add), 조회(get), 삭제(remove)가 정상 동작하는가?
- `size()`가 올바르게 증가/감소하는가?
- 빈 리스트에서 삭제 시 적절한 예외를 던지는가?
- 많은 양의 데이터를 넣어도 동작하는가? (예: 10,000개)

### 경계 조건
- 처음과 끝 인덱스 처리
- capacity 확장 여부
- 동일 값 여러 개 삭제 시 처리

---

## 📄 제출물
- `MyList.java`
- `MyArrayList.java`
- (선택) `MyLinkedList.java`
- 테스트 코드 or `Main.java` (동작 확인용)
- README.md (본 문서)

---

## ⭐ 추가 과제(선택)

### 선택 1. `iterator()` 구현
- `for-each` 문에서 동작 가능하도록 Iterable 구현

### 선택 2. `contains()`, `indexOf()`, `lastIndexOf()` 추가

### 선택 3. `MyLinkedList`에 양방향 리스트(Doubly Linked List)로 확장

