package Daou.Assignment.MockExam3.Q1;

public class PenaltyPolicy implements LoanPolicy {

    /**
     * 벌점이 4점 이하면 한도 유지
     * 벌점이 9점 이하면 2권 감소
     * 벌점이 10점 이상이면 5권 감소
     * @param member
     * @param currentLimit 현재까지 계산된 대출 한도
     * @return
     */
    @Override
    public int apply(Member member, int currentLimit) {
        validateMember(member);

        int result = currentLimit;
        int penaltyPoint = member.penaltyPoints();

        if (4 < penaltyPoint && penaltyPoint <= 9 ) {
            result -= 2;
        } else if (member.penaltyPoints() >= 10) {
            result -= 5;
        }

        return Math.max(result, 0);
    }
}