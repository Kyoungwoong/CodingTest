package Daou.Assignment.MockExam1;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(
            long balance,
            long requestedAmount
    ) {
        // TODO 9: 부모 생성자를 호출하여
        // 현재 잔액과 요청 금액을 포함하는
        // 예외 메시지 설정
        super("current Balance: " + balance + " requestedAmount: " + requestedAmount);
    }
}
