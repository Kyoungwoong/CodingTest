package Daou.Assignment.MockExam1.Q10;

public class PointHistory {

    private final String memberId;
    private final PointType type;
    private final long amount;
    private final long balanceAfter;

    public PointHistory(
            String memberId,
            PointType type,
            long amount,
            long balanceAfter
    ) {
        validateArgument(memberId, type, amount, balanceAfter);

        this.memberId = memberId;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    public String getMemberId() {
        return memberId;
    }

    public PointType getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }

    public long getBalanceAfter() {
        return balanceAfter;
    }

    private void validateArgument(String memberId,
                                  PointType type,
                                  long amount,
                                  long balanceAfter
    ) {
        if (memberId == null || memberId.isBlank()) {
            throw new IllegalArgumentException(
                    "member Id must not be null or blank"
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                    "type cannot be null"
            );
        }

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "amount has to positive number"
            );
        }

        if (balanceAfter < 0) {
            throw new IllegalArgumentException(
                    "balanceAfter value has to zero or positive number"
            );
        }
    }
}
