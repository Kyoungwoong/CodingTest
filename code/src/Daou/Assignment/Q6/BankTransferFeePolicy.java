package Daou.Assignment.Q6;

public class BankTransferFeePolicy implements FeePolicy {
    
    @Override
    public long calculateFee(long amount) {
        return amount / 100;
    }
}
