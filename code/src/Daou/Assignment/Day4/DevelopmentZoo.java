package Daou.Assignment.Day4;

class Animal {

    void act() {
        eat();
    }

    void eat() {
        System.out.println("Animal EAT");
    }
}

class Dog extends Animal {

    @Override
    void act() {
        bark();
    }

    void bark() {
        System.out.println("Dog BARK");
    }
}

class Cat extends Animal {

    @Override
    void act() {
        meow();
    }

    void meow() {
        System.out.println("Cat MEOW");
    }
}

public class DevelopmentZoo {
    public static void main(String[] args) {
        process(new Dog());
        process(new Cat());
        process(new Animal());
    }

    private static void process(Animal animal) {
        animal.act();
    }
}
