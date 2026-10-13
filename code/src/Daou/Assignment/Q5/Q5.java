package Daou.Assignment.Q5;

import java.util.HashSet;
import java.util.Set;

class Employee {
    private final int id;
    private final String name;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee other)) return false;
        return this.id == other.id;
    }
}

public class Q5 {
    public static void main(String[] args) {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee(1, "Kim"));
        employees.add(new Employee(1, "Lee"));

        System.out.println(employees.size());
    }
}

// 컴파일가능
// no
// hashcode를 오버라이딩하지 않았따.