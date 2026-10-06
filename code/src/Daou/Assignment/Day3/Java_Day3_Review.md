# Java Day 3 Review — 상속 / `super` / Overriding

## 1. 가장 중요한 기준

`Parent p = new Child();`를 만나면 다음 표를 먼저 떠올린다.

| 대상 | 결정 기준 |
|---|---|
| Instance Field | 참조 타입 |
| Static Field | 참조 타입 |
| Static Method | 참조 타입 / 클래스 |
| Overloaded Method | 컴파일 시점 타입 |
| Overridden Instance Method | 실제 객체 타입 |

즉 **overridden instance method가 runtime의 실제 객체를 본다.**

## 2. 상속과 생성자

`Child extends Parent`이면 Child는 Parent 타입으로 취급할 수 있다.

```java
Parent p = new Child();
```

Child 생성자는 부모 생성자를 먼저 호출한다. 명시적인 `this(...)` 또는 `super(...)`가 없다면 `super()`가 암묵적으로 들어간다.

```java
class Parent {
    Parent(int value) {}
}

class Child extends Parent {
    Child() {
        super(10);
    }
}
```

Parent에 `Parent()`가 없는데 Child가 `super()`를 호출하려 하면 Compile Error다.

`this(...)`는 같은 클래스의 다른 생성자, `super(...)`는 부모 생성자를 호출하며 둘 다 생성자의 첫 statement여야 한다.

## 3. `this`와 `super`

```java
class Parent {
    int value = 10;
}

class Child extends Parent {
    int value = 20;

    void print() {
        System.out.println(this.value);  // 20
        System.out.println(super.value); // 10
    }
}
```

- `this.value`: 현재 객체의 Child 관점 field
- `super.value`: Parent field
- `this.method()`: 현재 객체에 대한 instance method 호출
- `super.method()`: 부모 구현을 명시적으로 호출
- `super(...)`: 부모 constructor 호출

`super`는 별도의 부모 객체 reference가 아니라 현재 객체를 부모 클래스 관점에서 접근하기 위한 키워드다.

## 4. Overriding과 Dynamic Dispatch

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
p.print(); // Child
```

판단 순서:

1. Compile Time: 참조 타입 Parent에 `print()`가 존재하는지 확인
2. Runtime: 실제 객체 Child의 overriding 구현을 실행

따라서 Parent 타입에 없는 Child 전용 method는 바로 호출할 수 없다.

```java
Parent p = new Child();
p.childMethod(); // Compile Error
```

## 5. 부모 method 내부의 호출도 Dynamic Dispatch

```java
class Parent {
    void execute() {
        print();
    }

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
```

`new Child().execute()`는 Parent의 `execute()`를 사용하더라도 내부 `print()` 호출은 실제 객체 Child에 의해 dispatch되어 `Child.print()`가 실행된다.

## 6. Overriding 조건

기본적으로 method 이름과 parameter가 같아야 한다.

```java
void print(String value)
```

와

```java
void print(int value)
```

는 overriding이 아니다. `@Override`를 붙이면 Compile Error다.

접근제어자는 부모보다 좁힐 수 없다.

```java
// Parent
protected void print() {}

// Child
@Override
public void print() {} // 가능
```

반대로 `public`을 `protected`로 줄이는 것은 불가능하다.

Reference return type은 하위 타입으로 구체화할 수 있다.

```java
// Parent
Number getValue() { return 10; }

// Child
@Override
Integer getValue() { return 20; }
```

이를 Covariant Return Type이라고 한다.

## 7. `private` method는 Override되지 않는다

이번 Day에서 헷갈렸던 핵심 포인트다.

```java
class Parent {
    private void print() {
        System.out.println("Parent");
    }

    void execute() {
        print();
    }
}

class Child extends Parent {
    void print() {
        System.out.println("Child");
    }
}
```

```java
Parent p = new Child();
p.execute(); // Parent
```

Parent의 private `print()`와 Child의 `print()`는 서로 별개의 method다. Parent의 private method는 Child의 overriding 대상이 아니다.

따라서 Child의 `print()`에 `@Override`를 붙이면 Compile Error다.

## 8. Static Method Hiding

Static method는 overriding되지 않는다.

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

이를 **Method Hiding**이라고 한다.

```java
Parent.print(); // Parent
Child.print();  // Child
```

객체를 통해 호출해도 실제 객체 타입이 아니라 참조 타입을 기준으로 결정된다.

```java
Parent p = new Child();
Child c = new Child();

p.print(); // Parent
c.print(); // Child
```

Static method는 혼동을 피하기 위해 클래스 이름으로 호출하는 것이 좋다.

Child가 같은 static method를 선언하지 않았다면 부모 static method를 사용할 수 있다.

```java
class Child extends Parent {}

Child.print(); // Parent의 static print()
```

### 가능한 조합

```text
Parent instance → Child instance = Overriding 가능
Parent static   → Child static   = Method Hiding 가능
Parent instance → Child static   = Compile Error
Parent static   → Child instance = Compile Error
```

Static method에는 `@Override`를 붙일 수 없다.

## 9. Field Hiding vs Method Overriding

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
        return this.value;
    }
}

Parent p = new Child();

System.out.println(p.value);      // 10
System.out.println(p.getValue()); // 20
```

`p.value`는 field이므로 참조 타입 Parent 기준이다.

`p.getValue()`는 overridden instance method이므로 실제 객체 Child 기준이다.

**어떤 method가 선택되는지와 선택된 method 내부에서 어떤 field를 읽는지를 분리해서 생각한다.**

## 10. 부모/자식 초기화 순서

Child를 처음 생성할 때 큰 흐름:

```text
Parent static field / static block
→ Child static field / static block
→ Parent instance field / instance block
→ Parent constructor
→ Child instance field / instance block
→ Child constructor
```

Static 초기화는 클래스당 한 번이고 instance 초기화는 객체 생성마다 반복된다.

## 11. 생성자에서 Overridable Method 호출 주의

```java
class Parent {
    int value = 10;

    Parent() {
        print();
    }

    void print() {
        System.out.println(value);
    }
}

class Child extends Parent {
    int value = 20;

    @Override
    void print() {
        System.out.println(super.value);
        System.out.println(this.value);
    }
}
```

`new Child()`의 출력은:

```text
10
0
```

이유:

1. 객체 field는 먼저 기본값을 가진다.
2. Parent.value가 10으로 초기화된다.
3. Parent constructor가 실행된다.
4. `print()`는 override 가능한 instance method다.
5. 실제 객체가 Child이므로 `Child.print()`가 실행된다.
6. `super.value`는 이미 초기화된 Parent.value이므로 10.
7. Child의 field initializer는 아직 실행 전이므로 `this.value`는 int 기본값 0.
8. Parent constructor 종료 후 Child.value가 20으로 초기화된다.

Reference field라면 같은 시점에 `null`일 수 있다.

따라서 constructor에서 override 가능한 method를 호출하는 것은 위험할 수 있다.

## 12. `protected`

부모의 `private` member는 Child에서 직접 접근할 수 없지만 `protected`는 상속 관계에서 접근할 수 있다.

다른 package의 자식 클래스에서는 상속받은 자신의 관점에서 접근해야 한다.

```java
value
this.value
super.value
```

별도의 Parent 객체를 만들어 `p.value` 형태로 접근하는 것은 다른 package에서는 허용되지 않을 수 있다.

## 13. Day 3 최종 암기

```text
extends
- Child is a Parent
- Parent reference로 Child 객체 참조 가능

constructor
- Child 생성 전에 Parent 부분 초기화
- super() 생략 시 암묵적으로 호출
- Parent()가 없으면 super(args) 필요
- this() / super()는 첫 statement

overriding
- instance method
- 실제 객체 기준
- 접근 범위를 좁힐 수 없음
- final method override 불가
- private method override 불가

static method
- override X
- Method Hiding O
- 실제 객체가 아니라 참조/클래스 타입 기준

field
- override되지 않음
- hiding
- 참조 타입 기준

Parent p = new Child();

p.field
→ Parent

p.staticMethod()
→ Parent

p.overriddenMethod()
→ Child
```

## 14. 이번 Day에서 실제로 헷갈렸던 부분

1. `private` method는 접근 범위를 넓혀 override하는 것이 아니다. 애초에 override 대상이 아니다.
2. `static` method는 override가 아니라 Method Hiding이다.
3. 부모 static method는 Child가 별도로 선언하지 않아도 `Child.method()`로 사용할 수 있다.
4. 부모 instance method를 Child에서 static으로 변경할 수 없고 반대도 불가능하다.
5. Parent constructor에서 Child의 overridden method가 실행될 수 있다.
6. 이 시점에는 Child field initializer가 아직 실행되지 않아 `0`, `false`, `null` 같은 기본값을 읽을 수 있다.
7. `p.value`와 `p.getValue()`는 같은 방식으로 결정하지 않는다.
8. Parent method 내부의 일반 instance method 호출도 실제 객체에 따라 dynamic dispatch된다.
9. Parent의 private method 호출은 dynamic dispatch 대상이 아니다.

## 15. 빠른 복습 문제

1. `Parent p = new Child();`에서 `p.field`는 무엇을 기준으로 결정되는가?
2. `p.overriddenMethod()`는 무엇을 기준으로 결정되는가?
3. 부모와 자식이 동일한 static method를 선언하면 무엇이라고 하는가?
4. Parent의 private `print()`와 Child의 `print()`는 overriding 관계인가?
5. Parent constructor에서 Child가 override한 method가 호출될 수 있는가?
6. 그 시점에 아직 초기화되지 않은 Child의 `int value`는 얼마인가?
7. Parent instance method를 Child에서 static으로 선언할 수 있는가?

### 정답

1. 참조 타입
2. 실제 객체 타입
3. Method Hiding
4. 아니다. 별개의 method다.
5. 가능하다.
6. 0
7. 불가능. Compile Error.
