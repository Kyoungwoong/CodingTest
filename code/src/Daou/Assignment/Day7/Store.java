package Daou.Assignment.Day7;

enum OrderStatus {
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


class Order {
    private final long id;
    private String customer;
    private OrderStatus status;

    public Order(long id, String customer) {
        this.id = id;
        this.customer = customer;
        this.status = OrderStatus.READY;
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

public class Store {
    public static void main(String[] args) {
        Order a = new Order(1, "Kim");
        Order b = new Order(1, "Lee");
        Order c = new Order(2, "Park");

        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a.hashCode() == b.hashCode());

        a.pay();
        System.out.println(a.getStatus());
        System.out.println(a.getStatus().getDescription());

        try {
            a.cancel();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(a);
    }
}
