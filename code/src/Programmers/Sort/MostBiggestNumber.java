package Programmers.Sort;

import java.util.Arrays;

public class MostBiggestNumber {
    private static int[] numbers = {6, 10, 2}; // "6210"
//    private static int[] numbers = {3, 30, 34, 5, 9}; // "9534330"

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        String[] strNumbers = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        Arrays.sort(strNumbers, (a, b) -> (b + a).compareTo(a + b));

        for (String num : strNumbers) {
            sb.append(num);
        }

        if (sb.toString().charAt(0) == '0') {
            System.out.println("0");
        }
        System.out.println("sb.toString() = " + sb.toString());

    }
}
