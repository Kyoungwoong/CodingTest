package Daou.Assignment.MockExam1.Q3;

import java.util.List;

public class NotificationService {

    public void sendAll(List<Notification> notifications) {
        if (notifications == null) {
            throw new IllegalArgumentException(
                    "Notifications cannot be null"
            );
        }

        // 발송 전에 먼저 검증
        // 리스트에 null 원소가 있다면 어떤 알림도 발송하지 않아야 한다.
        for (Notification notification : notifications) {
            if (notification == null) {
                throw new IllegalArgumentException(
                        "Notification cannot be null"
                );
            }
        }

        for (Notification notification : notifications) {
            notification.send();
        }
    }
}
