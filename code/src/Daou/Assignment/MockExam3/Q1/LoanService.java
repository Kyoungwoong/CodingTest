package Daou.Assignment.MockExam3.Q1;

import java.util.List;

public class LoanService {

    private final List<LoanPolicy> policies;

    public LoanService(List<LoanPolicy> policies) {
        // TODO
        if (policies == null) {
            throw new IllegalArgumentException(
                    "policies must not be null"
            );
        }

        this.policies = policies;
    }

    public int calculateAvailableCount(Member member) {
        // TODO
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
