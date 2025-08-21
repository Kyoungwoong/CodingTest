package ssafy;

public class Test {
    static class Parent {
        String x = "parent";

        void print() {
            System.out.println("I am parent");
        }
    }

    static class Child extends Parent {
        String x = "child";

        void print() {
            System.out.println("I am Child");
        }
    }

    public static void main(String[] args) {
        Parent person = new Child();
        System.out.println(person.x);
        person.print();
    }
}
