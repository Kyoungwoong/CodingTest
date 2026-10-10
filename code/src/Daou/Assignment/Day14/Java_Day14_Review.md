# Java Day 14 — JUnit 5 복습 노트

## 학습 결과
- 기초 Q1–Q10: **9/10** (오답 Q10: `@AfterEach` 실행 시점)
- 심화 Q11–Q20: **10/10**
- 총 **19/20 (95%)**
- 제출 과제: `BankAccountJUnitTest`의 다섯 가지 테스트 메서드 구현 완료. 단, 테스트 대상 `BankAccount.java` 및 JUnit 실행 환경이 함께 제공되지 않아 실제 실행 성공 여부는 확인하지 못함.

## 1. JUnit 5 구성
- **JUnit Platform**: 테스트 실행 플랫폼
- **JUnit Jupiter**: `@Test`, Assertions, 테스트 생명주기 및 실행 엔진
- **JUnit Vintage**: 이전 JUnit 3/4 테스트 실행 지원
- 일반적으로 애플리케이션 소스는 `src/main/java`, 테스트는 `src/test/java`에 배치한다.

## 2. 핵심 어노테이션
| 어노테이션 | 의미 |
|---|---|
| `@Test` | 일반 테스트 |
| `@BeforeEach` | 각 테스트 실행 전 |
| `@AfterEach` | 각 테스트 실행 후 |
| `@BeforeAll` | 해당 테스트 클래스 실행 전 한 번 |
| `@AfterAll` | 해당 테스트 클래스 실행 후 한 번 |
| `@ParameterizedTest` | 여러 인자로 반복 실행 |
| `@ValueSource` | 하나의 인자에 여러 값 공급 |
| `@CsvSource` | 여러 인자를 CSV 형태로 공급 |

**오답 복습:** `@AfterEach`는 테스트 **후**에 실행한다. 기본 `PER_METHOD` 생명주기에서는 `@BeforeAll`과 `@AfterAll`을 `static`으로 작성한다.

## 3. Assertion 메서드
```java
assertEquals(expected, actual); // 값의 동등성
assertNotEquals(a, b);
assertTrue(condition);
assertFalse(condition);
assertNull(value);
assertNotNull(value);
assertSame(expected, actual);   // 같은 객체 참조
assertArrayEquals(expectedArray, actualArray);
```
- `assertEquals(new String("Java"), new String("Java"))`는 성공.
- `assertSame(new String("Java"), new String("Java"))`는 실패.
- `assertAll(() -> assertEquals(...), () -> assertTrue(...))`은 내부 검증을 모두 실행하고 실패를 모아 보고한다.

## 4. 예외 테스트
```java
IllegalArgumentException ex = assertThrows(
    IllegalArgumentException.class,
    () -> account.deposit(-1000)
);
assertEquals("입금액은 0보다 커야 합니다.", ex.getMessage());
assertEquals(0, account.getBalance());
```
- `assertThrows`는 지정 예외 또는 하위 예외가 발생하면 통과하며, 예외 객체를 반환한다.
- `assertThrowsExactly`는 예외 클래스가 정확히 일치해야 한다.
- 위 메시지 검증은 실제 구현의 예외 메시지가 동일할 때만 성립한다.

## 5. Parameterized Test
```java
@ParameterizedTest
@ValueSource(ints = {100, 500, 1000})
void depositTest(int amount) {
    account.deposit(amount);
    assertEquals(amount, account.getBalance());
}

@ParameterizedTest
@CsvSource({"1,2,3", "4,5,9"})
void addTest(int a, int b, int expected) {
    assertEquals(expected, a + b);
}
```
- `@ValueSource`는 한 실행당 인자 하나를 전달한다.
- `@CsvSource`는 여러 인자를 전달할 수 있다.
- 각 인자 조합은 독립적인 테스트 실행으로 취급된다.

## 6. 테스트 설계: AAA, 격리, 경계값
- **Arrange**: 객체와 입력 데이터 준비
- **Act**: 테스트 대상 실행
- **Assert**: 기대값 검증
- `@BeforeEach`에서 새 계좌를 만들어 테스트 간 공유 상태를 없앤다.
- 경계값 테스트: 허용 범위가 1~1,000,000이라면 `0`, `1`, `1_000_000`, `1_000_001`을 우선 확인한다.
- 테스트는 실행 순서나 이전 테스트 결과에 의존하지 않도록 작성한다.

## 7. Mockito 개념
```java
AccountRepository repo = Mockito.mock(AccountRepository.class);
Mockito.when(repo.findBalance(1L)).thenReturn(10000);
assertEquals(10000, new AccountService(repo).getBalance(1L));
Mockito.verify(repo).findBalance(1L);
```
- Mock은 외부 의존성을 대체해 서비스 로직을 격리한다.
- `when(...).thenReturn(...)`: 반환값 설정; `verify(...)`: 호출 여부 검증.
- 외부 의존성이 없는 단순 계산 로직은 Mockito 없이 실제 객체로 테스트하는 편이 낫다.

## 8. 제출 과제 리뷰 — BankAccountJUnitTest
제출 코드에서 확인한 사항:
1. `@BeforeEach`로 매번 `new BankAccount()` 생성.
2. 단일 입금 및 누적 입금 결과를 `assertEquals`로 검증.
3. 음수 입금에서 `assertThrows(IllegalArgumentException.class, ...)` 사용.
4. `@ValueSource(ints={100,500,1000})`로 세 입력값 검증.
5. `newFixedThreadPool(5)`에 입금 작업 10개 제출, 각 `Future.get(5, TimeUnit.SECONDS)`로 완료 및 예외 확인 후 잔액 10,000 검증.
6. `finally`에서 `shutdownNow()` 호출.

### 개선 포인트
- `invalidDepositTest()`의 `ex` 변수는 저장 후 사용하지 않았다. 메시지와 잔액 불변성도 검증해 보자.
- `Future.get()`으로 작업 완료를 확인한 점이 좋다. 다만 동시에 시작한다는 보장은 없으므로 엄격한 경쟁 상태 테스트에는 `CountDownLatch` 등의 시작 신호가 유용하다.
- 파일명은 `BankAccountJUnitTest.java`로 맞추는 것을 권장한다. 제출 파일명은 `BankAccountTest(1).java`였고 클래스는 package-private이므로 Java 문법상 파일명 일치는 필수가 아니다.
- 실제 테스트 실행에는 `BankAccount.java`와 JUnit 5 의존성/테스트 실행 환경이 필요하다.

## 9. 동시성 테스트 예제
```java
@Test
void concurrentDepositTest() throws Exception {
    ExecutorService executor = Executors.newFixedThreadPool(5);
    List<Future<?>> futures = new ArrayList<>();
    try {
        for (int i = 0; i < 10; i++) {
            futures.add(executor.submit(() -> account.deposit(1000)));
        }
        for (Future<?> future : futures) {
            future.get(5, TimeUnit.SECONDS);
        }
        assertEquals(10000, account.getBalance());
    } finally {
        executor.shutdownNow();
    }
}
```

## 10. 복습 체크리스트
- [ ] `@BeforeEach`와 `@AfterEach` 실행 시점 설명하기
- [ ] `assertEquals`와 `assertSame` 차이 설명하기
- [ ] `assertThrows`로 예외 타입과 메시지 검증하기
- [ ] `@ValueSource`와 `@CsvSource` 차이 설명하기
- [ ] 테스트 격리를 위해 객체 초기화하기
- [ ] 정상값, 경계값, 예외값 테스트 설계하기
- [ ] Mockito로 외부 의존성 대체하기
- [ ] `Future.get()`으로 병렬 작업 실패까지 감지하기

**다음 학습: Day 15 — Java 종합 실기 모의고사**
