package Daou.Assignment.Day9;

import java.util.*;

class Member {
    private final long id;
    private final String name;
    private final int age;
    private final int score;

    // 생성자, getter, toString 구현

    public Member(long id, String name, int age, int score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}

public class MemberService {

    public static List<Member> sortByAge(List<Member> members) {
        List<Member> result = new ArrayList<>(members);

        result.sort(Comparator.comparingInt(Member::getAge));

        return result;
    }

    public static List<Member> sortByScoreDesc(List<Member> members) {
        List<Member> result = new ArrayList<>(members);

        result.sort(Comparator.comparingInt(Member::getScore).reversed());

        return result;
    }

    public static List<Member> sortByAgeAndName(List<Member> members) {
        List<Member> result = new ArrayList<>(members);

        result.sort(Comparator.comparing(Member::getAge)
                .thenComparing(Member::getName));

        // 개선
        result.sort(
                Comparator.comparingInt(Member::getAge)
                        .thenComparing(Member::getName)
        );

        return result;
    }

    public static List<Member> removeUnderAge(List<Member> members, int age) {
        List<Member> result = new ArrayList<>(members);

        result.removeIf(member -> member.getAge() < age);

        return result;
    }

    public static void printMembers(List<Member> members) {
        members.forEach(member -> {
            System.out.print(member + "\t");
        });
        System.out.println();

        // 개선
        // members.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Member> members = new ArrayList<>();

        members.add(new Member(1, "Kim", 30, 85));
        members.add(new Member(2, "Lee", 25, 95));
        members.add(new Member(3, "Park", 30, 90));
        members.add(new Member(4, "Choi", 20, 95));

        // 1. sortByAge
        printMembers(sortByAge(members));

        // 2. sortByScoreDesc
        printMembers(sortByScoreDesc(members));

        // 3. sortByAgeAndName
        printMembers(sortByAgeAndName(members));

        // 4. removeUnderAge
        printMembers(removeUnderAge(members, 29));
    }
}
