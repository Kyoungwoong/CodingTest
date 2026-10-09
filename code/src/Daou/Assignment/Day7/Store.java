package Daou.Assignment.Day7;

enum StoreOrderStatus {
    READY("주문대기"), PAID("결제완료"), CANCELLED("주문취소");

    private final String description;

    StoreOrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isFinished() {
        return this == PAID || this == CANCELLED;
    }
}


class StoreOrder {
    private final long id;
    private String customer;
    private StoreOrderStatus status;

    public StoreOrder(long id, String customer) {
        this.id = id;
        this.customer = customer;
        this.status = StoreOrderStatus.READY;
    }

    public long getId() {
        return this.id;
    }

    public StoreOrderStatus getStatus() {
        return this.status;
    }

    public void pay() {
        if (status == StoreOrderStatus.READY) {
            status = StoreOrderStatus.PAID;
            return;
        }
        throw new IllegalStateException("잘못된 결재 상태 변경입니다");
    }

    public void cancel() {
        if (status == StoreOrderStatus.READY) {
            status = StoreOrderStatus.CANCELLED;
            return;
        }
        throw new IllegalStateException("잘못된 취소 상태 변경입니다");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StoreOrder other)) {
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
        StoreOrder a = new StoreOrder(1, "Kim");
        StoreOrder b = new StoreOrder(1, "Lee");
        StoreOrder c = new StoreOrder(2, "Park");

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
