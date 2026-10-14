package Daou.Assignment.MockExam4.Q6;

import java.util.*;

public class PaymentService {

    private final Map<String, Long> balances = new HashMap<>();

    public void charge(String userId, long amount) {
        validatePayment(userId, amount);
        
        balances.put(userId, Math.addExact(amount, balances.getOrDefault(userId, 0L)));
    }

    public boolean pay(String userId, long amount) {
        validatePayment(userId, amount);

        try {
            long balance = balances.getOrDefault(userId, 0L);
            
            if (balance < amount) {
                throw new RuntimeException("잔액 부족");
            }

            balances.put(userId, balance - amount);

            return true;
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public long getBalance(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId must not be null or empty");
        }
        return balances.get(userId);
    }

    private void validatePayment(String userId, long amount) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId must not be null or empty");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive number");
        }
    }
}