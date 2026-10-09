package Daou.Assignment.Day4;

class BankPayment {
    public void pay() {
        System.out.println("Payment");
    }
}

class BankCardPayment extends BankPayment {
    @Override 
    public void pay() {
        System.out.println("Card Payment");
    }
}

class BankTransferPayment extends BankPayment {
    @Override 
    public void pay() {
        System.out.println("Bank Transfer");
    }
}

public class Bank {
    public static void main(String[] args) {
        process(new BankCardPayment());
        process(new BankTransferPayment());
    }

    private static void process(BankPayment payment) {
        payment.pay();
    }
}
