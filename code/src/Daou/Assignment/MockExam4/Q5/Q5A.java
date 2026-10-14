package Daou.Assignment.MockExam4.Q5;

import java.util.*;

public class Q5A {

    public static <T> T getFirst(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return list.get(0);
    }
}