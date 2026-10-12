package Daou.Assignment.Q2;

public record Reservation(
        String reservationId,
        String customerName,
        int priority,
        long requestedAt
) {}