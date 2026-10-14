package Daou.Assignment.MockExam4.Q4;

import java.util.*;

public class CourseManager {

    private final Map<String, Set<String>> courseses = new HashMap<>();

    public boolean enroll(String courseId, String studentId) {
        if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("courseId must not be null or empty");
        }

        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId must not be null or empty");
        }
        return courseses.computeIfAbsent(courseId, k -> new HashSet<>()).add(studentId);
    }

    public boolean withdraw(String courseId, String studentId) {
        if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("courseId must not be null or empty");
        }

        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId must not be null or empty");
        }

        Set<String> students = courseses.get(courseId);

        if (students == null) {
            return false;
        }

        return students.remove(studentId);
    }

    public Set<String> getStudents(String courseId) {
    if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("Invalid courseId");
        }

        return new HashSet<>(
            courseses.getOrDefault(courseId, Set.of())
        );
    }

    public Set<String> getCommonStudents(String courseA, String courseB) {
        Set<String> result = new HashSet<>(getStudents(courseA));
        result.retainAll(getStudents(courseB));
        return result;
    }

    public Set<String> getOnlyStudents(String courseA, String courseB) {
        Set<String> result = new HashSet<>(getStudents(courseA));
        result.removeAll(getStudents(courseB));
        return result;
    }

    public Set<String> getAllStudents() {
        Set<String> result = new HashSet<>();

        for (Set<String> students : courseses.values()) {
            result.addAll(students);
        }

        return result;
    }

    public boolean isEnrolled(
            String courseId, String studentId) {
        if (courseId == null || courseId.isBlank()) {
            throw new IllegalArgumentException("courseId must not be null or empty");
        }

        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("studentId must not be null or empty");
        }

        return courseses.getOrDefault(courseId, Set.of())
                .contains(studentId);
    }
}