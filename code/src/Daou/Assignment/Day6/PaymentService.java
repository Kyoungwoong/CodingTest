package Daou.Assignment.Day6;

class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends PaymentException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

abstract class Payment {
    private long balance;
    
    public Payment(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return this.balance;
    }

    protected void decreaseBalance(long amount) {
        if (amount >= 0 && amount <= this.getBalance()) {
            this.balance -= amount;
        }
    }

    public abstract void pay(long amount);
}

class CardPayment extends Payment {
    public CardPayment(long balance) {
        super(balance);
    }

    @Override 
    public void pay(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (amount > this.getBalance()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        decreaseBalance(amount);
        System.out.println("Payment Complete");
    }
}

public class PaymentService {
    public void process(Payment payment, long amount) {

        // payment.pay(amount) 호출
        try {
            payment.pay(amount);
        } catch (InsufficientBalanceException ib) {
            // InsufficientBalanceException이면
            // "Payment Failed: {message}"
            System.out.println("Payment Failed: " + ib.getMessage());
        } catch (IllegalArgumentException iae) {
            // IllegalArgumentException이면
            // "Invalid Payment: {message}"
            System.out.println("Invalid Payment: " + iae.getMessage());
        }
    }

    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        Payment payment = new CardPayment(10_000);

        service.process(payment, 3_000);
        service.process(payment, 20_000);
        service.process(payment, -100);
    }
}
