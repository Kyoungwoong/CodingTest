package Daou.Assignment.MockExam1.Q2;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int rate;

    public RateDiscountPolicy(int rate) {
        // TODO 4: 할인율 검증
        if (isViolateRate(rate)) {
            throw new IllegalArgumentException();
        }
        this.rate = rate;
    }

    // TODO 5: DiscountPolicy 인터페이스 구현
    // TODO 6: calculateDiscount() 구현
    @Override
    public long calculateDiscount(long price) {
        return price - (price * rate / 100);
    }

    private boolean isViolateRate(int rate) {
        return 0 > rate || rate > 100;
    }
}
