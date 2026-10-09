# Java Day 10 복습 — Stream API & Optional 심화

> 대상: Java 17+, 다우기술 Java 구현형 과제 대비. 본 문서는 Day 10 강의 및 제출한 `MemberAnalyticsService.java`를 기반으로 정리했다. 동작 원리 설명과 추가 주의사항은 별도 보충 설명이다.

## 1. 오늘의 핵심

Stream 파이프라인은 **Source → 중간 연산 → 최종 연산**으로 구성된다.

```java
List<Integer> result = List.of(5, 2, 8, 1, 4).stream()
    .filter(n -> n % 2 == 0) // Stream<Integer>
    .map(n -> n * 2)         // Stream<Integer>
    .sorted()                // Stream<Integer>
    .toList();               // List<Integer>: [4, 8, 16]
```

- `filter(Predicate<T>)`: 조건에 맞는 원소만 남김. 타입 유지.
- `map(Function<T,R>)`: 원소를 다른 값으로 변환. `Stream<T>` → `Stream<R>`.
- `flatMap(Function<T,Stream<R>>)`: 원소별로 만들어진 Stream을 하나로 연결.
- `distinct()`: 중복 제거. `equals`/`hashCode` 계약이 중요.
- `sorted(Comparator)`: 지정된 비교 기준으로 정렬. 원본 컬렉션은 그대로.
- `limit(n)`: 앞의 n개만 유지.
- `toList()`: 수정 불가능한 List 반환(Java 16+).
- `count()`: 원소 수를 `long`으로 반환.
- `findFirst()`: `Optional<T>` 반환.
- `anyMatch()`: `boolean` 반환.

## 2. Stream에서 타입 추적하기 — 가장 중요한 습관

```java
members.stream()                   // Stream<Member>
       .map(Member::getScore)       // Stream<Integer>
       .reduce(0, Integer::sum);    // int
```

`Integer::sum`은 `Member`를 더할 수 없다. `map()`으로 먼저 점수(`Integer`)를 꺼내야 한다. 기본형 특화 Stream을 쓰면 `members.stream().mapToInt(Member::getScore).sum()`도 가능하다.

`sorted(Comparator.comparingInt(Member::getScore))`는 정렬 기준으로 점수를 **참조**할 뿐, Stream 원소 자체를 점수로 변환하지 않는다. 반환 타입은 계속 `Stream<Member>`다.

## 3. map vs flatMap

```java
List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));
List<Integer> flat = nested.stream().flatMap(List::stream).toList();
// [1, 2, 3, 4]
```

`map(List::stream)`을 쓰면 `Stream<Stream<Integer>>`가 된다. `flatMap(List::stream)`은 내부 Stream들을 이어 `Stream<Integer>`로 만든다.

## 4. collect와 그룹별 집계

```java
Map<String, Long> counts = members.stream().collect(
    Collectors.groupingBy(Member::getDepartment, Collectors.counting()));

Map<String, Double> averages = members.stream().collect(
    Collectors.groupingBy(Member::getDepartment,
                          Collectors.averagingInt(Member::getScore)));

Map<Boolean, List<Member>> partitions = members.stream().collect(
    Collectors.partitioningBy(m -> m.getScore() >= 90));
```

- `groupingBy(분류함수, downstream)`: Key는 분류 결과, Value는 downstream 결과.
- `counting()` → `Long` (`List<Member>`가 아님).
- `averagingInt()` → `Double`.
- `partitioningBy()` → `Boolean` Key의 두 그룹.
- 기본 `groupingBy()`의 Map 순회 순서에는 의존하지 말 것.

## 5. reduce — 누적과 타입

```java
int sum = List.of(1, 2, 3, 4).stream().reduce(10, Integer::sum);
// 20 = 초기값 10 + 1 + 2 + 3 + 4
```

`reduce(identity, accumulator)`에서 초기값은 누적 연산의 항등원이어야 병렬 처리에서도 올바른 의미를 갖는다. 합계는 0, 곱셈은 1을 사용한다. 병렬 처리에서는 누적 연산의 결합법칙도 중요하다.

## 6. Optional — 예외와 평가 시점

```java
Optional<String> value = Optional.of("Java");
value.orElse(expensiveDefault());            // 인자 계산이 먼저 실행됨
value.orElseGet(() -> expensiveDefault());   // 값이 없을 때만 실행
```

- `Optional.empty().get()` → **NoSuchElementException** (NPE 아님).
- `Optional` 변수 자체가 `null`이면 메서드 호출 시 **NullPointerException**.
- `Optional.map()`은 `Optional<R>` 반환. `Optional.flatMap()`은 Optional 중첩 방지.
- `System.out.println(Optional.of(4))` 출력은 `Optional[4]`, `4`가 아니다.
- `orElseThrow()`는 값이 없으면 예외 발생.

## 7. 지연 평가, 단락 평가, 최적화

```java
List<Integer> nums = List.of(1, 2, 3, 4, 5);
nums.stream().peek(System.out::println).anyMatch(n -> n >= 3);
// 순차 실행에서는 1, 2, 3 출력 후 중단
```

중간 연산은 보통 최종 연산을 만나야 평가된다. `anyMatch()`는 답이 정해지면 조기 종료할 수 있다. 일부 `count()`는 크기 정보를 이용해 `map()` 자체를 실행하지 않을 수 있다. 따라서 `map()`/`peek()`의 출력·상태 변경에 의존하지 말 것. Stream은 최종 연산으로 소비한 뒤 재사용할 수 없다(`IllegalStateException`). 병렬 Stream에서 외부 `ArrayList`에 `forEach(add)`하는 코드는 스레드 안전하지 않다.

## 8. 제출 과제 리뷰 — Member Analytics Service

### 문제 요약

회원 데이터에서 성인 필터링, 점수 상위 3명, 부서별 평균/인원, ID 검색, 이름 추출, 점수 총합을 Stream API로 구현한다. 원본 리스트는 변경하지 않는다.

### 제출 코드의 최종 구현 요점

| 기능 | 사용 패턴 | 결과 |
|---|---|---|
| 성인 조회 | `filter(m -> m.getAge() >= 20).toList()` | 4명 |
| 점수 상위 3명 | `sorted(comparingInt(...).reversed()).limit(3)` | Jung, Park, Kim |
| 부서별 평균 | `groupingBy(..., averagingInt(...))` | IT 95.0 / HR 82.5 |
| 부서별 인원 | `groupingBy(..., counting())` | IT 3 / HR 2 |
| ID 조회 | `filter(...).findFirst()` | 3L → Park, 99L → empty |
| 이름 목록 | `map(Member::getName).toList()` | Kim, Lee, Park, Choi, Jung |
| 점수 합계 | `map(Member::getScore).reduce(0, Integer::sum)` | 450 |

### 수정 전 오류와 원인

1. `sorted(Collections.reverseOrder())`: `Member`가 `Comparable`을 구현하지 않았고 점수 기준도 지정하지 않았다. `Comparator.comparingInt(Member::getScore).reversed()`를 사용.
2. `anyMatch(id)`: `anyMatch()`는 `Predicate<Member>`가 필요하며 반환값도 `boolean`. 객체 검색에는 `filter(...).findFirst()`.
3. `Stream<Member>.reduce(0, Integer::sum)`: 누적하려는 원소가 `Member`이므로 타입 불일치. `map(Member::getScore)`가 먼저 필요.
4. `averagingDouble(Member::getScore)`는 가능한 코드이지만 `int` getter에는 `averagingInt`가 더 적절.

### 추가 주의

제출 코드의 `findMemberById(List<Member>, Long id)`는 `member.getId() == id`에서 `id`가 `null`이면 언박싱 중 NPE가 발생할 수 있다. 필수 인자라면 `Objects.requireNonNull(id)`로 계약을 명확히 하자.

## 9. Day 10 오답 복습

- Q5: `filter`에서 탈락한 원소도 조건 검사 자체는 수행한다. `F1 F2 M2 F3 M3`.
- Q6: `anyMatch` 조기 종료 이후에도 별도의 `println(result)`는 실행된다. `1 2 3 true`.
- Q9: `map → filter → count()` 결과는 값 목록이 아니라 **개수 2**.
- Q14: 값이 있는 `Optional.orElseGet()`은 Supplier를 실행하지 않음. **Java**만 출력.
- Q17: `groupingBy(String::length, counting())`에서 `get(2)`는 문자열 리스트가 아니라 **2L**.
- Q18: `Optional.empty().get()`은 **NoSuchElementException**.
- Q19: `Optional.map(...).map(...)`의 결과를 그대로 출력하면 **Optional[4]**.
- Q20: 옳은 선택지는 **A, C, E, F**. `counting()` 결과는 Long.

## 10. 직접 풀어볼 추가 문제

1. `List.of(1,2,3,4,5).stream().filter(n -> n > 2).map(n -> n * 3).count()`의 결과는?
2. `Stream.of("A","BB","CCC").collect(groupingBy(String::length, counting()))`의 Value 타입은?
3. `Optional.of("X").orElseGet(() -> { System.out.println("Y"); return "Z"; })`는 무엇을 출력하는가? (반환값을 별도로 출력하지 않음)
4. `List.of(2,3,4).stream().reduce(1, (a,b) -> a*b)` 결과는?
5. `Stream.of(1,2,3).toList().add(4)`는 컴파일 오류인가 실행 오류인가?

### 정답

1. `3` (`3,4,5` 세 개). 2. `Long`. 3. 아무것도 출력하지 않음. 4. `24`. 5. 실행 시 `UnsupportedOperationException`.

## 11. 실행

```bash
javac Day10Review.java
java Day10Review
```

`Day10Review.java`는 원본 제출 파일과 별도로 실행 가능한 복습용 독립 예제다. 제출 파일은 일반 `Member` 클래스와 getter를 사용하고, 복습 예제는 같은 동작을 간결하게 보여주기 위해 `record Member`를 사용한다.
