package Daou.Assignment.Day4;

class Animal {
    void eat() {
        System.out.println("Animal EAT");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog BARK");
    }
}

class Cat extends Animal {
    void meow() {
        System.out.println("Cat MEOW");
    }
}

public class Zoo {
    public static void main(String[] args) {
        process(new Dog());
        process(new Cat());
        process(new Animal());
    }

    private static void process(Animal animal) {
        if (animal instanceof Dog dog) {
            dog.bark();
        } else if (animal instanceof Cat cat) {
            cat.meow();
        } else {
            animal.eat();
        }
    }
}