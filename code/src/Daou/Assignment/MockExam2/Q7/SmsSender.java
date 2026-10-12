package Daou.Assignment.MockExam2.Q7;

public class SmsSender implements NotificationSender {
    private static final String SMS_PREFIX = "SMS to ";

    @Override
    public void send(String recipient, String message) {
        System.out.println(
                SMS_PREFIX + recipient + ": " + message
        );
    }
}
