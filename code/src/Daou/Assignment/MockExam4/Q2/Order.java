package Daou.Assignment.MockExam4.Q2;

public record Order(
        String orderId,
        String customerId,
        String productId,
        int quantity,
        long unitPrice
) {}