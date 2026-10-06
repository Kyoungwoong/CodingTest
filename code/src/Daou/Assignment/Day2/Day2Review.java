package Daou.Assignment.Day2;

/**
 * Day 2 Review
 *
 * 핵심 주제
 * - static field / method
 * - static initializer
 * - final variable / reference
 * - blank final
 * - static final
 * - method hiding
 * - field hiding
 */
public class Day2Review {

    public static void main(String[] args) {

        // 1. static field는 모든 객체가 공유
        User a = new User("Kim");
        User b = new User("Lee");

        System.out.println(User.getCount()); // 2

        // 2. instance field는 객체마다 독립
        a.setAge(20);
        b.setAge(30);

        System.out.println(a.getAge()); // 20
        System.out.println(b.getAge()); // 30

        // 3. final primitive
        final int number = 10;

        // number = 20; // Compile Error

        // 4. final reference
        final User user = new User("Park");

        // 객체 내부 변경 가능
        user.setAge(40);

        // reference 재할당 불가능
        // user = new User("Choi"); // Compile Error

        // 5. final array
        final int[] numbers = {1, 2, 3};

        numbers[0] = 100; // O

        // numbers = new int[]{4, 5, 6}; // Compile Error

        // 6. static final
        System.out.println(User.MAX_AGE);

        // 7. static method hiding
        Parent parent = new Child();
        Child child = new Child();

        parent.staticPrint(); // Parent
        child.staticPrint();  // Child

        // 8. instance method overriding
        parent.instancePrint(); // Child

        // 9. field hiding
        System.out.println(parent.value); // 10
        System.out.println(child.value);  // 20

        // 10. overridden method
        System.out.println(parent.getValue()); // 20
    }
}


class User {

    public static final int MAX_AGE = 150;

    private static int count = 0;

    private final String name;

    private int age;

    static {
        System.out.println("User class initialized");
    }

    {
        // 객체가 생성될 때마다 실행
        System.out.println("User instance initialized");
    }

    public User(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


class Parent {

    static int staticValue = 10;

    int value = 10;

    static void staticPrint() {
        System.out.println("Parent");
    }

    void instancePrint() {
        System.out.println("Parent");
    }

    int getValue() {
        return value;
    }
}


class Child extends Parent {

    static int staticValue = 20;

    int value = 20;

    // static method는 overriding이 아니라 hiding
    static void staticPrint() {
        System.out.println("Child");
    }

    @Override
    void instancePrint() {
        System.out.println("Child");
    }

    @Override
    int getValue() {
        return value;
    }
}
