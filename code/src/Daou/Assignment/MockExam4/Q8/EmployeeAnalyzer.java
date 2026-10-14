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
    if (department == null || department.isBlank()) {
        throw new IllegalArgumentException("Invalid department");
    }

    return employees.stream()
        .filter(e -> department.equals(e.department()))
        .mapToLong(Employee::salary)
        .reduce(0L, Math::addExact);
}

    // Q8-3
    public Map<String, Long> getEmployeeCountByDepartment() {
        return employees.stream()
                    .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.counting()
                    ));
    }

    // Q8-4
    public List<Employee> getTopSalaryEmployees(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("limit must be positive");
        }
        return employees.stream()
                        .sorted(
                            Comparator.comparingInt(Employee::salary)
                                .reversed()
                                .thenComparing(Employee::name)
                        )
                        .limit(limit)
                        .toList();
    }
}
