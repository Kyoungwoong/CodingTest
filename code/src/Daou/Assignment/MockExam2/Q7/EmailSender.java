package Daou.Assignment.MockExam2.Q7;

/**
 * Email 관련 Sender
 */
public class EmailSender implements NotificationSender {

    private static final String EMAIL_PREFIX = "EMAIL to ";

    @Override
    public void send(String recipient, String message) {
        System.out.println(
                EMAIL_PREFIX + recipient + ": " + message
        );
    }
}
