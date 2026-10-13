package Daou.Assignment.Q6;

public class CreditCardFeePolicy implements FeePolicy {
    
    @Override
    public long calculateFee(long amount) {
        return amount * 3 / 100;
    }
}
