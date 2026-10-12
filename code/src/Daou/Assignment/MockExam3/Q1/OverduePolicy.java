package Daou.Assignment.MockExam3.Q1;

public class OverduePolicy implements LoanPolicy {

    @Override
    public int apply(Member member, int currentLimit) {
        validateMember(member);

        if (member.overdue()) {
            return 0;
        }
        return currentLimit;
    }
}
