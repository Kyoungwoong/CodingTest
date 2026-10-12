package Daou.Assignment.MockExam3.Q1;

public record Member(
        String memberId,
        MemberType type,
        int borrowedCount,
        boolean overdue,
        int penaltyPoints
) {}