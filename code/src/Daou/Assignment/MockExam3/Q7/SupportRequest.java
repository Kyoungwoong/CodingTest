package Daou.Assignment.MockExam3.Q7;

public record SupportRequest(
        String requestId,
        String customerName,
        int priority,
        long sequence
) {}