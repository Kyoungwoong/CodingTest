package Daou.Assignment.Q7;

public record SupportRequest(
        String requestId,
        String customerName,
        int priority,
        long sequence
) {}