package Daou.Assignment.Day11;

import javax.swing.text.DateFormatter;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

class Employee {

    private final int id;
    private final String name;
    private final String department;
    private final LocalDate joinDate;

    public Employee(int id, String name,
                    String department, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.joinDate = joinDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public LocalDate getJoinDate() { return joinDate; }

    @Override
    public String toString() {
        return id + " | " + name + " | "
                + department + " | " + joinDate;
    }
}

public class EmployeeAnalyzer {

    // 1. CSV 문자열을 Employee 객체로 변환
    public static Employee parseEmployee(String line) {
        String[] Info = line.split(",");
        return new Employee(
                Integer.parseInt(Info[0]),
                Info[1],
                Info[2],
                LocalDate.parse(Info[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );
    }

    // 2. 근속일수 계산
    public static long calculateWorkDays(
            Employee employee, LocalDate today) {
        return ChronoUnit.DAYS.between(
                employee.getJoinDate(), today);
    }

    // 3. 근속기간 계산
    public static Period calculateWorkPeriod(
            Employee employee, LocalDate today) {
        return Period.between(employee.getJoinDate(), today);
    }

    // 4. 입사일 오름차순 정렬
    // 원본 배열은 변경하지 않고 새 배열 반환
    public static Employee[] sortByJoinDate(Employee[] employees) {
        Employee[] result = employees.clone();
        Arrays.sort(
                result,
                Comparator.comparing(Employee::getJoinDate)
        );
        return result;
    }

    // 5. 부서별 인원 집계
    // Stream 사용하지 않고 Map으로 구현
    public static Map<String, Integer> countByDepartment(
            Employee[] employees) {

        Map<String, Integer> result = new HashMap<>();

        for (Employee employee : employees) {
            String department = employee.getDepartment();

            result.merge(department, 1, Integer::sum);
        }

        return result;
    }

    // 6. 입사일 포맷팅
    public static String formatJoinDate(Employee employee) {

        LocalDate joinDate = employee.getJoinDate();

        return String.format("%d년 %02d월 %02d일",
                joinDate.getYear(),
                joinDate.getMonthValue(),
                joinDate.getDayOfMonth()
        );
    }

    // 7. 직원 배열 복사
    public static Employee[] copyEmployees(Employee[] employees) {
        return employees.clone();
    }

    public static void main(String[] args) {

        String[] rawData = {
                "1001,Kim,IT,2020-03-15",
                "1002,Lee,HR,2022-07-01",
                "1003,Park,IT,2019-11-20",
                "1004,Choi,Finance,2024-01-10",
                "1005,Jung,HR,2021-06-05"
        };

        LocalDate today = LocalDate.of(2026, 10, 9);

        // TODO
        // 1. rawData를 Employee[]로 변환
        // 2. 입사일 오름차순 정렬 후 출력
        // 3. 직원별 근속일수와 근속기간 출력
        // 4. 부서별 인원 출력
        // 5. 날짜 포맷팅 출력
        // 6. 배열 복사 후 원본과 참조 비교
    }
}