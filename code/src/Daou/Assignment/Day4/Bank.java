package Daou.Assignment.Day4;

class Payment {
    public void pay() {
        System.out.println("Payment");
    }
}

class CardPayment extends Payment {
    @Override 
    public void pay() {
        System.out.println("Card Payment");
    }
}

class BankTransfer extends Payment {
    @Override 
    public void pay() {
        System.out.println("Bank Transfer");
    }
}

public class Bank {
    public static void main(String[] args) {
        process(new CardPayment());
        process(new BankTransfer());
    }

    private static void process(Payment payment) {
        payment.pay();
    }
}
