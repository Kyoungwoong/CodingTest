package Daou.Assignment.MockExam1.Q2;

public class FixedDiscountPolicy implements DiscountPolicy {

    private final long discountAmount;
    private static final int ALLOWED_DISCOUNT_AMOUNT = 0;

    public FixedDiscountPolicy(long discountAmount) {
        // TODO 1: 할인 금액 검증
        if (discountAmount < ALLOWED_DISCOUNT_AMOUNT) {
            throw new IllegalArgumentException();
        }
        this.discountAmount = discountAmount;
    }

    // TODO 2: DiscountPolicy 인터페이스 구현
    // TODO 3: calculateDiscount() 구현
    @Override
    public long calculateDiscount(long price) {
        return price >= discountAmount ?
                price - discountAmount : price;
    }
}