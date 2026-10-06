# Java Day 4 Review — Polymorphism / Upcasting / Downcasting / `instanceof`

## 1. 가장 중요한 구분

```java
Animal animal = new Dog();
```

- Reference Type: `Animal`
- Actual Object Type: `Dog`

| 판단 대상 | 기준 |
|---|---|
| 호출 가능한 member | Reference Type |
| Overridden instance method | Actual Object Type |
| Downcasting 성공 여부 | Actual Object Type |
| `instanceof` 결과 | Actual Object Type |

## 2. Polymorphism

```java
Animal dog = new Dog();
Animal cat = new Cat();

dog.sound();
cat.sound();
```

같은 `Animal` 타입으로 서로 다른 자식 구현을 처리할 수 있다. Overridden instance method는 실제 객체 타입에 따라 실행된다.

## 3. Upcasting

```java
Dog dog = new Dog();
Animal animal = dog;
```

`Dog -> Animal`은 Upcasting이며 자동이다.

객체가 Animal로 변하는 것이 아니다. 같은 Dog 객체를 더 일반적인 부모 타입으로 바라보는 것이다.

```java
System.out.println(dog == animal); // true
```

객체는 하나다.

## 4. Reference Type과 호출 가능 여부

```java
Animal animal = new Dog();

animal.sound(); // O
animal.bark();  // Compile Error
```

실제 객체가 Dog여도 `animal`의 reference type인 Animal에 `bark()`가 없으므로 호출할 수 없다.

> 호출 가능한가? → Reference Type  
> 어떤 override 구현이 실행되는가? → Actual Object Type

## 5. Downcasting

```java
Animal animal = new Dog();
Dog dog = (Dog) animal;
```

`Parent -> Child`는 Downcasting이며 명시적 cast가 필요하다.

```java
Dog dog = animal; // Compile Error
```

모든 Animal이 Dog라는 보장이 없기 때문이다.

Downcasting 역시 새 객체를 만들지 않는다.

```java
System.out.println(animal == dog); // true
```

## 6. `ClassCastException`

```java
Animal animal = new Cat();
Dog dog = (Dog) animal;
```

이 코드는 Compile된다. `Animal` reference가 Dog 객체를 가리킬 가능성 자체는 있기 때문이다.

하지만 실제 객체가 Cat이므로 Runtime에:

```text
ClassCastException
```

이 발생한다.

## 7. 형제 클래스 직접 Casting

```java
Dog dog = new Dog();
Cat cat = (Cat) dog;
```

Dog와 Cat이 서로 직접적인 상속 관계가 없는 형제 클래스라면 Compile Error다.

반면:

```java
Animal animal = new Dog();
Cat cat = (Cat) animal;
```

은 Compile은 가능할 수 있지만 실제 객체가 Dog이므로 Runtime `ClassCastException`이 발생한다.

## 8. 다단계 상속

```text
Object
  ↑
Animal
  ↑
Mammal
  ↑
Dog
```

Dog는 상위 타입으로 자동 Upcasting할 수 있다.

```java
Dog dog = new Dog();
Mammal mammal = dog;
Animal animal = dog;
Object object = dog;
```

전부 같은 객체를 바라본다.

## 9. `instanceof`

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.bark();
}
```

Downcasting 전에 해당 타입으로 안전하게 취급 가능한지 검사할 수 있다.

### Pattern Matching

```java
if (animal instanceof Dog dog) {
    dog.bark();
}
```

검사와 변수 생성을 한 번에 처리한다.

## 10. `null instanceof`

```java
Animal animal = null;

System.out.println(animal instanceof Dog);
```

결과:

```text
false
```

`NullPointerException`은 발생하지 않는다.

## 11. Casting은 실제 객체를 변경하지 않는다

```java
Parent parent = new Child();

((Parent) parent).print();
```

`print()`가 override되어 있다면 `Child.print()`가 실행된다.

`(Parent)`로 cast해도 실제 객체는 여전히 Child이기 때문이다.

> Casting은 reference의 타입 관점을 변경하지 실제 객체의 타입을 변경하지 않는다.

부모 구현을 명시적으로 호출하려면 casting이 아니라 자식 내부에서 `super.print()`를 사용한다.

## 12. `instanceof`가 필요한 경우

특정 자식에만 존재하는 고유 기능이라면 사용할 수 있다.

```java
if (animal instanceof Dog dog) {
    dog.bark();
}
```

하지만 자식 종류마다 분기가 계속 늘어난다면 다형성으로 해결할 수 있는지 검토한다.

## 13. `instanceof` 대신 Polymorphism

```java
class Payment {
    void pay() {}
}

class CardPayment extends Payment {
    @Override
    void pay() {
        System.out.println("Card Payment");
    }
}

class BankTransfer extends Payment {
    @Override
    void pay() {
        System.out.println("Bank Transfer");
    }
}
```

```java
static void process(Payment payment) {
    payment.pay();
}
```

```java
process(new CardPayment());
process(new BankTransfer());
```

결과:

```text
Card Payment
Bank Transfer
```

`process()`는 실제 결제 종류를 알 필요가 없다.

이것이 부모 타입을 사용하는 중요한 이유다.

## 14. Day 3 + Day 4 통합 판단표

| 대상 | 결정 기준 |
|---|---|
| Instance Field | Reference Type |
| Static Field | Reference Type |
| Static Method | Reference Type |
| Overloaded Method | Compile-time Type |
| Overridden Instance Method | Actual Object Type |
| Downcasting 성공 여부 | Actual Object Type |
| `instanceof` 결과 | Actual Object Type |

## 15. Day 4에서 특히 기억할 함정

1. Upcasting한다고 실제 객체가 부모 객체로 바뀌지 않는다.
2. Downcasting한다고 새 객체가 생성되지 않는다.
3. Parent reference에서는 Child 고유 method를 바로 호출할 수 없다.
4. Parent -> Child에는 명시적 cast가 필요하다.
5. Cast가 Compile된다고 Runtime cast까지 성공한다는 의미는 아니다.
6. `Animal a = new Cat(); Dog d = (Dog) a;` → Runtime `ClassCastException`.
7. `Dog d = new Dog(); Cat c = (Cat) d;` → Compile Error.
8. `null instanceof Dog` → `false`, NPE 없음.
9. Parent로 cast해도 실제 객체가 Child이면 overridden method는 Child 구현이 실행된다.
10. `instanceof` 분기가 계속 늘어나면 공통 method + overriding을 검토한다.

## 16. 빠른 복습 문제

1. `Animal animal = new Dog(); animal.sound();`에서 override되어 있다면 누구의 `sound()`가 실행되는가?
2. `Animal animal = new Dog(); animal.bark();`는 정상인가?
3. `Animal animal = new Dog(); Dog dog = (Dog) animal;`은 정상인가?
4. `Animal animal = new Cat(); Dog dog = (Dog) animal;`의 결과는?
5. `Dog dog = new Dog(); Cat cat = (Cat) dog;`의 결과는?
6. `null instanceof Dog`의 결과는?
7. Upcasting/Downcasting으로 실제 객체가 새로 생성되거나 변경되는가?
8. `instanceof` 분기가 자식 타입마다 계속 증가하면 어떤 설계를 검토해야 하는가?

### 정답

1. `Dog.sound()`
2. Compile Error. Animal에 `bark()`가 없다.
3. 정상.
4. Runtime `ClassCastException`.
5. Compile Error.
6. `false`.
7. 아니다. reference의 타입 관점만 달라진다.
8. 부모 공통 method와 자식 overriding을 이용한 다형성 구조.
