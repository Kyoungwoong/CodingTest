package Daou.Assignment.Day7;

public enum OrderStatus {
    READY("주문대기"), PAID("결제완료"), CANCELLED("주문취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isFinished() {
        return this == PAID || this == CANCELLED;
    }
}
