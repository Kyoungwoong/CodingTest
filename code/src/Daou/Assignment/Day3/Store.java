package Daou.Assignment.Day3;


class Employee {
    private String name;
    protected int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return this.name;
    }

    public int getSalary() {
        return this.salary;
    }

    public void work() {
        System.out.println("Employee Working");
    }
}

class Manager extends Employee {
    private int bonus;
    
    public Manager(String name, int salary, int bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public int getTotalSalary() {
        return getSalary() + this.bonus;
    }

    @Override 
    public void work() {
        super.work();
        System.out.println("Manager Working");
    }
}

public class Store {
    public static void main(String[] args) {
        Employee employee = new Manager("Kim", 5000, 1000);

        System.out.println(employee.getName());   // Kim
        System.out.println(employee.getSalary()); // 5000

        employee.work();
        // Employee Working
        // Manager Working
    }
}
