package Daou.Assignment.MockExam3.Q6;

public class LegacyPaymentService {

    public long calculateFee(String paymentType, long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if ("CREDIT_CARD".equals(paymentType)) {
            return amount * 3 / 100;
        } else if ("BANK_TRANSFER".equals(paymentType)) {
            return amount / 100;
        } else if ("POINT".equals(paymentType)) {
            return 0;
        } else {
            throw new IllegalArgumentException("Unsupported payment type");
        }
    }

    public long calculateTotal(String paymentType, long amount) {
        return amount + calculateFee(paymentType, amount);
    }
}