package Daou.Assignment.MockExam1.Q9;

import java.util.*;

public class Main {

    public static void copyNumbers(
            List<? extends Number> source,
            List<? super Integer> target
    ) {
        for (Number number : source) {
            target.add(number.intValue());
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);

        List<Number> numbers = new ArrayList<>();
        copyNumbers(integers, numbers);

        List rawList = integers;
        rawList.add("30");

        System.out.println(numbers);

        try {
            for (Integer value : integers) {
                System.out.println(value);
            }
        } catch (ClassCastException e) {
            System.out.println("TYPE ERROR");
        }
    }
}