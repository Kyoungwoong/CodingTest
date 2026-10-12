package Daou.Assignment.MockExam2.Q7;

/**
 * 회원에게 알람을 발송하는 interface
 */
public interface NotificationSender {

    void send(String recipient, String message);
}
