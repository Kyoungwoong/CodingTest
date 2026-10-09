package Daou.Assignment.Day4;

class ZooAnimal {
    void eat() {
        System.out.println("Animal EAT");
    }
}

class ZooDog extends ZooAnimal {
    void bark() {
        System.out.println("Dog BARK");
    }
}

class ZooCat extends ZooAnimal {
    void meow() {
        System.out.println("Cat MEOW");
    }
}

public class Zoo {
    public static void main(String[] args) {
        process(new ZooDog());
        process(new ZooCat());
        process(new ZooAnimal());
    }

    private static void process(ZooAnimal animal) {
        if (animal instanceof ZooDog dog) {
            dog.bark();
        } else if (animal instanceof ZooCat cat) {
            cat.meow();
        } else {
            animal.eat();
        }
    }
}
