package Daou.Assignment.MockExam1.Q5;

// TODO 14: Identifiable<Long> 구현
public class Member implements Identifiable<Long> {

    private final Long id;
    private final String name;

    public Member(Long id, String name) {
        // TODO 15: id, name 유효성 검증
        // id는 null이면 안 된다.
        // name은 null 또는 공백이면 안 된다.
        if (id == null) {
            throw new IllegalArgumentException(
                    "id must not be null"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "name must not be null"
            );
        }

        this.id = id;
        this.name = name;
    }

    // TODO 16: getId() 구현
    @Override
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }
}
