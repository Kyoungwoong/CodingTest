package Daou.Assignment.MockExam1.Q3;

public class SmsNotification extends Notification {

    private static final int MAX_MESSAGE_LENGTH = 20;
    private static final String SMS = "[SMS] ";

    public SmsNotification(String recipient, String message) {
        super(recipient, message);
    }

    // 원본 메시지 길이 검증 포함
    @Override
    public String formatMessage(String message) {
        if (message.length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException(
                    "message length can be " + MAX_MESSAGE_LENGTH
            );
        }
        return SMS + message;
    }

    @Override
    public void deliver(String recipient, String formattedMessage) {
        System.out.println(
                "SMS TO: " + recipient + " | " +
                        "MESSAGE: " + formattedMessage
        );
    }
}
