package Daou.Assignment.Day3;

class Parent {

    int value = 10;

    Parent() {
        print();
    }

    void print() {
        System.out.println(value);
    }
}

class Child extends Parent {

    int value = 20;

    @Override
    void print() {
        System.out.println(value);
    }
}

public class Constructor {
    public static void main(String[] args) {
        new Child();
    }
}

/**
new Child()
↓
Parent 부분부터 초기화
Parent.value = 10
↓
Parent constructor
↓
print()

Child.print()
int value = 20;의 초기화는 Parent constructor가 끝난 다음이야.
현재 시점에는 Child field initializer가 아직 실행되지 않았다.
field는 먼저 기본값을 가지므로:
Child.value = 0

 */