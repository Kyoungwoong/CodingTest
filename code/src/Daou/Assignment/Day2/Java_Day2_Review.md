# Java Day 2 - static / final

## 1. Instance Member

Instance field와 method는 객체에 속한다.

```java
class User {
    String name;
}
```

`User a = new User();`, `User b = new User();`에서 `a.name`과 `b.name`은 서로 다른 field이다.

## 2. static

`static` member는 객체가 아니라 클래스에 속한다.

```java
class User {
    static int count;
}
```

접근은 `User.count`처럼 클래스 이름으로 하는 것이 원칙이다.

## 3. static Field 공유

```java
class User {
    static int count;
}

User a = new User();
User b = new User();

a.count = 10;

System.out.println(b.count);    // 10
System.out.println(User.count); // 10
```

모두 동일한 static field를 의미한다.

## 4. static Method

static method는 객체 없이 호출할 수 있다.

```java
class User {
    static void print() {
    }
}

User.print();
```

## 5. static → Instance 직접 접근 불가능

```java
class User {
    static int count;
    String name;

    static void print() {
        System.out.println(count); // O
        System.out.println(name);  // Compile Error
    }
}
```

static method는 특정 객체와 연결되어 있지 않기 때문에 어떤 객체의 `name`인지 결정할 수 없다.

## 6. Instance → static 접근 가능

```java
class User {
    static int count;
    String name;

    void print() {
        System.out.println(name);
        System.out.println(count);
    }
}
```

둘 다 가능하다.

## 7. static에서 this 사용 불가능

`this`는 현재 객체를 의미한다. static method에는 현재 객체가 존재하지 않는다.

```java
static void print() {
    System.out.println(this); // Compile Error
}
```

## 8. static Method에서도 명시적인 객체는 사용 가능

```java
class User {
    private String name;

    static void change(User user) {
        user.name = "Kim";
    }
}
```

`user`라는 특정 객체가 명확하기 때문에 접근 가능하다.

## 9. static 초기화

```java
class Test {
    static int value = 10;

    static {
        value = 20;
    }
}
```

static initializer는 클래스 초기화 시 실행된다. 객체를 생성할 때마다 실행되는 것이 아니다.

## 10. 초기화 순서

### 클래스 초기화

```text
static field initializer
static block
```

코드에 작성된 순서대로 실행된다.

### 객체 초기화

```text
instance field initializer
instance initializer
constructor
```

객체를 생성할 때마다 실행된다.

## 11. 초기화 순서 예제

```java
class Test {
    static int a = print("static field");

    static {
        print("static block");
    }

    int b = print("instance field");

    {
        print("instance block");
    }

    Test() {
        print("constructor");
    }
}
```

첫 객체 생성:

```text
static field
static block
instance field
instance block
constructor
```

두 번째 객체 생성 시 static 부분은 다시 실행되지 않는다.

## 12. final Primitive

```java
final int value = 10;
value = 20; // Compile Error
```

`final` 변수는 최초 할당 이후 재할당할 수 없다.

## 13. Blank Final

```java
class User {
    final int id;

    User(int id) {
        this.id = id;
    }
}
```

객체 생성이 완료될 때까지 모든 생성 경로에서 정확히 한 번 초기화되어야 한다.

## 14. 잘못된 Blank Final

```java
class User {
    final int id;

    User(boolean flag) {
        if (flag) {
            id = 10;
        }
    }
}
```

Compile Error.

`flag == false`이면 `id`가 초기화되지 않기 때문이다.

## 15. this()와 final

```java
class User {
    final int id;

    User() {
        this(10);
    }

    User(int id) {
        this.id = id;
    }
}
```

정상이다.

하지만 다음은 Compile Error이다.

```java
User() {
    this(10);
    id = 20;
}
```

`this(10)`을 통해 이미 `id`가 초기화되었기 때문이다.

## 16. final Reference

```java
final User user = new User();
```

객체 내부 변경은 가능하다.

```java
user.setName("Kim");
```

다른 객체로 reference를 재할당하는 것은 불가능하다.

```java
user = new User(); // Compile Error
```

```text
final reference
→ 객체 변경 금지 X
→ reference 재할당 금지 O

final reference != immutable object
```

## 17. final Array

```java
final int[] values = {1, 2, 3};

values[0] = 100;              // O
values = new int[]{4, 5, 6};  // Compile Error
```

배열도 객체이므로 같은 원리가 적용된다.

## 18. final Parameter

```java
void change(final User user) {
    user.setName("Kim"); // O
    user = new User();   // Compile Error
}
```

parameter의 reference 재할당만 금지된다.

## 19. static final

```java
public static final int MAX_COUNT = 100;
```

```text
static → 클래스에 하나
final  → 재할당 불가능
```

상수 이름은 보통 `MAX_COUNT`, `DEFAULT_SIZE`, `MAX_RETRY_COUNT`처럼 대문자와 `_`를 사용한다.

## 20. final 위치별 의미

```text
final 변수   → 재할당 X
final method → overriding X
final class  → 상속 X
```

## 21. Static Method Hiding

```java
class Parent {
    static void print() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    static void print() {
        System.out.println("Child");
    }
}
```

static method는 overriding되지 않고 Method Hiding이 발생한다.

```java
Parent p = new Child();
p.print();
```

결과:

```text
Parent
```

static method는 실제 객체가 아니라 참조 타입을 기준으로 결정된다.

## 22. Instance Method Overriding과 비교

```java
class Parent {
    void print() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    @Override
    void print() {
        System.out.println("Child");
    }
}

Parent p = new Child();
p.print();
```

결과:

```text
Child
```

Overridden instance method는 실제 객체 타입을 기준으로 실행된다.

## 23. Field Hiding

```java
class Parent {
    int value = 10;
}

class Child extends Parent {
    int value = 20;
}

Parent p = new Child();
System.out.println(p.value); // 10
```

Field는 overriding되지 않는다. 참조 타입을 기준으로 field가 결정된다.

## 24. Field와 Method의 차이

```java
class Parent {
    int value = 10;

    int getValue() {
        return value;
    }
}

class Child extends Parent {
    int value = 20;

    @Override
    int getValue() {
        return value;
    }
}

Parent p = new Child();

System.out.println(p.value);      // 10
System.out.println(p.getValue()); // 20
```

이유:

```text
p.value
→ Field
→ 참조 타입 Parent
→ Parent.value

p.getValue()
→ Overridden Instance Method
→ 실제 객체 Child
→ Child.getValue()
```

## 25. 호출 결정 기준

`Parent p = new Child();`에서:

| 대상 | 결정 기준 |
|---|---|
| Instance Field | 참조 타입 |
| Static Field | 참조 타입 |
| Static Method | 참조 타입 |
| Overloaded Method | Compile Time의 선언 타입 |
| Overridden Instance Method | 실제 객체 타입 |

```text
Field
→ Reference Type

Static
→ Reference Type

Overloading
→ Compile Time / Reference Type

Overriding
→ Runtime / Actual Object Type
```

## 26. 자주 틀리는 초기화 문제

```java
class Test {
    static int count = 0;

    {
        count++;
    }

    Test() {
        count++;
    }
}

new Test();
new Test();

System.out.println(Test.count); // 4
```

Instance initializer와 constructor 모두 객체 생성마다 실행된다.

## 27. 후위 증가와 Instance 초기화

```java
class Test {
    static int count = 10;
    int value = count++;

    Test() {
        value += 10;
    }
}

Test a = new Test();
Test b = new Test();
```

최종값:

```text
Test.count = 12
a.value    = 20
b.value    = 21
```

`count++`는 현재 값을 먼저 사용하고 이후 증가한다.

## Day 2 핵심 요약

```text
static
→ 객체가 아니라 클래스에 속한다.
→ 모든 객체가 공유한다.
→ 클래스 이름으로 접근한다.
→ this 사용 불가능
→ instance member 직접 접근 불가능

static initialization
→ 클래스 초기화 시 1회

instance initialization
→ 객체 생성마다 실행

final
→ 기본 개념은 재할당 금지

final primitive
→ 값 재할당 X

final reference
→ reference 재할당 X
→ 객체 내부 변경 O

final array
→ 배열 reference 재할당 X
→ 요소 변경 O

blank final
→ 모든 생성 경로에서 정확히 한 번 초기화

static final
→ 클래스에 하나 + 재할당 불가능

static method
→ overriding이 아니라 hiding
→ reference type 기준

instance overriding
→ actual object type 기준
```
