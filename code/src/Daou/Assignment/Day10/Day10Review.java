import java.util.*;
import java.util.stream.*;

/** Day 10 review: Stream API and Optional. Java 17+. */
public class Day10Review {
    record Member(long id, String name, String department, int age, int score) {}

    static List<Member> findAdults(List<Member> members) {
        return members.stream().filter(m -> m.age() >= 20).toList();
    }
    static List<Member> findTopScorers(List<Member> members) {
        return members.stream().sorted(Comparator.comparingInt(Member::score).reversed()).limit(3).toList();
    }
    static Map<String, Double> getAverageScoreByDepartment(List<Member> members) {
        return members.stream().collect(Collectors.groupingBy(Member::department, Collectors.averagingInt(Member::score)));
    }
    static Map<String, Long> countMembersByDepartment(List<Member> members) {
        return members.stream().collect(Collectors.groupingBy(Member::department, Collectors.counting()));
    }
    static Optional<Member> findMemberById(List<Member> members, Long id) {
        Objects.requireNonNull(id, "id must not be null");
        return members.stream().filter(m -> m.id() == id).findFirst();
    }
    static List<String> getAllNames(List<Member> members) {
        return members.stream().map(Member::name).toList();
    }
    static int getTotalScore(List<Member> members) {
        return members.stream().map(Member::score).reduce(0, Integer::sum);
    }
    static void check(boolean ok, String message) {
        if (!ok) throw new AssertionError(message);
    }
    public static void main(String[] args) {
        List<Member> members = List.of(
            new Member(1, "Kim", "IT", 30, 90),
            new Member(2, "Lee", "HR", 25, 80),
            new Member(3, "Park", "IT", 35, 95),
            new Member(4, "Choi", "HR", 19, 85),
            new Member(5, "Jung", "IT", 28, 100)
        );
        check(findAdults(members).size() == 4, "adult count");
        check(getAllNames(findTopScorers(members)).equals(List.of("Jung", "Park", "Kim")), "top scorers");
        Map<String, Double> avg = getAverageScoreByDepartment(members);
        check(avg.get("IT") == 95.0 && avg.get("HR") == 82.5, "averages");
        check(countMembersByDepartment(members).get("IT") == 3L, "IT count");
        check(countMembersByDepartment(members).get("HR") == 2L, "HR count");
        check(findMemberById(members, 3L).orElseThrow().name().equals("Park"), "find existing");
        check(findMemberById(members, 99L).isEmpty(), "find missing");
        check(getAllNames(members).equals(List.of("Kim", "Lee", "Park", "Choi", "Jung")), "names");
        check(getTotalScore(members) == 450, "total score");
        check(members.get(0).name().equals("Kim"), "original order unchanged");
        System.out.println("PASS: all Day 10 review checks");
        System.out.println("Top scorers: " + getAllNames(findTopScorers(members)));
        System.out.println("Averages: " + avg);
        System.out.println("Total score: " + getTotalScore(members));

        List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));
        System.out.println("flatMap: " + nested.stream().flatMap(List::stream).toList());
        System.out.println("partitioningBy: " + members.stream().collect(Collectors.partitioningBy(m -> m.score() >= 90)).get(true).size());
        System.out.println("Optional: " + Optional.of("Java").orElseGet(() -> "Unknown"));
    }
}
