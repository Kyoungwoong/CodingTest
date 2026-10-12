package Daou.Assignment.MockExam3.Q1;

public enum MemberType {
    REGULAR(3),
    VIP(7),
    STAFF(10);

    private final int availableCount;

    MemberType(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getAvailableCount() {
        return availableCount;
    }
}