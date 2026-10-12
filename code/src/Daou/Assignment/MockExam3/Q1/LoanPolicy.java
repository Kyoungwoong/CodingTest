package Daou.Assignment.MockExam3.Q1;

/**
 * 대출 정책을 나타내는 인터페이스
 */
public interface LoanPolicy {

    /**
     * 대출 apply
     * @param member
     * @param currentLimit 현재까지 계산된 대출 한도
     * @return 정책 적용 후 한도
     */
    int apply(Member member, int currentLimit);

    default void validateMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException(
                    "member must not be null"
            );
        }
    }
}