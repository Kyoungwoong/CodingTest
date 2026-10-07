package Daou.Assignment.Day5;

abstract class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public abstract void send();
}

interface Loggable {
    void log();
}

class EmailNotification extends Notification implements Loggable {

    public EmailNotification(String message) {
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
        Notification notification = new EmailNotification("Hello");

        notification.send();

        Loggable loggable = new EmailNotification("Hello");

        loggable.log();
    }
}