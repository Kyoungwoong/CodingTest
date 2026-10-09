# Java 실기 대비 — Day 11 복습 노트

**주제:** String / StringBuilder / Arrays / java.time  
**진도:** Day 11 완료 (Day 12: I/O)  
**퀴즈:** Q1~10 7/10, Q11~20 9/10 → **16/20 (80%)**  
**구현 과제:** `EmployeeAnalyzer`의 7개 메서드 검토 및 수정본 완성

## 1. String: 불변성과 String Pool

`String` 객체의 문자열 내용은 생성 후 변경할 수 없다. `concat`, `replace`, `toUpperCase` 등은 결과 문자열을 반환하므로 **반환값을 사용해야 한다**.

```java
String s = "Hello";
s.concat(" World");
System.out.println(s); // Hello (Q2 오답)
s = s.concat(" World");
System.out.println(s); // Hello World
```

- `==`: 두 참조가 같은 객체를 가리키는지 비교.
- `String.equals()`: 문자열 내용 비교.
- 리터럴 `"Java"`는 String Pool을 재사용하며, `new String("Java")`는 별도 객체를 생성한다.
- `intern()`은 Pool의 정규화된 문자열 참조를 반환한다. 원래 변수를 자동으로 바꾸지는 않는다.
- 컴파일타임 상수 표현식 `"Hello" + "Java"`는 상수로 접힐 수 있다. `final String x = "Hello"` 역시 컴파일타임 상수 변수지만, `final String x = new String("Hello")`는 아니다.
- 불변성은 안전한 공유, 키 안정성, 문자열 풀 재사용 등에 유리하다.

```java
String a = "Java", b = "Java", c = new String("Java");
System.out.println(a == b);      // true
System.out.println(a == c);      // false
System.out.println(a.equals(c)); // true
System.out.println(c.intern() == a); // true
```

**실무 팁:** nullable 문자열은 `"ADMIN".equals(role)` 형태로 비교하면 `role == null`에서도 안전하다.

## 2. StringBuilder / StringBuffer

`StringBuilder`는 가변 버퍼에 문자를 누적한다. 반복 결합에서 중간 문자열 생성 비용을 줄일 수 있다. `append`, `insert`, `delete`, `reverse`, `toString`을 기억한다. `StringBuffer`는 주요 메서드가 동기화되지만 **여러 메서드를 묶은 복합 작업 전체의 원자성을 자동 보장하지 않는다** (Q10 F 오답).

```java
StringBuilder a = new StringBuilder("Java");
StringBuilder b = new StringBuilder("Java");
System.out.println(a.equals(b)); // false (Q9 오답)
System.out.println(a.toString().equals(b.toString())); // true
System.out.println(a.compareTo(b) == 0); // true (Java 11+)
```

`StringBuilder`는 `Object.equals()`를 오버라이드하지 않으므로 내용 비교를 기대하면 안 된다.

## 3. 문자열 처리와 정규표현식

- `substring(begin, end)`: 시작 포함, 끝 제외. `"ABCDE".substring(1,4)` → `"BCD"`.
- `split(regex)`: **정규표현식**을 인자로 받는다. 점 문자를 구분자로 쓰려면 `split("\\\\.")`라고 **Java 코드에서** 적는다(아래 코드 참고).
- `split(",")`는 뒤쪽 빈 토큰을 버리고, `split(",", -1)`은 유지한다.
- `length()`는 문자열 메서드, 배열은 `length` 필드.

```java
System.out.println(Arrays.toString("A.B.C".split("\\."))); // [A, B, C]
System.out.println(Arrays.toString("A,B,".split(",", -1))); // [A, B, ]
```

## 4. 배열의 참조, 복사, 얕은 복사

배열은 객체다. `int[] b = a`는 **같은 배열 참조**를 공유한다. `a.clone()`, `Arrays.copyOf(a, n)`은 **새 배열**을 만든다. 단, 객체 배열은 원소 객체의 참조를 복사하므로 얕은 복사다.

```java
int[] a = {1,2,3};
int[] b = a.clone();
b[0] = 100;
System.out.println(a[0]); // 1 (Q12 오답)

StringBuilder[] x = {new StringBuilder("A")};
StringBuilder[] y = x.clone();
y[0].append("B");
System.out.println(x[0]); // AB
```

- `Arrays.copyOfRange(a, 1, 4)`: 인덱스 1~3만 복사.
- `Arrays.equals(int[][], int[][])`: 안쪽 배열 객체의 참조를 비교하므로 내용이 같아도 false일 수 있다.
- `Arrays.deepEquals`: 중첩 배열의 내용까지 비교.
- `Arrays.sort`: 원본 배열을 정렬한다. 원본 보존이 필요하면 먼저 복사한다.
- `Arrays.binarySearch`: 정렬된 배열이 전제. 없으면 `-(삽입위치)-1` 반환. `{1,3,5,7}`에서 4 검색 → `-3`.

## 5. 날짜·시간 API

| 타입 | 의미 | 핵심 |
|---|---|---|
| `LocalDate` | 날짜 | 시간대 정보 없음, 불변 |
| `LocalTime` | 시각(하루 중 시간) | 시간대 정보 없음 |
| `LocalDateTime` | 날짜와 시각 | 시간대 정보 없음 |
| `ZonedDateTime` | 시간대 포함 날짜·시각 | 지역별 시간대 규칙 적용 |
| `Instant` | 타임라인의 한 시점 | 시점 비교/기록 |
| `Period` | 연·월·일 기반 차이 | `Period.between` |
| `Duration` | 시간 기반 차이 | `Duration.between` |

```java
LocalDate join = LocalDate.of(2020, 3, 15);
LocalDate base = LocalDate.of(2026, 10, 9);
long totalDays = ChronoUnit.DAYS.between(join, base);
Period p = Period.between(join, base);
System.out.println(totalDays);
System.out.println(p.getYears() + "년 " + p.getMonths() + "개월 " + p.getDays() + "일");

LocalDate date = LocalDate.of(2026,10,9);
date.plusDays(10);
System.out.println(date); // 2026-10-09: 불변
System.out.println(date.plusDays(10)); // 2026-10-19
```

`Period.getDays()`는 **전체 일수**가 아니라 연·월을 제외한 **나머지 일수**다. 전체 일수는 `ChronoUnit.DAYS.between`을 사용한다.

### 날짜 포맷팅

```java
DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
System.out.println(LocalDate.of(2020,3,15).format(f)); // 2020년 03월 15일
```

- `getDayOfMonth()` = 월의 몇 번째 날 (15)
- `getDayOfYear()` = 연도의 몇 번째 날 (2020-03-15는 75) → 구현 과제 오답
- `yyyy`는 달력 연도(era 기반), `YYYY`는 주 기반 연도. 일반적인 날짜 서식에서 `YYYY`를 쓰지 않는다. 엄격한 ISO 파싱 등에서는 `uuuu`도 중요하다.

## 6. Day 11 퀴즈 오답 집중 정리

| 문항 | 잘못 생각한 부분 | 기억할 규칙 |
|---|---|---|
| Q2 | `concat()`이 원본을 수정 | String 메서드 결과를 재할당해야 변경된 참조를 사용 |
| Q9 | StringBuilder의 `equals()`가 내용 비교 | `toString().equals()` 또는 `compareTo() == 0` |
| Q10 | B 누락, F 선택 | Builder는 가변; Buffer의 메서드 동기화 ≠ 복합 작업 원자성 |
| Q12 | `clone()` 후 원본도 변경 | 기본형 배열 clone은 새로운 배열에 값 복사 |

## 7. 실기 구현 과제: EmployeeAnalyzer

**문제 요약:** `id,name,department,joinDate` 형태의 CSV를 직원 객체로 파싱하고, 근속일수·근속기간, 입사일 정렬, 부서별 인원, 날짜 서식, 배열 복사를 구현한다. 기준일은 `2026-10-09`.

**제출본 평가:** 7개 메서드 중 5개 정상, 2개 수정 필요, `main()` 테스트 미작성. 수정 완료본: `EmployeeAnalyzer_Corrected.java`.

### 잘 구현한 부분

```java
return ChronoUnit.DAYS.between(employee.getJoinDate(), today);
return Period.between(employee.getJoinDate(), today);

Employee[] result = employees.clone();
Arrays.sort(result, Comparator.comparing(Employee::getJoinDate));
return result;
```

원본 배열을 복사한 뒤 정렬해 원본을 보존하는 점이 중요하다.

### 수정 1 — Stream 없이 부서별 인원 집계

기존 제출본은 `Collectors.groupingBy(..., count)`에서 `count`가 정의되지 않았고 반환문도 없었으며, Stream 미사용 조건을 어겼다.

```java
public static Map<String, Integer> countByDepartment(Employee[] employees) {
    Map<String, Integer> result = new HashMap<>();
    for (Employee employee : employees) {
        result.merge(employee.getDepartment(), 1, Integer::sum);
    }
    return result;
}
```

`merge(key, 1, Integer::sum)`은 키가 없으면 1, 있으면 기존 값 + 1을 저장한다. `put(key, getOrDefault(key, 0) + 1)`과 같은 목적이다.

### 수정 2 — 날짜 서식

제출본의 `getDayOfYear()`는 일자 대신 연중 순번을 반환하며 `%d`는 앞자리 0을 채우지 않는다.

```java
public static String formatJoinDate(Employee employee) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    return employee.getJoinDate().format(formatter);
}
```

### 실무 수준으로 확장할 때

과제 요구사항을 넘어서는 추가 고려사항: CSV 컬럼 수와 공백 검사, 잘못된 숫자/날짜의 예외 처리, null 인자 정책, 미래 입사일 처리, 동률 정렬 시 보조 키, 날짜 포맷터의 상수 재사용, 테스트 코드의 자동 검증(assertion). 이 항목들은 원래 과제 필수 요건은 아니었다.

## 8. 복습용 추가 문제 (정답은 아래)

1. `String s="A"; s.replace("A","B");` 뒤 `s`는?
2. `new StringBuilder("x").equals(new StringBuilder("x"))`는?
3. `int[] a={3,4}; int[] b=a.clone(); b[0]=8;` 뒤 `a[0]`는?
4. `Arrays.binarySearch(new int[]{2,4,6}, 5)`는?
5. `LocalDate.of(2026,10,9).plusDays(1)`을 호출만 한 후 원래 변수 값은?
6. `LocalDate.of(2020,3,15).getDayOfYear()`는?
7. `"A,B,".split(",", -1)`의 배열 길이는?
8. `Period.between(2026-01-01, 2026-02-10).getDays()`는?

<details>
<summary>정답 보기</summary>

1. `"A"` (불변, 반환값 미사용)
2. `false` (참조 비교)
3. `3` (기본형 배열 복사)
4. `-3` (삽입 위치 2)
5. `2026-10-09` (불변)
6. `75` (2020년은 윤년)
7. `3` (마지막 빈 문자열 유지)
8. `9` (1개월 9일 중 일수 부분)

</details>

## 9. Day 11 최종 체크리스트

- [ ] `==`와 `equals()` 차이를 설명할 수 있다.
- [ ] String Pool 및 컴파일타임 상수 결합을 구분할 수 있다.
- [ ] StringBuilder의 가변성과 `equals()` 동작을 이해한다.
- [ ] 정규식 기반 `split()`과 마지막 빈 토큰을 처리할 수 있다.
- [ ] 배열 참조 복사, 기본형 배열 clone, 객체 배열 얕은 복사를 구분한다.
- [ ] `Arrays.sort`, `binarySearch`, `deepEquals`를 올바르게 사용한다.
- [ ] `Period`와 `ChronoUnit.DAYS`의 차이를 안다.
- [ ] `getDayOfMonth`와 `getDayOfYear`를 구분한다.
- [ ] CSV 파싱·정렬·부서 집계·날짜 서식을 직접 구현할 수 있다.

**다음 진도: Day 12 — Java I/O (File, Stream, Reader/Writer, try-with-resources).**
