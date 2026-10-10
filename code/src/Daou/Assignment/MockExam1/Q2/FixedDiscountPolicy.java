package Daou.Assignment.MockExam1.Q2;

public class FixedDiscountPolicy implements DiscountPolicy {

    private final long discountAmount;

    public FixedDiscountPolicy(long discountAmount) {
        if (discountAmount < 0) {
            throw new IllegalArgumentException(
                    "Discount amount must not be negative"
            );
        }
        this.discountAmount = discountAmount;
    }

    @Override
    public long calculateDiscount(long price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price must not be negative"
            );
        }

        // 할인 금액은 상품 가격을 초과할 수 없다.
        return Math.min(price, discountAmount);
    }
}