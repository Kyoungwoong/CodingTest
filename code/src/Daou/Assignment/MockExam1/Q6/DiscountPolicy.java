package Daou.Assignment.MockExam1.Q6;

public class DiscountPolicy {

    private final int rate;

    public DiscountPolicy(int rate) {
        validateRate(rate);
        this.rate = rate;
    }

    public long calculateDiscount(long price) {
        if (price < 0) {
            throw new IllegalArgumentException(
                    "price must be zero or positive number"
            );
        }
        return price * (rate / 100);
    }

    private void validateRate(int rate) {
        if (0 > rate || rate > 100) {
            throw new IllegalStateException(
                    "rate must between 0 and 100"
            );
        }
    }
}