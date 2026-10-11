package Daou.Assignment.MockExam1.Q7;

class Payment {
    public void process(Object value) {
        System.out.println("Payment-Object");
    }

    public void process(String value) {
        System.out.println("Payment-String");
    }
}

class CardPayment extends Payment {
    @Override
    public void process(Object value) {
        System.out.println("Card-Object");
    }

    public void process(Integer value) {
        System.out.println("Card-Integer");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment payment = new CardPayment();

        Object value = "10000";

        payment.process(value);
        payment.process("10000");
        payment.process(null);
    }
}
