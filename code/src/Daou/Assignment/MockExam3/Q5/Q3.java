package Daou.Assignment.MockExam3.Q5;

import java.util.ArrayList;
import java.util.List;

public class Q3 {

    static void process(List<? extends Number> numbers) {
        numbers.add(10);
        System.out.println(numbers.get(0));
    }

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(5);

        process(values);
    }
}

// 컴팡리 불가
// numbers에는 Number를 상속한 객체가 들어갈 수 있지만, 
// Number를 상속한 어떤 것이 들어갈지 모르기에 컴파일 에러가 발생한다.
// List<? extends Integer> numbers

// -----------------------------------------
/**
 * ANSWER

기억할 규칙은 PECS(Producer Extends, Consumer Super) 야. 
값을 꺼내는 용도라면 extends, 값을 넣는 용도라면 super를 우선 고려해.

 */