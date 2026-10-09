package Daou.Assignment.Day12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

class Employee {
    private final int id;
    private final String name;
    private final String department;
    private final int salary;

    public Employee(int id, String name, String department, int salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getSalary() { return salary; }
}

public class EmployeeFileProcessor {

    // 1. 샘플 CSV 파일 생성
    public static void writeSampleFile(Path path) throws IOException {

        List<String> lines = List.of(
                "1001,Kim,IT,5000",
                "1002,Lee,HR,4000",
                "1003,Park,IT,6000",
                "1004,Choi,Finance,5500",
                "1005,Jung,HR,4500"
        );

        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    // 2. CSV 파일 읽기
    public static List<Employee> readEmployees(Path path)
            throws IOException {

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(
                path, StandardCharsets.UTF_8)) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String department = parts[2];
                int salary = Integer.parseInt(parts[3]);

                Employee employee = new Employee(
                        id, name, department, salary
                );

                employees.add(employee);
            }
        }

        return employees;
    }

    // 3. 평균 급여 계산
    public static double calculateAverageSalary(
            List<Employee> employees) {

        return employees.stream()
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    // 4. 부서별 인원 집계
    public static Map<String, Integer> countByDepartment(
            List<Employee> employees) {

        Map<String, Integer> result = new HashMap<>();

        for (Employee employee : employees) {

            String department = employee.getDepartment();

            result.merge(department, 1, Integer::sum);
        }

        return result;
    }

    // 5. 분석 결과 파일 저장
    public static void writeReport(
            Path path,
            List<Employee> employees
    ) throws IOException {

        double average = calculateAverageSalary(employees);

        Map<String, Integer> departmentCount =
                countByDepartment(employees);

        try (BufferedWriter bw = Files.newBufferedWriter(
                path, StandardCharsets.UTF_8)) {

            bw.write("=== Employee Report ===");
            bw.newLine();

            bw.write("Total Employees: " + employees.size());
            bw.newLine();

            bw.write("Average Salary: " + average);
            bw.newLine();
            bw.newLine();

            bw.write("Department Count:");
            bw.newLine();

            for (Map.Entry<String, Integer> entry
                    : departmentCount.entrySet()) {

                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
        }
    }

    public static void main(String[] args) {

        Path input = Path.of("employees.csv");
        Path output = Path.of("report.txt");

        try {
            // 1. CSV 파일 생성
            writeSampleFile(input);

            // 2. CSV 파일 읽기
            List<Employee> employees = readEmployees(input);

            // 3. 평균 급여 계산
            double average = calculateAverageSalary(employees);

            // 4. 부서별 인원 집계
            Map<String, Integer> counts =
                    countByDepartment(employees);

            // 5. 결과 파일 저장
            writeReport(output, employees);

            System.out.println("직원 수: " + employees.size());
            System.out.println("평균 급여: " + average);
            System.out.println("부서별 인원: " + counts);
            System.out.println("보고서 저장 완료: " + output);

        } catch (IOException e) {
            System.err.println("파일 처리 오류: " + e.getMessage());
        }
    }
}
