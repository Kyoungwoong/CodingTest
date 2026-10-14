package Daou.Assignment.MockExam4.Q5;

import java.util.List;

public class Q5B {
    static void process(List<? extends Number> source,
                    List<? super Integer> target) {

        Number number = source.get(0);

        target.add(100);

        // A
        source.add(200);

        // B
        Integer value = target.get(0);
    }
}
