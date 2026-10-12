package Daou.Assignment.MockExam2.Q6;

public record Order(
        String customerId,
        long amount,
        OrderStatus status
) {}