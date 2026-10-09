package Daou.Assignment.Day4;

class DevelopmentAnimal {

    void act() {
        eat();
    }

    void eat() {
        System.out.println("Animal EAT");
    }
}

class DevelopmentDog extends DevelopmentAnimal {

    @Override
    void act() {
        bark();
    }

    void bark() {
        System.out.println("Dog BARK");
    }
}

class DevelopmentCat extends DevelopmentAnimal {

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
        process(new DevelopmentDog());
        process(new DevelopmentCat());
        process(new DevelopmentAnimal());
    }

    private static void process(DevelopmentAnimal animal) {
        animal.act();
    }
}
