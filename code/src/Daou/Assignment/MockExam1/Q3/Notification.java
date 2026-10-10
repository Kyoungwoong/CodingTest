package Daou.Assignment.MockExam1.Q3;

public abstract class Notification {

    private final String recipient;
    private final String message;

    protected Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    /**
     * 공통 알림 발송 절차를 수행한다.
     * 검증, 메시지 가공, 발송 순서를 보장하며
     * 하위 클래스에서 재정의할 수 없다.
     */
    public final void send() {
        validate();

        String formattedMessage = formatMessage(message);

        deliver(recipient, formattedMessage);
    }

    private void validate() {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException(
                    "Recipient must not be null or blank"
            );
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException(
                    "Message must not be null or blank"
            );
        }
    }

    // formatMessage() 추상 메서드 선언
    // 매개변수: String message
    // 반환 타입: String
    protected abstract String formatMessage(String message);

    // deliver() 추상 메서드 선언
    // 매개변수: String recipient, String formattedMessage
    // 반환 타입: void
    protected abstract void deliver(String recipient, String formattedMessage);
}