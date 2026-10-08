package Daou.Assignment.Day7;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Day 7: Object methods + enum. Run: javac -d . Day7Review.java && java Daou.Assignment.Day7.Day7Review */
enum ReviewOrderStatus {
    READY("주문 대기"), PAID("결제 완료"), CANCELLED("주문 취소");

    private final String description;
    ReviewOrderStatus(String description) { this.description = description; }
    public String getDescription() { return description; }
    public boolean isFinished() { return this == PAID || this == CANCELLED; }
}

class ReviewOrder {
    private final long id;
    private final String customer;
    private ReviewOrderStatus status;

    ReviewOrder(long id, String customer) {
        this.id = id;
        this.customer = customer;
        this.status = ReviewOrderStatus.READY;
    }

    public long getId() { return id; }
    public ReviewOrderStatus getStatus() { return status; }

    public void pay() {
        if (status != ReviewOrderStatus.READY)
            throw new IllegalStateException("Cannot pay order");
        status = ReviewOrderStatus.PAID;
    }

    public void cancel() {
        if (status != ReviewOrderStatus.READY)
            throw new IllegalStateException("Cannot cancel order");
        status = ReviewOrderStatus.CANCELLED;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ReviewOrder other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() { return Long.hashCode(id); }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customer='" + customer + "', status=" + status + "}";
    }
}

public class Day7Review {
    public static void main(String[] args) {
        ReviewOrder a = new ReviewOrder(1, "Kim");
        ReviewOrder b = new ReviewOrder(1, "Lee");
        ReviewOrder c = new ReviewOrder(2, "Park");

        System.out.println(a == b);                 // false: different instances
        System.out.println(a.equals(b));            // true: same id
        System.out.println(a.equals(c));            // false: different id
        System.out.println(a.equals(null));         // false, no NPE
        System.out.println(a.equals("hello"));      // false, no ClassCastException
        System.out.println(a.hashCode() == b.hashCode()); // true
        System.out.println(Objects.equals(null, null));  // true

        Set<ReviewOrder> orders = new HashSet<>();
        orders.add(a);
        orders.add(b);
        System.out.println(orders.size());          // 1: equal id

        a.pay();
        System.out.println(a.getStatus());                  // PAID
        System.out.println(a.getStatus().getDescription()); // 결제 완료
        System.out.println(a.getStatus().isFinished());     // true
        System.out.println(a);

        try {
            a.cancel();
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());    // Cannot cancel order
        }

        ReviewOrderStatus status = null;
        System.out.println(status == ReviewOrderStatus.READY); // false
        try {
            System.out.println(status.equals(ReviewOrderStatus.READY));
        } catch (NullPointerException e) {
            System.out.println("NPE from null.equals(...)");
        }

        System.out.println(ReviewOrderStatus.valueOf("PAID")); // PAID
        try {
            ReviewOrderStatus.valueOf("paid");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid enum name (runtime exception)");
        }

        for (ReviewOrderStatus s : ReviewOrderStatus.values()) {
            System.out.println(s.name() + " -> " + s.getDescription());
        }
    }
}

/*
 * REVIEW NOTES
 * - == compares reference identity; equals(Object) compares logical equality if overridden.
 * - equals(ReviewOrder) is an overload, NOT an override of Object.equals(Object).
 * - Always handle null and wrong types in equals(Object).
 * - If equals(a,b) is true, hashCode(a) MUST equal hashCode(b).
 * - Same hashCode does NOT imply equals is true (hash collision).
 * - Enum constants are singleton-like fixed instances; compare with ==.
 * - null == enumConstant is false; null.equals(...) throws NPE.
 * - Enum.valueOf("invalid") compiles, then throws IllegalArgumentException at runtime.
 * - Enum instances can have their own final fields, constructors and methods.
 * - Do not put mutable status in equals/hashCode when identity is based on id.
 */
