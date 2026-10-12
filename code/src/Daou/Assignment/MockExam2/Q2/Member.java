package Daou.Assignment.MockExam2.Q2;

public class Member implements Identifiable<String> {

    private final String id;
    private final String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}