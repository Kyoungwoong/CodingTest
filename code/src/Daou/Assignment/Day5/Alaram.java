package Daou.Assignment.Day5;

abstract class AlarmNotification {
    private String message;

    public AlarmNotification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public abstract void send();
}

interface AlarmLoggable {
    void log();
}

class AlarmEmailNotification extends AlarmNotification implements AlarmLoggable {

    public AlarmEmailNotification(String message) {
        super(message);
    }

    @Override 
    public void send() {
        System.out.println("Email: " + this.getMessage());
    }

    @Override 
    public void log() {
        System.out.println("Log: " + this.getMessage());
    }
}

public class Alaram {
    public static void main(String[] args) {
        AlarmNotification notification = new AlarmEmailNotification("Hello");

        notification.send();

        AlarmLoggable loggable = new AlarmEmailNotification("Hello");

        loggable.log();
    }
}
