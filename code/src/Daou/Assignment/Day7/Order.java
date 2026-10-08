package Daou.Assignment.Day7;

public class Order {
    private final long id;
    private String customer;
    private OrderStatus status;

    public Order(long id, String customer) {
        this.id = id;
        this.customer = customer;
        status = OrderStatus.READY;
    }

    public long getId() {
        return this.id;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void pay() {
        if (status == OrderStatus.READY) {
            status = OrderStatus.PAID;
            return;
        }
        throw new IllegalStateException("잘못된 결재 상태 변경입니다");
    }

    public void cancel() {
        if (status == OrderStatus.READY) {
            status = OrderStatus.CANCELLED;
            return;
        }
        throw new IllegalStateException("잘못된 취소 상태 변경입니다");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Order other)) {
            return false;
        }

        return this.id == other.id;
    }

    @Override 
    public int hashCode() {
        return Long.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Order{id=" + id
                + ", customer='" + customer + "'"
                + ", status=" + status + "}";
    }
}
