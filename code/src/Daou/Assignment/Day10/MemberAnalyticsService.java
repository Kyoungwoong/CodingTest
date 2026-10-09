package Daou.Assignment.Day10;

import java.util.*;
import java.util.stream.Collectors;

class Member {
    private final long id;
    private final String name;
    private final String department;
    private final int age;
    private final int score;

    // 생성자, getter, toString 구현

    public Member(long id, String name, String department, int age, int score) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.age = age;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getAge() {
        return age;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}

public class MemberAnalyticsService {
    public static List<Member> findAdults(List<Member> members) {
        return members.stream()
                .filter(m -> m.getAge() >= 20)
                .toList();
    }

    // 점수 내림차순으로 상위 3명 조회
    public static List<Member> findTopScorers(List<Member> members) {
        // 뭐를 정렬할지 가 없음
//        return members.stream()
//                .sorted(Collections.reverseOrder())
//                .limit(3)
//                .toList();
        return members.stream()
                .sorted(Comparator.comparingInt(Member::getScore).reversed())
                .limit(3)
                .toList();
    }

    // 부서별 평균 점수
    public static Map<String, Double> getAverageScoreByDepartment(List<Member> members) {
        return members.stream()
                .collect(Collectors.groupingBy(
                        Member::getDepartment,
                        Collectors.averagingInt(Member::getScore)
                ));
    }

    // 부서별 인원 수
    public static Map<String, Long> countMembersByDepartment(List<Member> members) {
        return members.stream()
                .collect(Collectors.groupingBy(
                        Member::getDepartment,
                        Collectors.counting()
                ));
    }

    // ID로 회원 검색
    public static Optional<Member> findMemberById(List<Member> members, Long id) {

        return members.stream()
                .filter(member -> member.getId() == id)
                .findFirst();
    }

    // 회원 이름만 추출
    public static List<String> getAllNames(List<Member> members) {
        return members.stream()
                .map(Member::getName)
                .toList();
    }

    // 전체 점수 합계
    public static int getTotalScore(List<Member> members) {
        return members.stream()
                .map(Member::getScore)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        List<Member> members = List.of(
                new Member(1, "Kim", "IT", 30, 90),
                new Member(2, "Lee", "HR", 25, 80),
                new Member(3, "Park", "IT", 35, 95),
                new Member(4, "Choi", "HR", 19, 85),
                new Member(5, "Jung", "IT", 28, 100)
        );

        // 1. 20세 이상 회원 조회
        System.out.println("=== 1. 성인 회원 ===");
        findAdults(members).forEach(System.out::println);

        // 2. 점수 상위 3명
        System.out.println("\n=== 2. 점수 상위 3명 ===");
        findTopScorers(members).forEach(System.out::println);

        // 3. 부서별 평균 점수
        System.out.println("\n=== 3. 부서별 평균 점수 ===");
        getAverageScoreByDepartment(members)
                .forEach((department, average) ->
                        System.out.println(department + " : " + average)
                );

        // 4. 부서별 인원수
        System.out.println("\n=== 4. 부서별 인원수 ===");
        countMembersByDepartment(members)
                .forEach((department, count) ->
                        System.out.println(department + " : " + count)
                );

        // 5. ID로 회원 검색
        System.out.println("\n=== 5. 회원 검색 ===");

        Optional<Member> found = findMemberById(members, 3L);
        System.out.println("ID 3 : " + found.orElse(null));

        Optional<Member> notFound = findMemberById(members, 99L);
        System.out.println("ID 99 : " + notFound.orElse(null));

        // 6. 회원 이름만 추출
        System.out.println("\n=== 6. 전체 회원 이름 ===");
        System.out.println(getAllNames(members));

        // 7. 전체 점수 합계
        System.out.println("\n=== 7. 전체 점수 합계 ===");
        System.out.println(getTotalScore(members));
    }
}
