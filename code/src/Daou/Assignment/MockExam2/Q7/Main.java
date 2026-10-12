package Daou.Assignment.MockExam2.Q7;

public class Main {

    public static void main(String[] args) {

        NotificationService emailService =
                new NotificationService(new EmailSender());

        emailService.notify(
                "user@example.com",
                "Hello"
        );

        NotificationService smsService =
                new NotificationService(new SmsSender());

        smsService.notify(
                "01012345678",
                "Hello"
        );
    }
}