# Java Day 13 — 멀티스레딩과 동시성 복습

## 학습 결과
- 기초 퀴즈 Q1~Q10: **6/10**
- 심화 퀴즈 Q11~Q20: **9/10**
- 총점: **15/20 (75%)**
- 구현 과제 `BankAccountTest.java`: 필수 요구사항 충족. 완료 대기 결과 확인은 보완 권장.

## 1. 핵심 개념

### Thread와 Runnable
- `thread.run()`: 일반 메서드 호출. **현재 스레드**에서 실행.
- `thread.start()`: 새 스레드를 시작하고 그 스레드에서 `run()` 실행.
- 같은 `Thread` 인스턴스에 `start()`를 두 번 호출하면 **`IllegalThreadStateException`** (실행 예외).
- `Runnable`은 반환값이 없는 작업 정의. `Callable<V>`는 값을 반환하거나 검사 예외를 던질 수 있음.

### 스레드 상태
`Thread.State`: **NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED**.
- `RUNNING`은 Java의 `Thread.State`에 없음.
- 생성 직후: `NEW`.
- `RUNNABLE`은 실행 중이거나 실행 가능한 상태를 모두 포함.
- `BLOCKED`: 모니터 락 획득 대기.
- `WAITING`: 무기한 대기. `TIMED_WAITING`: 시간 제한 대기.

### sleep과 join
- `Thread.sleep(...)`: **호출한 현재 스레드**를 일시 대기시킴. 이미 획득한 모니터 락을 자동으로 해제하지 않음.
- `t.join()`: **현재 스레드가 t의 종료를 기다림**.
- `sleep()`은 정확한 실행 재개 시각을 보장하지 않음.

### Race condition / synchronized
`count++`는 읽기→계산→쓰기의 복합 연산이므로 원자적이지 않음.

```java
class Counter {
    private int count;
    public synchronized void increment() { count++; }
    public synchronized int getCount() { return count; }
}
```

- 인스턴스 `synchronized`는 **그 객체의 모니터 락**을 사용.
- `Counter a`와 `Counter b`는 같은 클래스라도 **서로 다른 락**을 사용.
- `static synchronized`는 클래스의 `Class` 객체 모니터를 사용.

### volatile vs synchronized vs AtomicInteger
| 방식 | 가시성 | 단일 변수 증가 연산의 원자성 | 특징 |
|---|---|---|---|
| `volatile int` | 보장 | `count++`는 보장 안 함 | 상태 플래그에 적합 |
| `synchronized` | 보장 | 동일 락으로 보호 시 보장 | 임계 영역 보호 |
| `AtomicInteger` | 보장 | `incrementAndGet()` 등 보장 | CAS 기반 원자 연산 |

```java
AtomicInteger a = new AtomicInteger(5);
System.out.println(a.getAndIncrement()); // 5: 증가 전 값
System.out.println(a.get());             // 6

AtomicInteger b = new AtomicInteger(5);
System.out.println(b.incrementAndGet()); // 6: 증가 후 값
System.out.println(b.get());             // 6
```

- `compareAndSet(expected, update)`는 현재 값이 `expected`와 같을 때만 원자적으로 갱신하고 `true` 반환.
- 원자적 변수 하나가 DB 트랜잭션이나 여러 변수에 걸친 불변식까지 보장하지는 않음.

### ExecutorService / Future
```java
ExecutorService pool = Executors.newFixedThreadPool(5);
try {
    Future<Integer> f = pool.submit(() -> 10 + 20);
    System.out.println(f.get()); // 30; 완료 전이면 대기
} finally {
    pool.shutdown();
}
```
- 작업 10개와 작업자 스레드 10개가 같은 뜻은 아님.
- `shutdown()`: 새 작업 제출 중단, 기존 작업은 계속 처리.
- `awaitTermination(timeout, unit)`: 시간 안에 종료되면 `true`, 시간 초과면 `false`.
- `Future.get()`: 완료까지 대기 가능. 작업 예외는 `ExecutionException`으로 전달될 수 있음.

## 2. 오답 집중 복습

| 문제 | 핵심 정답 | 실수 포인트 |
|---|---|---|
| Q1 | `t.run()` 직접 호출 시 `main` 출력 | `run()`과 `start()` 혼동 |
| Q2 | `start()` 두 번 → `IllegalThreadStateException` | 출력 대신 실행 예외 |
| Q5 | 생성 직후 상태 `NEW` | `WAITING`과 혼동 |
| Q9 | `RUNNING`은 `Thread.State`에 없음 | 상태는 총 6개 |
| Q13 | `incrementAndGet()` → `6`, 이후 `get()` → `6` | 증가 후 반환 vs 증가 전 반환 |

## 3. 제출 과제 코드 리뷰

사용자 제출 파일: `BankAccountTest.java` (`package Daou.Assignment.Day13`).

**잘한 점**
- `deposit(int amount)`의 `balance += amount`를 `synchronized`로 보호.
- `getBalance()` 역시 `synchronized` 사용.
- `Executors.newFixedThreadPool(10)`으로 스레드 풀 생성.
- `submit()`으로 10개의 1,000원 입금 작업 제출.
- `shutdown()` 이후 `awaitTermination(10, TimeUnit.SECONDS)` 호출.

**보완할 점**
- `completed`를 선언했지만 확인하지 않아, 제한 시간 초과 시에도 잔액을 출력할 수 있음.
- `submit()`으로 실행된 작업의 예외를 별도로 확인하지 않음. 이번 간단한 입금 작업은 예외 가능성이 낮지만, 일반적인 실무 코드에서는 `Future` 결과나 오류 로깅을 검토.
- `count` 반복 변수는 람다에서 사용하지 않으므로 effectively final 문제 없음.

### 개선 예제 (과제 핵심 구조)
```java
import java.util.concurrent.*;

class BankAccount {
    private int balance;
    public synchronized void deposit(int amount) { balance += amount; }
    public synchronized int getBalance() { return balance; }
}

public class BankAccountTest {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executor.submit(() -> account.deposit(1000));
        }
        executor.shutdown();
        boolean completed = executor.awaitTermination(10, TimeUnit.SECONDS);
        if (!completed) {
            System.out.println("시간 초과: 작업 미완료");
            return;
        }
        System.out.println("Final Balance: " + account.getBalance()); // 10000
    }
}
```

## 4. 스스로 확인할 문제
1. `run()`과 `start()`의 차이를 설명해 보자.
2. `Thread.State` 6개를 외워 보자.
3. `count++`에 `volatile`을 붙여도 값이 유실될 수 있는 이유는?
4. 같은 클래스의 서로 다른 인스턴스가 `synchronized` 메서드를 실행하면 서로를 막을까?
5. `getAndIncrement()`와 `incrementAndGet()`의 반환값 차이는?
6. `awaitTermination()`이 `false`를 반환할 때 무엇을 하면 좋을까?
7. 스레드 풀 크기 5로 작업 10개를 제출하면 어떻게 처리될까?

## 5. Day 14 예고
**종합 구현 과제:** 클래스 설계, 컬렉션/스트림, 예외 처리, 파일 I/O, 동시성 중 여러 개를 결합한 실전형 문제. 설계 → 구현 → 테스트 → 리뷰 순서로 진행.
