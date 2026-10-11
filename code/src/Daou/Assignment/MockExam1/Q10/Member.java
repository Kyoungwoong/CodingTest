package Daou.Assignment.MockExam1.Q10;

public class Member {

    private final String id;
    private final String name;
    private long points;
    private static final int ALLOWED_AMOUNT = 0;

    public Member(String id, String name, long points) {
        validateConstructor(id, name, points);

        this.id = id;
        this.name = name;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPoints() {
        return points;
    }

    public void earn(long amount) {
        validateAmount(amount);
        if (points > Long.MAX_VALUE - amount) {
            throw new ArithmeticException(
                    "points overflow"
            );
        }
        points += amount;
    }

    public void use(long amount) {
        validateAmount(amount);
        if (points < amount) {
            throw new IllegalStateException(
                    "point less than amount"
            );
        }
        points -= amount;
    }

    private void validateConstructor(String id, String name, long points) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(
                    "id must not be null"
            );
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "name must not be null"
            );
        }

        if (points < 0) {
            throw new IllegalArgumentException(
                    "point must be zero or positive number"
            );
        }
    }

    private void validateAmount(long amount) {
        if (amount <= ALLOWED_AMOUNT) {
            throw new IllegalArgumentException(
                    "amount post be positive number"
            );
        }
    }
}