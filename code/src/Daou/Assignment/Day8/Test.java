package Daou.Assignment.Day8;

import java.util.ArrayList;
import java.util.List;

class Animal {

}

class Dog extends Animal {

}

class Pudel extends Dog {

}

class Cat extends Animal {

}

public class Test {
    public static void main(String[] args) {
        // Dog 또는 Dog의 어떤 하위 타입의 리스트
        /**
         * ? extends Dog는 Dog의 하위 쪽입니다

            ? extends Dog는 "Dog를 상속하는 어떤 타입(?)"이라는 뜻이에요. 
            ? extends Dog라는 글자 그대로 "?가 Dog를 extends한다"로 읽으면 됩니다.
            
            List<? extends Dog>는 "Dog나 Pudel을 넣을 수 있는 리스트"가 아니라, 
            "이 리스트는 List<Dog>일 수도, List<Pudel>일 수도 있다
         */
        List<? extends Dog> a = new ArrayList<>();
        a.add(new Animal());
        a.add(new Dog());
        a.add(new Pudel());

        // Dog 또는 Dog의 어떤 상위 타입의 리스트
        List<? super Dog> b = new ArrayList<>();
        b.add(new Dog());    // OK
        b.add(new Pudel());  // OK (Pudel은 Dog니까)
        b.add(new Animal()); // 에러: Dog의 상위 타입은 넣을 수 없음
    }
}
