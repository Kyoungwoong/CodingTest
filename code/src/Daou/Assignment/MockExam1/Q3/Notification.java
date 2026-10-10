package Daou.Assignment.MockExam1.Q3;

public abstract class Notification {

    private final String recipient;
    private final String message;

    protected Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    public void send() {
        validate();

        String formatedMessage = formatMessage(message);

        deliver(recipient, formatedMessage);
    }

    private void validate() {
        if (recipient.isEmpty()) {
            throw new IllegalArgumentException(
                    "recipient must not be null"
            );
        }

        if (message.isEmpty()) {
            throw new IllegalArgumentException(
                    "message must not be null"
            );
        }
    }

    // formatMessage() 추상 메서드 선언
    // 매개변수: String message
    // 반환 타입: String
    abstract String formatMessage(String message);

    // deliver() 추상 메서드 선언
    // 매개변수: String recipient, String formattedMessage
    // 반환 타입: void
    abstract void deliver(String recipient, String formattedMessage);
}