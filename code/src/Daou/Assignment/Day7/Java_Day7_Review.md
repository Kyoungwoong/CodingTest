# Java Day 7 — Object, equals/hashCode/toString, enum

## 1. 핵심 개념

Java의 일반 클래스는 명시하지 않아도 `Object`를 상속한다. `Object`에서 중요한 메서드는 `equals(Object)`, `hashCode()`, `toString()`이다.

| 표현 | 비교 기준 | 예시 |
|---|---|---|
| `a == b` (primitive) | 원시값 | `10 == 10` → `true` |
| `a == b` (reference) | 동일한 객체를 참조하는지 | 서로 다른 `new` 객체 → `false` |
| `a.equals(b)` | `equals()` 구현에 따른 논리적 동등성 | 주문 ID가 같으면 `true`로 정의 가능 |

기본 `Object.equals()`는 객체 identity를 비교한다. 논리적 동등성을 원하면 오버라이딩해야 한다.

## 2. `equals(Object)` — 실제 구현에서 발견한 문제

처음 작성한 코드:

```java
@Override
public boolean equals(Object obj) {
    return getId() == ((Order) obj).getId();
}
```

- `a.equals(null)` → `NullPointerException`
- `a.equals("hello")` → `ClassCastException`
- `a.equals(new Order(1, "Lee"))` → `true`

안전한 구현:

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Order other)) return false;
    return this.id == other.id;
}
```

`null instanceof Order`는 `false`이므로 별도 null 검사 없이 안전하다. `this == obj`는 동일 객체에 대한 빠른 처리다.

**오버로딩 함정**: `equals(Order other)`는 `Object.equals(Object)`를 오버라이딩하지 않는다. `@Override`를 붙이면 시그니처 오류를 컴파일 시점에 확인할 수 있다.

## 3. `hashCode()` 계약

**반드시 성립:** `a.equals(b) == true` → `a.hashCode() == b.hashCode()`.

**반대는 성립하지 않음:** 같은 해시코드라도 서로 다른 객체일 수 있다. 이를 *hash collision*이라고 한다.

ID가 동등성 기준이라면:

```java
@Override
public int hashCode() {
    return Long.hashCode(id);
}
```

`HashSet`/`HashMap` 등은 해시코드로 후보를 좁히고 `equals()`로 동등성을 판단한다. **상태값(status)이 변해도 주문의 동일성은 ID로 유지**하는 설계라면 `equals()`/`hashCode()` 모두 ID만 사용한다.

## 4. `toString()` — 실제 구현에서 발견한 문제

기존 구현에서는 `customer`의 따옴표와 마지막 `}`가 빠졌다. 기대 출력 형식까지 맞추려면:

```java
@Override
public String toString() {
    return "Order{id=" + id
        + ", customer='" + customer + "'"
        + ", status=" + status + "}";
}
```

출력: `Order{id=1, customer='Kim', status=PAID}`.

## 5. enum은 문자열이 아니라 타입이다

```java
enum OrderStatus {
    READY("주문 대기"), PAID("결제 완료"), CANCELLED("주문 취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }
    public boolean isFinished() { return this == PAID || this == CANCELLED; }
}
```

- `OrderStatus`는 타입이고 `READY`, `PAID`, `CANCELLED`는 각각의 enum 인스턴스다.
- enum 인스턴스를 `new OrderStatus()`로 만들 수 없다.
- `READY` 인스턴스의 `description`은 `"주문 대기"`, `PAID` 인스턴스는 `"결제 완료"`이다.
- `OrderStatus.READY.name()` → `"READY"`.
- `OrderStatus.values()` → 선언된 모든 상수의 배열.
- enum 상수의 `toString()`은 오버라이딩하지 않으면 이름을 반환한다. `name()`과 `toString()`은 오버라이딩 시 달라질 수 있다.

**String 대신 enum을 사용하는 이유**: `changeStatus("PADI")`는 컴파일되지만 `changeStatus(OrderStatus.PADI)`는 컴파일되지 않는다. 허용 가능한 값의 집합을 타입으로 제한한다.

## 6. enum의 null과 `valueOf()` — 오늘 틀렸던 부분

```java
OrderStatus status = null;
System.out.println(status == OrderStatus.READY);      // false
System.out.println(status.equals(OrderStatus.READY)); // NPE
```

`==`는 null reference 비교가 가능하지만 null을 통해 인스턴스 메서드를 호출하면 NPE가 발생한다.

```java
OrderStatus.valueOf("PAID"); // 정상
OrderStatus.valueOf("paid"); // 컴파일 정상, 실행 중 IllegalArgumentException
```

`valueOf(String)`의 매개변수 타입은 `String`이므로 잘못된 문자열 자체는 컴파일러가 막지 못한다. enum 이름은 대소문자를 구분한다.

## 7. 주문 상태 변경과 예외

```java
public void pay() {
    if (status != OrderStatus.READY)
        throw new IllegalStateException("Cannot pay order");
    status = OrderStatus.PAID;
}

public void cancel() {
    if (status != OrderStatus.READY)
        throw new IllegalStateException("Cannot cancel order");
    status = OrderStatus.CANCELLED;
}
```

`IllegalStateException`은 **현재 객체의 상태 때문에 작업이 허용되지 않을 때** 적절하다. `IllegalArgumentException`은 일반적으로 **인자로 받은 값이 유효하지 않을 때** 사용한다.

## 8. 오늘의 오답 및 주의점

1. `status.equals(...)`에서 status가 null이면 `false`가 아니라 **NPE**.
2. `valueOf("done")`은 **Compile Error가 아니라 Runtime의 `IllegalArgumentException`**.
3. `Status.DONE.getDescription()`은 `"완료"`이며, 각 enum 인스턴스가 자신의 필드를 가진다.
4. `valueOf()`에 없는 이름을 넣는다고 컴파일 에러가 발생하지 않는다.
5. 구현 과제에서는 `equals()`의 null/타입 검사를 빠뜨렸고, `toString()` 출력 형식이 누락되었다.
6. enum의 `==`는 값 내용 비교가 아니라 **동일 enum 인스턴스 비교**이며, 상수별 인스턴스가 고정되어 있어 적합하다.

## 9. 직접 풀어보는 복습 문제

1. `equals(Order)`는 `Object.equals(Object)`의 오버라이딩인가?
2. `new Order(1, "A").equals(null)`은 안전한 구현에서 무엇을 반환하는가?
3. 서로 다른 객체의 `hashCode()`가 같을 수 있는가?
4. `equals()`는 ID만 비교하고 `hashCode()`는 ID와 상태를 사용해도 되는가?
5. `OrderStatus.valueOf("paid")`는 컴파일 에러인가, 런타임 예외인가?
6. `OrderStatus s = null; s == OrderStatus.READY`의 결과는?
7. `OrderStatus s = null; s.equals(OrderStatus.READY)`의 결과는?
8. `OrderStatus.PAID.getDescription()`의 결과는?
9. 주문이 이미 PAID인데 `cancel()`을 호출했다면 어떤 예외가 적절한가?
10. `HashSet`에 ID가 같은 주문 객체 두 개를 넣으면, 올바른 equals/hashCode 구현에서 크기는?

### 정답

1. 아니오, 오버로딩.
2. `false`.
3. 가능, hash collision.
4. 안 됨. 같은 ID지만 상태가 다른 두 객체의 hashCode가 달라질 수 있어 계약 위반.
5. 컴파일 정상, 런타임 `IllegalArgumentException`.
6. `false`.
7. `NullPointerException`.
8. `"결제 완료"`.
9. `IllegalStateException`.
10. `1`.

## 10. 실습 파일

`Day7Review.java`를 같은 폴더에 놓고 다음처럼 실행한다.

```bash
javac -d . Day7Review.java
java Daou.Assignment.Day7.Day7Review
```

실습에는 `equals`의 null/타입 검사, `HashSet`, enum 필드와 `valueOf`, 상태 변경 예외까지 포함했다.
