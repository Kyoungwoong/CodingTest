package Daou.Assignment.MockExam4.Q3;

import java.util.*;

class Product {
    private final String id;

    Product(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product other)) {
            return false;
        }
        return id.equals(other.id);
    }

    // hashCode()는 오버라이딩하지 않음
}

public class Q3C {
    public static void main(String[] args) {
        Map<Product, Integer> map = new HashMap<>();

        Product p1 = new Product("P100");
        Product p2 = new Product("P100");

        map.put(p1, 100);
        map.put(p2, 200);

        System.out.println(map.size());
        System.out.println(map.get(p1));
        System.out.println(p1.equals(p2));
    }
}