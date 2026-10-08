# Java Day 8 — Collections Framework & Generics 복습

## 1. 학습 범위
- `List`, `Set`, `Map`의 용도와 구현체 (`ArrayList`, `LinkedList`, `HashSet`, `LinkedHashSet`, `HashMap`)
- `getOrDefault`, `putIfAbsent`, `computeIfAbsent`, `keySet`, `values`, `entrySet`
- HashMap의 Key 비교: `hashCode()`로 bucket을 찾고 `equals()`로 Key의 동등성을 확인
- 제네릭의 타입 안정성, 타입 소거, 불공변성, `? extends`, `? super`
- `Optional.of`, `Optional.ofNullable`, `Optional.empty`
- `Map.values()` 뷰, `new ArrayList<>(values)` 복사, 얕은 복사

## 2. 자료구조 비교
| 구조 | 특징 | 대표 사용처 |
|---|---|---|
| `ArrayList<E>` | 순서/중복 허용, 인덱스 조회 O(1), 중간 삽입·삭제 O(n) | 일반 목록 |
| `LinkedList<E>` | 이중 연결 리스트, 인덱스 조회 O(n) | 노드 기반 양끝 조작 |
| `HashSet<E>` | 중복 제거, 순서 미보장 | 유일한 ID 모음 |
| `LinkedHashSet<E>` | 중복 제거 + 삽입 순서 유지 | 순서 있는 중복 제거 |
| `HashMap<K,V>` | Key 중복 불가, 평균 조회 O(1), 순서 미보장 | ID로 객체 조회 |

`Map`은 `Collection`의 하위 인터페이스가 아니다. `HashMap`의 동등성 검사는 **Key**에 적용한다. `Map<Long, Member>`에서는 `Long`의 `equals/hashCode`가 조회에 쓰이며 `Member`의 구현은 조회에 필요하지 않다. `Set<Member>`에서는 `Member`의 `equals/hashCode`가 중요하다.

## 3. 자주 쓰는 Map API
```java
Map<String, Integer> count = new HashMap<>();
count.put("A", count.getOrDefault("A", 0) + 1);
count.putIfAbsent("A", 100); // 기존 값이 있으면 유지

Map<String, List<Integer>> grouped = new HashMap<>();
grouped.computeIfAbsent("A", k -> new ArrayList<>()).add(10);
grouped.computeIfAbsent("A", k -> new ArrayList<>()).add(20);
// A -> [10, 20]; 두 번째 호출에서는 생성 람다 실행 안 함
```
`values()`는 값의 Collection 뷰, `keySet()`은 Key의 Set 뷰, `entrySet()`은 Key-Value 엔트리 뷰다.

## 4. Hash Collision과 mutable key
해시값이 같아도 `equals()`가 false라면 다른 Key다. 충돌은 같은 bucket의 엔트리를 비교해서 처리한다. Key의 `equals/hashCode`에 사용한 필드를 Map에 넣은 뒤 변경하면 조회가 실패할 수 있다. 가능하면 불변 Key를 사용한다.

## 5. Generics와 타입 소거
```java
class Box<T> {
    private T value;
    void set(T value) { this.value = value; }
    T get() { return value; }
}
Box<String> b = new Box<>();
b.set("Java");
// b.set(10); // Compile Error
```
`List<Dog>`는 `List<Animal>`의 하위 타입이 아니다(**불공변**). 제네릭 타입 인자는 일반적으로 런타임에 소거되므로 `List<String>`과 `List<Integer>`는 같은 런타임 `ArrayList` 클래스를 사용한다.

| 타입 | 안전하게 읽기 | 안전하게 추가 |
|---|---|---|
| `List<? extends Animal>` | `Animal` | 일반 객체 추가 불가 (`null`은 가능) |
| `List<? super Dog>` | `Object` | `Dog` 및 하위 타입 |

**PECS**: Producer Extends, Consumer Super. `? extends Animal`은 `List<Cat>`일 수도 있으므로 `new Dog()`를 추가할 수 없다. 이것이 Day 8 문제 17의 오답 포인트였다.

## 6. Optional: 실제 제출 코드의 오류
```java
// Optional.of(null); // NullPointerException
Optional<Member> findById(long id) {
    return Optional.ofNullable(members.get(id));
}
```
- `of(x)`: x가 null이면 NPE
- `ofNullable(x)`: null이면 빈 Optional
- `empty()`: 빈 Optional
- 과제에서 반환 타입을 `Member`로 요구했다면 `return members.get(id);`가 요구사항에 부합한다. Optional을 쓰려면 호출 측도 Optional을 처리해야 한다.

## 7. View vs 복사 vs 얕은 복사 — 마지막 혼동 지점
```java
Map<Integer, StringBuilder> map = new HashMap<>();
map.put(1, new StringBuilder("A"));
Collection<StringBuilder> view = map.values();
List<StringBuilder> copy = new ArrayList<>(map.values());
map.put(2, new StringBuilder("B"));
copy.get(0).append("C");
System.out.println(view.size()); // 2
System.out.println(copy.size()); // 1
System.out.println(map.get(1));  // AC
```
- **뷰**: Map에 연결되어 있어 Map 엔트리 추가/삭제가 뷰에도 반영된다.
- **새 ArrayList**: 컬렉션 저장 공간을 새로 만들고 당시 원소의 참조를 복사한다. Map에 새 원소를 추가해도 List 크기는 바뀌지 않는다.
- **얕은 복사**: `StringBuilder` 객체 자체는 복제되지 않는다. 두 컬렉션이 같은 객체를 참조하므로 `append("C")`가 원본에서도 보인다.
- `StringBuilder`는 변경 가능 객체이고 `String`은 불변 객체다. 원소가 불변 객체라면 내부 상태 변경에 따른 영향이 없다.
- 깊은 복사를 원하면 원소 객체와 필요한 중첩 객체까지 별도로 복제해야 한다.

## 8. 제출한 MemberRepository 피드백
잘한 점: `Member`의 불변 필드, `equals(Object)`의 null/타입 검사, ID 기준 `hashCode`, 중복 ID 검사, `findAll`의 별도 리스트 반환.

개선점:
1. `Optional.of(null)` → `Optional.empty()` 또는 `Optional.ofNullable(members.get(id))`.
2. `findAll()`의 Key 순회 + `get` 반복 → `new ArrayList<>(members.values())`.
3. 반환 타입은 문제의 API 요구사항에 맞추기 (`Member` 또는 `Optional<Member>`).
4. `Map<Long, Member>`에서 중복 Key는 `Long` 기준; Member 동등성과 구분하기.

## 9. 복습 문제
1. `new ArrayList<>(map.values())` 생성 후 Map에 값을 추가하면 List 크기가 변하는가?
2. 위 List의 `Member`를 수정하면 Map의 Member에도 반영될 수 있는가?
3. `Optional.of(null)`과 `Optional.ofNullable(null)`의 차이는?
4. `List<? extends Animal>`에 `new Dog()`를 추가할 수 없는 이유는?
5. `Map<Long, Member>`의 Key 검색에 어떤 타입의 `hashCode()`가 사용되는가?
6. `HashSet<Member>`에서 ID가 같은 두 객체를 하나로 취급하려면 무엇이 필요한가?
7. `HashMap.put("A", 1); put("A", 2);` 이후 크기와 값은?
8. `computeIfAbsent`의 Key에 이미 null이 아닌 값이 있다면 계산 함수가 실행되는가?

### 정답
1. 아니오. 2. 예, 얕은 복사이므로 같은 변경 가능 객체를 공유할 수 있다. 3. 전자는 NPE, 후자는 빈 Optional. 4. 실제 리스트가 `List<Cat>`일 수 있다. 5. `Long`. 6. ID 기준의 일관된 `equals/hashCode`. 7. 크기 1, 값 2. 8. 아니오.

## 10. 실행 방법
```bash
javac Day8Review.java
java Day8Review
```
