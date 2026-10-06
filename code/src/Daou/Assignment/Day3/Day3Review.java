package Daou.Assignment.Day3;

public class Day3Review {
    public static void main(String[] args) {
        System.out.println("=== Field vs Overridden Method ===");
        ValueParent value = new ValueChild();
        System.out.println(value.number);       // 10
        System.out.println(value.getNumber()); // 20

        System.out.println("=== Static Method Hiding ===");
        StaticParent sp = new StaticChild();
        StaticChild sc = new StaticChild();
        sp.print(); // StaticParent
        sc.print(); // StaticChild

        System.out.println("=== Private Method ===");
        PrivateParent pp = new PrivateChild();
        pp.execute(); // Parent private

        System.out.println("=== Constructor + Dynamic Dispatch ===");
        new InitChild(); // 10, 0

        System.out.println("=== super.method() ===");
        WorkParent worker = new WorkChild();
        worker.work();
    }
}

class ValueParent {
    int number = 10;
    int getNumber() { return number; }
}

class ValueChild extends ValueParent {
    int number = 20;
    @Override
    int getNumber() { return this.number; }
}

class StaticParent {
    static void print() { System.out.println("StaticParent"); }
}

class StaticChild extends StaticParent {
    // @Override 불가: overriding이 아니라 Method Hiding
    static void print() { System.out.println("StaticChild"); }
}

class PrivateParent {
    private void print() { System.out.println("Parent private"); }
    void execute() { print(); }
}

class PrivateChild extends PrivateParent {
    // Parent의 private print()를 override하는 것이 아님
    void print() { System.out.println("Child"); }
}

class InitParent {
    int value = 10;
    InitParent() { print(); }
    void print() { System.out.println(value); }
}

class InitChild extends InitParent {
    int value = 20;

    @Override
    void print() {
        System.out.println(super.value); // 10
        System.out.println(this.value);  // Parent 생성자 시점에는 아직 0
    }
}

class WorkParent {
    void work() { System.out.println("Parent Working"); }
}

class WorkChild extends WorkParent {
    @Override
    void work() {
        super.work();
        System.out.println("Child Working");
    }
}

/*
핵심 암기:
- instance field: 참조 타입 기준
- static field: 참조 타입 기준
- static method: 참조/클래스 타입 기준 (Method Hiding)
- overridden instance method: 실제 객체 타입 기준
- private method: overriding 대상 아님
- final method: overriding 불가
- super.field: 부모 field
- super.method(): 부모 method 구현
- super(...): 부모 constructor
- Parent 생성자에서 override 가능한 method 호출 시 Child field가 아직 기본값일 수 있음
*/
