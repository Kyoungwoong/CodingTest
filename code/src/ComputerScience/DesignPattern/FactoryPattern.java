package ComputerScience.DesignPattern;

enum CoffeeType {
    LATTE(0),
    ESPRESSO(1),
    TEA(2);

    private int type;

    CoffeeType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}

abstract class Coffee {
    protected String name;

    public String getName() {
        return name;
    }
}

class Latte extends Coffee {
    public Latte() {
        super.name = "Latte";
    }
}

class Espresso extends Coffee {
    public Espresso() {
        super.name = "Espresso";
    }
}


public class FactoryPattern {
    static class CoffeeFactory {
        public static Coffee createCoffee(CoffeeType type) {
            switch (type) {
                case LATTE -> {
                    return new Latte();
                }
                case ESPRESSO -> {
                    return new Espresso();
                }
                default -> {
                    throw new IllegalArgumentException("Invalid coffee type: " + type);
                }
            }
        }
    }
    public static void main(String[] args) {
        Coffee brianCoffee = CoffeeFactory.createCoffee(CoffeeType.LATTE);
        Coffee jackCoffee = CoffeeFactory.createCoffee(CoffeeType.ESPRESSO);
//        Coffee mikeCoffee = CoffeeFactory.createCoffee(CoffeeType.TEA);

        System.out.println("brianCoffee = " + brianCoffee.getName());
        System.out.println("jackCoffee = " + jackCoffee.getName());
//        System.out.println("mikeCoffee = " + mikeCoffee.getName()); // IllegalArgumentException

    }

}