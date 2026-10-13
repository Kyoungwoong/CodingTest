package Daou.Assignment.MockExam3.Q5;

class Animal {
    String name = "Animal";

    static void sound() {
        System.out.println("Animal-static");
    }

    void move() {
        System.out.println("Animal-move");
    }
}

class Dog extends Animal {
    String name = "Dog";

    static void sound() {
        System.out.println("Dog-static");
    }

    @Override
    void move() {
        System.out.println("Dog-move");
    }
}

public class Q2 {
    public static void main(String[] args) {
        Animal a = new Dog();

        System.out.println(a.name);
        a.sound();
        a.move();
    }
}

// Animal
// Animal-static
// Dog-move
// 컴파일 가능.static 메소드는 클래스에 종속. 인스턴스 메소드나 필드 메소드는 오버라이딩 가능.
// 단 여기서 인스턴스 변수에 대해서는 Animal 객체이므로 Animal 반환

// -----------------------------------------
/**
 * ANSWER
Java에서 필드는 오버라이딩되지 않아. 필드는 hiding(숨김) 이 발생해.

 */