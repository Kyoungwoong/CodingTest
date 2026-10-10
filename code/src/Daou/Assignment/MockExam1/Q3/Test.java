package Daou.Assignment.MockExam1.Q3;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        try {
            // 정상발송
            caseA();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            // SMS 메시지 길이 초과
            caseB();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            // null 원소 포함
            caseC();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void caseA() {
        NotificationService service = new NotificationService();

        List<Notification> notifications = List.of(
                new EmailNotification("user@example.com", "Hello"),
                new SmsNotification("01012345678", "Welcome")
        );

        service.sendAll(notifications);
    }

    private static void caseB() {
        Notification notification = new SmsNotification(
                "01012345678",
                "123456789012345678901"
        );

        notification.send();
    }

    private static void caseC() {
        List<Notification> notifications = new ArrayList<>();

        notifications.add(
                new EmailNotification("user@example.com", "Hello")
        );
        notifications.add(null);

        new NotificationService().sendAll(notifications);
    }
}
