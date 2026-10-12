package Daou.Assignment.MockExam3.Q1;

import java.util.List;

public class LoanService {

    private final List<LoanPolicy> policies;

    public LoanService(List<LoanPolicy> policies) {
        if (policies == null) {
            throw new IllegalArgumentException(
                    "policies must not be null"
            );
        }

        // 원본 객체를 변경 불가능한 리스트 요소로 만듦.
        // null 요소가 포함되면 NPE를 발생시킴
        if (policies.contains(null)) {
            throw new IllegalArgumentException(
                    "policies must not contain null"
            );
        }

        this.policies = List.copyOf(policies);
    }

    public int calculateAvailableCount(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("member must not be null");
        }

        if (member.memberId() == null || member.memberId().isBlank()) {
            throw new IllegalArgumentException("Invalid memberId");
        }

        if (member.type() == null) {
            throw new IllegalArgumentException("Invalid member type");
        }

        if (member.borrowedCount() < 0 || member.penaltyPoints() < 0) {
            throw new IllegalArgumentException("Negative count or points");
        }
        
        int result = member.type().getAvailableCount();

        for (LoanPolicy policy : policies) {
            result = policy.apply(member, result);

            if (result < 0) {
                throw new IllegalStateException(
                        "Policy returned a negative limit"
                );
            }
        }

        return Math.max(0, result - member.borrowedCount());
    }
}
