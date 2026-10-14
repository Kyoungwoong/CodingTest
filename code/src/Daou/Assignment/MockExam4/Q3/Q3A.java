package Daou.Assignment.MockExam4.Q3;

class Parent {
    void print(Object obj) {
        System.out.println("Parent-Object");
    }

    void print(String str) {
        System.out.println("Parent-String");
    }
}

class Child extends Parent {
    @Override
    void print(Object obj) {
        System.out.println("Child-Object");
    }

    void print(Integer num) {
        System.out.println("Child-Integer");
    }
}

public class Q3A {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();

        p.print(10);
        p.print("Hello");
        c.print(10);
        c.print((Object) "Hello");
    }
}