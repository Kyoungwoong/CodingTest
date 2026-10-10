package Daou.Assignment.MockExam1.Q2;

public class RateDiscountPolicy implements DiscountPolicy {

    private static final int MIN_RATE = 0;
    private static final int MAX_RATE = 100;

    private final int rate;

    public RateDiscountPolicy(int rate) {
        if (isInvalidRate(rate)) {
            throw new IllegalArgumentException(
                    "Discount rate must be between 0 and 100"
            );
        }
        this.rate = rate;
    }

    @Override
    public long calculateDiscount(long price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "Price must not be negative"
            );
        }

        // price * rate를 직접 계산하면 long 오버플로가 발생할 수 있다.
        // 몫과 나머지를 분리하여 정확한 할인 금액을 계산한다.
        long quotient = price / 100;
        long remainder = price % 100;

        return quotient * rate + (remainder * rate) / 100;
    }

    private static boolean isInvalidRate(int rate) {
        return rate < MIN_RATE || rate > MAX_RATE;
    }
}