# Java Day 6 Review — Exception

## 1. 예외 계층

```text
Throwable
├── Error
└── Exception
    ├── IOException                 ← Checked
    ├── SQLException                ← Checked
    └── RuntimeException            ← Unchecked
        ├── IllegalArgumentException
        ├── NullPointerException
        ├── ArithmeticException
        └── ClassCastException
```

Exception도 객체다.

```java
new IllegalArgumentException("Error");       // 객체 생성
throw new IllegalArgumentException("Error"); // 실제 예외 발생
```

## 2. Checked vs Unchecked — 오늘 틀렸던 핵심

처음 문제에서 `Exception`, `IOException`을 Unchecked로 골랐지만 반대다.

**Checked Exception**
- `Exception` 계열 중 `RuntimeException` 계열이 아닌 것
- compiler가 `catch` 또는 `throws`를 강제

**Unchecked Exception**
- `RuntimeException` 및 그 자식
- compiler가 처리를 강제하지 않음

```text
RuntimeException인가?
├─ YES → Unchecked
└─ NO → Exception 계열이면 Checked
```

Custom Exception에서는:

```java
class MyException extends Exception {}
```
→ Checked

```java
class MyException extends RuntimeException {}
```
→ Unchecked

## 3. Checked Exception 처리

```java
void test() {
    throw new Exception("Error"); // Compile Error
}
```

방법 1 — 직접 처리:

```java
try {
    throw new Exception("Error");
} catch (Exception e) {
}
```

방법 2 — 호출자에게 위임:

```java
void test() throws Exception {
    throw new Exception("Error");
}
```

즉:

```text
Checked Exception → catch OR throws
```

## 4. Unchecked Exception

```java
void test() {
    throw new IllegalArgumentException("Error");
}
```

정상이다.

`try-catch`를 하면 안 된다는 뜻이 아니라 **compiler가 강제하지 않는다**는 뜻이다.

## 5. try-catch 흐름

```java
try {
    System.out.println("A");
    int result = 10 / 0;
    System.out.println("B");
} catch (ArithmeticException e) {
    System.out.println("C");
}
System.out.println("D");
```

출력:

```text
A
C
D
```

예외가 발생한 지점 이후의 try 코드는 실행되지 않는다.

## 6. Catch Parameter

```java
catch (IllegalArgumentException e)
```

`e`는 발생한 `IllegalArgumentException` 객체를 참조하는 **reference variable**이다.

```java
e.getMessage();
```

로 예외 message를 가져올 수 있다.

## 7. 여러 Catch와 상속

정상:

```java
catch (IllegalArgumentException e) {}
catch (RuntimeException e) {}
catch (Exception e) {}
```

구체적인 child exception부터 작성한다.

```java
catch (Exception e) {}
catch (RuntimeException e) {} // Compile Error
```

첫 번째 `Exception`이 이미 RuntimeException까지 처리하기 때문이다.

## 8. Catch도 다형성

```java
throw new IllegalArgumentException();
```

를 다음으로 잡을 수 있다.

```java
catch (RuntimeException e) {}
```

상속관계:

```text
Exception
   ↑
RuntimeException
   ↑
IllegalArgumentException
```

Day 4의 upcasting/다형성이 예외 처리에도 적용된다.

## 9. finally

```java
try {
    System.out.println("A");
    throw new RuntimeException();
} catch (RuntimeException e) {
    System.out.println("B");
} finally {
    System.out.println("C");
}
```

출력:

```text
A
B
C
```

`finally`는 정상/예외 흐름 이후 정리 작업에 사용된다.

## 10. throw vs throws

```java
void validate(int age) throws Exception {
    if (age < 0) {
        throw new Exception("Invalid age");
    }
}
```

```text
throw  = 실제 Exception 발생
throws = 호출자로 전달될 수 있음을 method 선언부에 표시
```

`throws`는 예외를 처리하는 것이 아니다.

## 11. Exception Propagation

```java
void c() throws Exception {
    throw new Exception();
}
void b() throws Exception {
    c();
}
void a() {
    try {
        b();
    } catch (Exception e) {
    }
}
```

```text
c에서 발생
↓ catch 없음
b로 전파
↓ catch 없음
a로 전파
↓ catch 발견
```

즉 `throws`는 예외를 없애는 것이 아니라 call stack 위로 전달한다.

## 12. Custom Exception과 super(message)

```java
class PaymentException extends RuntimeException {
    PaymentException(String message) {
        super(message);
    }
}
```

`super(message)`는 부모 Exception 생성자에 message를 전달한다.

이후:

```java
e.getMessage();
```

로 해당 message를 얻을 수 있다.

## 13. RuntimeException도 throws 가능

```java
void pay() throws PaymentException {
    throw new PaymentException("Error");
}
```

`PaymentException extends RuntimeException`이어도 정상이다.

하지만 `throws`는 **필수가 아니다.**

```java
void pay() {
    throw new PaymentException("Error");
}
```

도 정상.

## 14. Overriding + throws

Checked Exception에서는 자식이 부모보다 예외 범위를 넓힐 수 없다.

```text
Parent: throws Exception
Child : throws Exception    O
Child : throws IOException  O
Child : throws nothing      O
```

반면:

```text
Parent: throws IOException
Child : throws Exception
```

→ Compile Error

Unchecked Exception은 추가할 수 있다.

```java
class Parent {
    void work() {}
}
class Child extends Parent {
    @Override
    void work() throws IllegalArgumentException {}
}
```

정상.

## 15. finally + return

```java
static int test() {
    try {
        return 10;
    } finally {
        System.out.println("A");
    }
}
```

출력 순서:

```text
A
10
```

실제 method 종료 전에 finally가 실행된다.

```java
static int test() {
    try {
        return 10;
    } finally {
        return 20;
    }
}
```

결과는 `20`.

문법적으로 가능하지만 기존 return이나 exception을 숨길 수 있어 권장하지 않는다.

## 16. try-with-resources

```java
try (FileInputStream fis = new FileInputStream("test.txt")) {
    // resource 사용
}
```

try가 끝나면 자동으로 `close()`된다.

대상 resource는 `AutoCloseable`을 구현해야 한다.

## 17. Day 6 구현 — Payment

오늘 구현한 예외 계층:

```text
RuntimeException
      ↑
PaymentException
      ↑
InsufficientBalanceException
```

둘 다 Unchecked Exception이다.

```java
public void pay(long amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Invalid amount");
    }

    if (amount > getBalance()) {
        throw new InsufficientBalanceException("Insufficient balance");
    }

    decreaseBalance(amount);
    System.out.println("Payment Complete");
}
```

`balance`는 private으로 유지하고 자식은 부모의 protected method를 통해 상태를 변경한다.

## 18. PaymentService

```java
void process(Payment payment, long amount) {
    try {
        payment.pay(amount);
    } catch (InsufficientBalanceException e) {
        System.out.println("Payment Failed: " + e.getMessage());
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid Payment: " + e.getMessage());
    }
}
```

예상 결과:

```text
Payment Complete
Payment Failed: Insufficient balance
Invalid Payment: Invalid amount
```

초기 balance 10,000에서 첫 결제 3,000만 성공하므로 최종 balance는 `7000`.

오늘 작성한 코드에서는 처음에 `e.getMessage()`만 출력해서 요구된 `Payment Failed:` / `Invalid Payment:` prefix가 빠졌었다. 예외 처리에서도 요구 출력 형식을 정확히 확인한다.

## 19. 최종 암기표

| 개념 | 핵심 |
|---|---|
| Checked | Exception 중 RuntimeException 계열 제외 |
| Unchecked | RuntimeException 및 자식 |
| Checked 처리 | catch 또는 throws 강제 |
| Unchecked 처리 | 강제 X |
| `throw` | 실제 예외 발생 |
| `throws` | 호출자로 전달 가능성 선언 |
| `extends Exception` | Checked |
| `extends RuntimeException` | Unchecked |
| `super(message)` | 부모 Exception에 message 전달 |
| catch 순서 | 구체적 child → 넓은 parent |
| propagation | catch 없으면 call stack 위로 전파 |
| overriding Checked | 같거나 더 좁게 / 제거 가능 |
| overriding Unchecked | 추가 가능 |
| finally | method 종료 전 실행 |
| try-with-resources | AutoCloseable 자동 close |

## 20. 빠른 복습 문제

1. `IllegalArgumentException`은 Checked인가 Unchecked인가?
2. `IOException`은?
3. Checked Exception을 처리하는 두 방법은?
4. `throw`와 `throws`의 차이는?
5. RuntimeException도 `throws`에 작성할 수 있는가?
6. `extends Exception`인 Custom Exception은?
7. `extends RuntimeException`인 Custom Exception은?
8. 왜 `catch(Exception)` 뒤에 `catch(RuntimeException)`을 둘 수 없는가?
9. 왜 `IllegalArgumentException`을 `RuntimeException`으로 catch할 수 있는가?
10. Parent가 `throws Exception`이면 Child가 `throws IOException`이어도 되는가?
11. Parent가 `throws IOException`이면 Child가 `throws Exception`이어도 되는가?
12. Parent가 throws가 없어도 Child가 `throws IllegalArgumentException`을 추가할 수 있는가?
13. finally는 try의 return보다 먼저 실행되는가?
14. finally에서 return을 권장하지 않는 이유는?
15. try-with-resources 대상이 구현해야 하는 interface는?

### 정답

1. Unchecked
2. Checked
3. catch / throws
4. `throw` 실제 발생, `throws` 전달 가능성 선언
5. O. 필수는 아님
6. Checked
7. Unchecked
8. 부모 Exception이 RuntimeException까지 이미 처리하기 때문
9. 상속관계에 따른 upcasting/다형성
10. O
11. X
12. O
13. O
14. 기존 return이나 exception을 숨길 수 있기 때문
15. `AutoCloseable`
