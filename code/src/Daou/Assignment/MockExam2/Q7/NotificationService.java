package Daou.Assignment.MockExam2.Q7;


public class NotificationService {

    private final NotificationSender sender;

    public NotificationService(NotificationSender sender) {
        if (sender == null) {
            throw new IllegalArgumentException(
                    "NotificationSender must not be null"
            );
        }
        this.sender = sender;
    }

    /**
     * validaate를 실패하면 send를 호출하지 않는다.
     */
    public void notify(
            String recipient,
            String message
    ) {
        validateNotify(recipient, message);
        sender.send(recipient, message);
    }

    /**
     * recipient와 message는 null이나 공백이 아니여야 한다.
     * @throws IllegalArgumentException: recipient나 message가 null이나 공백일 때
     * @param recipient 수신자
     * @param message 메시지
     */
    private void validateNotify(String recipient, String message) {
        if (recipient == null || recipient.isBlank()) {
            throw new IllegalArgumentException(
                    "recipient must not be null"
            );
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException(
                    "message must not be null"
            );
        }
    }
}