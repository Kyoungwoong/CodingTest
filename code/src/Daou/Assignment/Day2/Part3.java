package Daou.Assignment.Day2;

class Part3Parent {
    int value = 10;

    int getValue() {
        return value;
    }

    void print() {
        System.out.println("Parent");
    }

    static void staticPrint() {
        System.out.println("Static Parent");
    }
}

class Part3Child extends Part3Parent {

    int value = 20;

    /**    (non-Javadoc)
     * field는 overriding되지 않는다.
     * @see Daou.Assignment.Day2.Parent#getValue()
     */
    @Override
    int getValue() {
        return value;
    }

    @Override
    void print() {
        System.out.println("Child");
    }

    /**
     * static 메서드는 오버라이딩(Overriding)이 불가능해. 따라서 @Override를 붙이면 Compile Error가 발생해.
     * static 메서드는 오버라이딩이 아니라 Method Hiding
     */
    static void staticPrint() {
        System.out.println("Static Child");
    }
}

public class Part3 {
    public static void main(String[] args) {
        Part3Parent parent = new Part3Child();
        parent.print();
        parent.staticPrint();
        System.out.println(parent.value);
        System.out.println(parent.getValue());

        Part3Child child = new Part3Child();
        child.staticPrint();
    }
}
