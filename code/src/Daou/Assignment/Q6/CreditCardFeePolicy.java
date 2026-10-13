package Daou.Assignment.Q6;

public class CreditCardFeePolicy implements FeePolicy {
    
    @Override
    public long calculateFee(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        return Math.multiplyExact(amount, 3) / 100;
    }
}
