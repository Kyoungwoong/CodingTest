# Java Day 5 Review — Abstract Class & Interface

## 1. 핵심
- **Abstract class**: 공통 instance state, constructor, 기반 구현을 공유하는 상속 계층에 적합
- **Interface**: 서로 다른 클래스에 공통 계약/능력을 부여할 때 적합
- 둘 다 reference type으로 사용하여 **다형성**을 구현할 수 있다.

## 2. Abstract Class
```java
abstract class Payment {
    private int amount;

    Payment(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public abstract void pay();
}
```

가능한 것:
- instance field
- constructor
- 일반 method
- abstract method
- static method

직접 객체 생성은 불가능하다.
```java
new Payment(1000); // Compile Error
```

Concrete child는 abstract method를 구현해야 한다. 구현을 미루려면 child도 `abstract`여야 한다.

## 3. Interface 기본
```java
interface Payment {
    void pay();
}

class CardPayment implements Payment {
    @Override
    public void pay() {}
}
```

관계:
```text
class -> class         : extends
class -> interface     : implements
interface -> interface : extends
```

## 4. Interface Abstract Method
```java
interface Payment {
    void pay();
}
```
사실상:
```java
public abstract void pay();
```

따라서 구현 method는 접근 범위를 줄일 수 없다.
```java
void pay() {}        // Compile Error
public void pay() {} // O
```

## 5. Interface Field
```java
interface Config {
    int MAX = 100;
}
```
사실상:
```java
public static final int MAX = 100;
```

따라서 객체별 instance field가 아니며 재할당할 수 없다.
```java
Config.MAX = 200; // Compile Error
```

## 6. Interface와 다형성
```java
Printer printer = new ConsolePrinter();
printer.print(); // reference type에 print() 존재 -> O
printer.reset(); // Printer에 reset()이 없다면 Compile Error
```

핵심:
```text
호출 가능한가? -> reference type
어떤 override가 실행되는가? -> actual object type
```

## 7. Default Method
```java
interface Payment {
    default void cancel() {
        System.out.println("Cancel");
    }
}
```
구현 class가 그대로 사용하거나 override할 수 있다.

## 8. Interface Static Method — 오늘 헷갈린 부분
```java
interface A {
    static void print() {}
}
```

```java
A.print(); // O
```

하지만:
```java
A a = ...;
a.print(); // Compile Error

class B implements A {}
B.print(); // Compile Error
```

**Interface static method는 구현 class에 상속되지 않는다.**
반드시 `InterfaceName.method()` 형태로 호출한다.

## 9. Interface Private Method
```java
interface Payment {
    default void pay() {
        validate();
    }

    private void validate() {}
}
```
Interface 내부 구현을 위한 helper다. 외부나 구현 class에서 직접 호출할 수 없다.

## 10. Interface Method 정리
| 선언 | 구현부 | 핵심 |
|---|---|---|
| `void pay();` | X | `public abstract` |
| `default void pay() {}` | O | 상속/override 가능 |
| `static void pay() {}` | O | `InterfaceName.pay()` |
| `private void pay() {}` | O | interface 내부 helper |

## 11. 다중 Interface
```java
class C extends A, B {}              // X
class C implements A, B {}          // O
interface C extends A, B {}          // O
class C extends Parent implements A, B {} // O
```

## 12. Default Method 충돌
```java
interface A {
    default void print() { System.out.println("A"); }
}

interface B {
    default void print() { System.out.println("B"); }
}

class C implements A, B {
    @Override
    public void print() {
        A.super.print();
    }
}
```

관계없는 두 interface가 동일한 default method를 제공하면 구현 class가 직접 override해야 한다.

`A.super.print()`는 A의 default 구현을 명시적으로 호출한다.

## 13. Default Method 우선순위
### Class wins
Superclass concrete method와 interface default method가 충돌하면 superclass method가 우선한다.

### More specific interface wins
```java
interface B extends A
```
이고 A/B가 같은 default method를 제공하면 더 구체적인 B가 우선한다.

정리:
1. Superclass concrete method -> **Class wins**
2. Interface 상속 관계 -> **More specific interface wins**
3. 관계없는 interface끼리 충돌 -> 구현 class가 직접 override

## 14. Abstract Class vs Interface
### Abstract Class
`Employee -> Developer / Manager`처럼 같은 기반 객체이며 다음을 공유할 때 적합:
- `name`
- `salary`
- constructor
- 공통 구현

### Interface
Developer, Manager, Invoice가 모두 PDF 출력이 가능하지만 같은 종류의 객체는 아닐 때:
```java
interface Printable {
    void print();
}
```

시험용 핵심 문장:

> Abstract class는 공통 상태와 기반 구현을 공유하는 상속 계층에 적합하고, interface는 서로 다른 클래스에 공통된 행위나 계약을 부여하는 데 적합하다.

## 15. Abstract Class가 Interface 구현을 미룰 수 있음
```java
interface Printable {
    void print();
}

abstract class Document implements Printable {
}
```
정상이다. `Document`가 abstract이므로 구현 책임을 concrete child에게 넘길 수 있다.

```java
class PdfDocument extends Document {
    @Override
    public void print() {}
}
```

## 16. Abstract / Interface도 다형성 원리는 동일
```java
Parent p = new Child();
p.print();
```
- 호출 가능 여부: `Parent`
- 실제 override 실행: `Child`

Abstract class나 interface라고 이 원리가 달라지지 않는다.

## 17. Reference Type A인데 B가 출력되는 이유
```java
class C implements A, B {
    @Override
    public void print() {
        B.super.print();
    }
}

A a = new C();
a.print();
```

과정:
1. A에 `print()`가 있으므로 호출 가능
2. actual object는 C
3. C의 overriding `print()` 실행
4. C 내부에서 `B.super.print()`
5. B 출력

## 18. BankAccount 문제 — 캡슐화
처음 구현처럼 `balance`를 `private`으로 유지한 것은 좋다.

하지만:
```java
public boolean setBalance(long balance)
```
를 열어두면 외부가 잔액 자체를 임의 변경할 수 있다.

더 나은 방향:
```java
public void deposit(long amount) { ... }
protected boolean decreaseBalance(long amount) { ... }
```

즉 `setBalance()`보다:
- `deposit()`
- `withdraw()`
- `transfer()`

처럼 **의미 있는 행위**를 통해 상태를 변경한다.

> Encapsulation은 field에 `private`을 붙이는 것뿐 아니라, 객체 상태가 올바른 규칙을 통해서만 변경되도록 API를 설계하는 것까지 포함한다.

## 19. BankAccount에서 실제로 발생한 로직 실수
잘못된 형태:
```java
withdraw(amount);
target.setBalance(this.getBalance() + amount);
```

Receiver의 기존 잔액이 아니라 Sender의 잔액을 사용하고 있다.

최소 수정:
```java
target.setBalance(target.getBalance() + amount);
```

더 좋은 구조:
```java
if (decreaseBalance(amount)) {
    target.deposit(amount);
}
```

각 객체가 자신의 상태를 변경하도록 한다.

## 20. withdraw 반환 타입
문제 요구사항은:
```java
public abstract void withdraw(long amount);
```

하지만 성공 여부가 필요하다면:
```java
public abstract boolean withdraw(long amount);
```
같은 설계가 더 유용할 수 있다.

실제 시스템에서는 요구사항에 따라 boolean, exception, 결과 객체 등을 선택한다.

## 21. 최종 암기표
| 대상 | 결과 |
|---|---|
| abstract class 직접 생성 | X |
| interface 직접 생성 | X |
| abstract class constructor | O |
| interface constructor | X |
| abstract class instance field | O |
| interface 일반 instance field | X |
| interface field | `public static final` |
| interface abstract method | `public abstract` |
| default method | 구현 O / override O |
| interface static method | `InterfaceName.method()` |
| interface private method | 내부 helper |
| class extends 여러 class | X |
| class implements 여러 interface | O |
| interface extends 여러 interface | O |
| abstract class가 interface 구현 생략 | O |
| concrete class가 interface 구현 생략 | X |

## 22. 빠른 복습 문제
1. Abstract class는 constructor를 가질 수 있는가?
2. Interface의 `int MAX = 10;`에 실제로 붙는 modifier는?
3. Interface의 `void print();`에 실제로 붙는 modifier는?
4. Interface static method를 구현 class 이름으로 호출할 수 있는가?
5. 관계없는 두 interface가 동일한 default method를 제공하면?
6. Superclass method와 interface default method가 충돌하면?
7. Parent interface와 child interface의 default method가 충돌하면?
8. Abstract class가 interface method 구현을 생략할 수 있는 이유는?
9. `private` field + public setter면 항상 좋은 캡슐화인가?
10. Abstract class와 interface 선택의 핵심 기준은?

### 정답
1. O
2. `public static final`
3. `public abstract`
4. X. Interface 이름으로 호출
5. 구현 class가 override. 필요하면 `A.super.method()`
6. Superclass method 우선 — Class wins
7. 더 구체적인 child interface 우선
8. 구현 책임을 concrete child에게 넘길 수 있기 때문
9. X. 의미 있는 상태 변경 method가 더 적절할 수 있음
10. 공통 instance state/기반 구현 공유인지, 공통 계약/능력 부여인지 판단
