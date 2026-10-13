package Daou.Assignment.Q5;

class Parent {
    void print(Object value) {
        System.out.println("Parent-Object");
    }
}

class Child extends Parent {
    void print(String value) {
        System.out.println("Child-String");
    }

    @Override
    void print(Object value) {
        System.out.println("Child-Object");
    }
}

public class Q1 {
    public static void main(String[] args) {
        Parent p = new Child();
        Child c = new Child();

        p.print("Hello");
        c.print("Hello");
        c.print((Object) "Hello");
    }
}

// 컴파일 가능
// Child-Object, Parent 객체의 print(Object) 메소드의 오버라이딩 된 Child-Object
// Child-String, Child 객체의 print(String) 가장 구체적인 파라미터 바인딩
// Child-string, Child 객체의 print(String) 가장 구체적인 파라미터 바인딩

// ==============================
/**
 * ANSWER
 * 컴파일 시점: 참조 변수 타입과 인수의 컴파일 시점 타입을 기준으로 오버로드할 메서드를 결정한다.
 * 런타임: 결정된 메서드 시그니처에 대해 실제 객체의 오버라이딩 구현을 실행한다.
Child-Object
Child-String
Child-Object: 세 번째는 (Object)로 명시적 형변환을 했기 때문에 print(Object)가 선택돼. 실제 값이 String 객체라는 사실이 print(String)으로 선택을 바꾸지는 않아.
 */