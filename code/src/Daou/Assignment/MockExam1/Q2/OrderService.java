package Daou.Assignment.MockExam1.Q2;

public class OrderService {

    private final DiscountPolicy discountPolicy;

    public OrderService(DiscountPolicy discountPolicy) {
        if (discountPolicy == null) {
            throw new IllegalArgumentException(
                    "Discount policy must not be null"
            );
        }

        this.discountPolicy = discountPolicy;
    }

    public long calculateFinalPrice(long price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price must not be negative"
            );
        }

        long discountAmount = discountPolicy.calculateDiscount(price);

        // 할인 정책이 계약을 위반한 경우 정상적인 결제 금액을 반환하지 않는다.
        if (discountAmount < 0 || discountAmount > price) {
            throw new IllegalStateException(
                    "Invalid discount amount: " + discountAmount
            );
        }

        return price - discountAmount;
    }
}