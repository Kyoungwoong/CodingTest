package Daou.Assignment.MockExam4.Q3;

import java.util.*;

public class Q3D {

    static void addNumbers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
    }

    static int sumNumbers(List<? extends Number> list) {
        int sum = 0;

        for (Number n : list) {
            sum += n.intValue();
        }

        return sum;
    }

    public static void main(String[] args) {
        List<Number> numbers = new ArrayList<>();

        addNumbers(numbers);

        System.out.println(sumNumbers(numbers));
        System.out.println(numbers);
    }
}