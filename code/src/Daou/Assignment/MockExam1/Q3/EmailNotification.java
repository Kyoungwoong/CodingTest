package Daou.Assignment.MockExam1.Q3;

public class EmailNotification extends Notification {

    private final static String EMAIL = "[EMAIL] ";

    public EmailNotification(String recipient, String message) {
        super(recipient, message);
    }

    @Override
    public String formatMessage(String message) {
        return EMAIL + message;
    }

    @Override
    public void deliver(String recipient, String formattedMessage) {
        System.out.println(
                "EMAIL TO: " + recipient + " | " +
                        "MESSAGE: " + formattedMessage
        );
    }
}