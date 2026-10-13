package Daou.Assignment.MockExam3.Q6;

public class PaymentService {

    private final FeePolicy feePolicy;

    public PaymentService(FeePolicy feePolicy) {
        if (feePolicy == null) {
            throw new IllegalArgumentException(
                "feePolicy must not be null"
            );
        }

        this.feePolicy = feePolicy;
    }

    public long calculateFee(long amount) {
        return feePolicy.calculateFee(amount);
    }

    public long calculateTotal(long amount) {
        return Math.addExact(amount, calculateFee(amount));
    }
}
