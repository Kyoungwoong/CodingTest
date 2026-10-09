package Daou.Assignment.Day5;

abstract class ReviewAccount {
    private long balance;

    public ReviewAccount(long balance) { this.balance = balance; }
    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount > 0) balance += amount;
    }

    protected boolean decreaseBalance(long amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    public abstract void withdraw(long amount);
}

interface ReviewTransferable {
    int MAX_TRANSFER_COUNT = 10; // public static final
    void transfer(ReviewAccount target, long amount); // public abstract

    default void printTransferInfo() {
        printMessage("Transfer Available");
    }

    static void printRule() {
        System.out.println("Transfer rule");
    }

    private void printMessage(String message) {
        System.out.println(message);
    }
}

interface ReviewPrintable {
    default void print() { System.out.println("Printable"); }
}

interface ReviewPrintableLoggable {
    default void print() { System.out.println("Loggable"); }
}

class ReviewBankAccount extends ReviewAccount implements ReviewTransferable {
    public ReviewBankAccount(long balance) { super(balance); }

    @Override
    public void withdraw(long amount) {
        decreaseBalance(amount);
    }

    @Override
    public void transfer(ReviewAccount target, long amount) {
        if (decreaseBalance(amount)) {
            target.deposit(amount);
        }
    }
}

// Same default method from unrelated interfaces -> must resolve conflict.
class ReviewReport implements ReviewPrintable, ReviewPrintableLoggable {
    @Override
    public void print() {
        ReviewPrintable.super.print();
    }
}

abstract class ReviewNotification {
    private final String message;

    public ReviewNotification(String message) { this.message = message; }
    public String getMessage() { return message; }
    public abstract void send();
}

interface ReviewNotificationLoggable {
    void log();
}

class ReviewEmailNotification extends ReviewNotification implements ReviewNotificationLoggable {
    public ReviewEmailNotification(String message) { super(message); }

    @Override
    public void send() {
        System.out.println("Email: " + getMessage());
    }

    @Override
    public void log() {
        System.out.println("Log: " + getMessage());
    }
}

public class Day5Review {
    public static void main(String[] args) {
        ReviewNotification notification = new ReviewEmailNotification("Hello");
        notification.send();

        ReviewNotificationLoggable loggable = new ReviewEmailNotification("Hello");
        loggable.log();

        ReviewBankAccount sender = new ReviewBankAccount(10_000);
        ReviewBankAccount receiver = new ReviewBankAccount(3_000);

        ReviewTransferable transferable = sender;
        transferable.printTransferInfo();
        transferable.transfer(receiver, 2_000);

        System.out.println(sender.getBalance());   // 8000
        System.out.println(receiver.getBalance()); // 5000

        // Interface static method -> call using interface name.
        ReviewTransferable.printRule();

        // Interface field -> public static final.
        System.out.println(ReviewTransferable.MAX_TRANSFER_COUNT);

        // Default-method conflict was resolved in Report#print().
        new ReviewReport().print();
    }
}

/*
DAY 5 QUICK REVIEW

1. abstract class
   - instance field O
   - constructor O
   - normal method O
   - abstract method O
   - direct instantiation X

2. interface
   - abstract method: public abstract
   - field: public static final
   - default method: implementation O, override O
   - static method: InterfaceName.method()
   - private method: internal helper

3. inheritance
   class -> class: extends
   class -> interface: implements
   interface -> interface: extends

4. multiple inheritance
   class extends A, B: X
   class implements A, B: O
   interface C extends A, B: O

5. default conflict
   unrelated A/B same default method -> implementing class must override.
   A.super.method() can explicitly call A's default implementation.

6. priority
   superclass concrete method vs interface default -> class wins.
   parent interface vs more-specific child interface -> child interface wins.

7. polymorphism
   callable members -> reference type
   overridden implementation -> actual object

8. encapsulation
   private field + public setter is not automatically good design.
   Prefer meaningful operations such as deposit(), withdraw(), transfer().
*/
