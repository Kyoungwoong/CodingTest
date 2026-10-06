package Daou.Assignment.Day4;

public class Day4Review {
    public static void main(String[] args) {
        Animal animal = new Dog(); // Upcasting
        animal.sound();            // Dog Sound

        Dog dog = (Dog) animal;    // Downcasting
        System.out.println(animal == dog); // true
        dog.bark();

        check(new Dog());
        check(new Cat());
        check(null);

        Payment[] payments = {new CardPayment(), new BankTransfer()};
        for (Payment payment : payments) {
            process(payment);
        }
    }

    static void check(Animal animal) {
        if (animal instanceof Dog dog) {
            dog.bark();
        } else if (animal instanceof Cat cat) {
            cat.meow();
        } else {
            System.out.println("Not Dog/Cat");
        }
    }

    static void process(Payment payment) {
        // instanceof / casting 없이 다형성 사용
        payment.pay();
    }
}

class Animal {
    void sound() { System.out.println("Animal Sound"); }
}

class Dog extends Animal {
    @Override
    void sound() { System.out.println("Dog Sound"); }
    void bark() { System.out.println("Dog Bark"); }
}

class Cat extends Animal {
    @Override
    void sound() { System.out.println("Cat Sound"); }
    void meow() { System.out.println("Cat Meow"); }
}

class Payment {
    void pay() { System.out.println("Payment"); }
}

class CardPayment extends Payment {
    @Override
    void pay() { System.out.println("Card Payment"); }
}

class BankTransfer extends Payment {
    @Override
    void pay() { System.out.println("Bank Transfer"); }
}

/*
Day 4 핵심

1. Child -> Parent
   Upcasting / 자동

2. Parent -> Child
   Downcasting / 명시적 cast 필요

3. Animal animal = new Dog();
   - Reference Type: Animal
   - Actual Object Type: Dog

4. 호출 가능한 member
   -> Reference Type 기준

5. Overridden instance method
   -> Actual Object Type 기준

6. 잘못된 Downcasting
   Animal a = new Cat();
   Dog d = (Dog) a;
   -> Compile O / Runtime ClassCastException

7. 형제 클래스 직접 cast
   Dog d = new Dog();
   Cat c = (Cat) d;
   -> Compile Error

8. null instanceof Dog
   -> false / NPE 없음

9. Casting은 실제 객체를 바꾸지 않는다.

10. instanceof 분기가 계속 늘어난다면
    공통 method + overriding으로 해결 가능한지 검토한다.
*/
