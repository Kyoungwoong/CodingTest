package Daou.Assignment.MockExam4.Q8;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeAnalyzer {

    private final List<Employee> employees;

    public EmployeeAnalyzer(List<Employee> employees) {
        this.employees = new ArrayList<>(employees);
    }

    // Q8-1
    public List<String> getActiveEmployeeNames() {
        return employees.stream().filter(e -> e.active() == true).map(Employee::name).toList();
    }

    // Q8-2
    public long getTotalSalary(String department) {
        return employees.stream().collect(Collectors.toMap(Employee::department,
                                                            Employee::salary,
                                                            Math::addExact
                                                            )).getOrDefault(department, 0);
    }

    // Q8-3
    public Map<String, Long> getEmployeeCountByDepartment() {
        // TODO
    }

    // Q8-4
    public List<Employee> getTopSalaryEmployees(int limit) {
        // TODO
    }
}
