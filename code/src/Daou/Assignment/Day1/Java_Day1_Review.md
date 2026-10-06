# Java Day 1 - Class / Object / Constructor / this / Access Modifier

## 1. Class와 Object

```java
class User {
    String name;
}

User user = new User();
```

- `User`: 클래스 / 타입
- `user`: 참조 변수
- `new User()`: 객체 생성
- 생성된 객체: Instance

```text
user ─────→ User 객체
```

## 2. Constructor

생성자는 객체를 생성할 때 초기화를 담당한다.

```java
class User {
    String name;

    User(String name) {
        this.name = name;
    }
}
```

### 특징

- 클래스와 이름이 같다.
- return type이 없다.
- 생성자를 하나도 작성하지 않으면 기본 생성자가 자동 제공된다.
- 생성자를 하나라도 작성하면 기본 생성자는 자동 제공되지 않는다.

```java
User(String name) {
}
```

를 작성하면 `new User()`는 Compile Error이다.

## 3. this

`this`는 현재 객체 자신을 의미한다.

```java
class User {
    String name;

    User(String name) {
        this.name = name;
    }
}
```

- `this.name`: 현재 객체의 field
- `name`: 가장 가까운 parameter/local variable

따라서 다음 코드는 field를 변경하지 않는다.

```java
name = name;
```

## 4. this()

같은 클래스의 다른 생성자를 호출한다.

```java
User(String name) {
    this(name, 0);
}

User(String name, int age) {
    this.name = name;
    this.age = age;
}
```

`this()`는 반드시 생성자의 첫 번째 statement여야 한다.

## 5. Access Modifier

| Modifier | 접근 범위 |
|---|---|
| `private` | 같은 클래스 |
| default | 같은 package |
| `protected` | 같은 package + 상속 관계 |
| `public` | 모든 곳 |

### private의 핵심

`private`은 객체 단위가 아니라 **클래스 단위 접근 제한**이다.

```java
class User {
    private String name;

    void copy(User other) {
        this.name = other.name;
    }
}
```

`other.name`은 정상이다. 같은 `User` 클래스 내부이기 때문이다.

## 6. Encapsulation

캡슐화는 단순히 모든 field를 private으로 만들고 getter/setter를 만드는 것이 아니다.

객체가 자신의 상태와 규칙을 직접 관리하도록 만드는 것이 핵심이다.

```java
private String name;

public void changeName(String name) {
    if (name == null || name.isEmpty()) {
        return;
    }

    this.name = name;
}
```

무조건적인 setter를 제공하면 객체의 규칙을 우회할 수 있으므로 주의한다.

## 7. Field와 Local Variable

Field에는 기본값이 존재한다.

```text
int / long 등 → 0
boolean       → false
reference     → null
```

```java
class User {
    String name; // null
    int age;     // 0
}
```

Local variable은 사용하기 전에 직접 초기화해야 한다.

```java
void test() {
    int value;
    System.out.println(value); // Compile Error
}
```

## 8. Instance 초기화 순서

기본적인 객체 초기화 순서:

```text
Field 기본값
↓
Field initializer
↓
Instance initializer
↓
Constructor
```

예:

```java
class User {
    String name = "A";

    {
        name = "B";
    }

    User() {
        name = "C";
    }
}
```

최종값은 `C`.

## 9. Java는 항상 Pass By Value

Java는 primitive와 reference 모두 Pass By Value이다.

```java
static void change(User user) {
    user.name = "Lee";
}
```

참조값의 복사본이 전달된다.

```text
caller user ──┐
              ├──→ User 객체
parameter ────┘
```

같은 객체를 가리키므로 객체 상태 변경은 caller에서도 확인된다.

## 10. Reference 재할당

```java
static void change(User user) {
    user = new User();
    user.name = "Lee";
}
```

parameter에 저장된 참조값만 변경된다. caller의 reference에는 영향이 없다.

```text
caller ─────────→ 기존 객체
parameter ──────→ 새로운 객체
```

## 11. Reference 비교

객체에서 `==`는 같은 객체를 가리키는지 비교한다.

```java
User a = new User();
User b = a;

System.out.println(a == b); // true
```

하지만 서로 별도로 생성한 객체는 false이다.

```java
User a = new User();
User b = new User();

System.out.println(a == b); // false
```

## 12. String 비교

문자열 내용 비교는 `equals()`를 사용한다.

```java
String a = new String("hello");
String b = new String("hello");

a == b;       // false
a.equals(b);  // true
```

```text
객체 동일성 → ==
내용 비교   → equals()
```

## 13. Method Overloading

같은 이름의 method를 parameter 구성에 따라 여러 개 정의할 수 있다.

```java
void print(int value) {}
void print(long value) {}
void print(String value) {}
```

구분 기준:

- parameter 개수
- parameter 타입
- parameter 순서

parameter 이름이나 return type만 다른 것은 Overloading이 아니다.

## 14. Overloading 결정 시점

Overloading은 Compile Time에 결정된다.

```java
void print(Object value) {}
void print(String value) {}

Object value = "hello";
print(value);
```

호출되는 것은 `print(Object)`이다.

실제 객체가 String이어도 선언 타입이 `Object`이기 때문이다.

## 15. Primitive Widening / Narrowing

대표적인 widening:

```text
byte → short → int → long → float → double
                 ↑
char ────────────┘
```

Widening은 자동 변환 가능하다.

```java
int value = 10;
long result = value;
```

Narrowing은 명시적 cast가 필요하다.

```java
int value = 10;
short result = (short) value;
```

단, `short value = 10;`처럼 컴파일러가 범위를 확인할 수 있는 상수 literal에는 예외가 존재한다.

## 16. Varargs

가변 개수의 argument를 받을 수 있다.

```java
void print(int... values) {
}
```

```java
print();
print(10);
print(10, 20, 30);
```

내부에서는 배열처럼 사용한다.

Varargs는 parameter 목록의 마지막에 위치해야 한다.

## 17. 일반 Method vs Varargs

```java
void print(int value) {}
void print(int... values) {}

print(10);
```

일반 method인 `print(int)`가 우선된다.

## 18. 객체 배열

```java
User[] users = new User[3];
```

User 객체 3개가 생성되는 것이 아니다.

```text
[ null | null | null ]
```

따라서 객체 생성 전 `users[0].name = "Kim";`을 실행하면 Runtime Error인 `NullPointerException`이 발생한다.

## 19. 배열도 객체다

```java
int[] a = {1, 2, 3};
int[] b = a;

b[0] = 100;

System.out.println(a[0]); // 100
```

두 reference가 같은 배열 객체를 가리킨다.

## 20. Compile Time vs Runtime Error

대표적인 Compile Error:

- private 접근 위반
- 존재하지 않는 method 호출
- type mismatch
- 잘못된 constructor 호출

대표적인 Runtime Error:

```java
User user = null;
user.getName();
```

→ `NullPointerException`

## Day 1 핵심 요약

```text
Class      → 객체의 설계도
Object     → 실제 생성된 객체
Reference  → 객체를 가리키는 값

this       → 현재 객체
this()     → 같은 클래스의 다른 생성자

private    → 객체 기준 X / 클래스 기준 O

Java       → 항상 Pass By Value

객체 상태 변경
→ caller에서도 확인 가능

parameter reference 재할당
→ caller reference에는 영향 없음

Overloading
→ Compile Time
→ 선언된 타입 기준

객체 ==
→ 같은 객체인지 비교
```
