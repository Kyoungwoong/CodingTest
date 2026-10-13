package Daou.Assignment.Q6;

public class PointFeePolicy implements FeePolicy {
    
    @Override
    public long calculateFee(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        return 0;
    }
}
