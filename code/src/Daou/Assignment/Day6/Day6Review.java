package Daou.Assignment.Day6;

import java.io.IOException;

class ReviewPaymentException extends RuntimeException {
    public ReviewPaymentException(String message) { super(message); }
}
class ReviewInsufficientBalanceException extends ReviewPaymentException {
    public ReviewInsufficientBalanceException(String message) { super(message); }
}
class ReviewExternalPaymentException extends Exception {
    public ReviewExternalPaymentException(String message) { super(message); }
}
abstract class ReviewPayment {
    private long balance;
    public ReviewPayment(long balance) { this.balance = balance; }
    public long getBalance() { return balance; }
    protected void decreaseBalance(long amount) { balance -= amount; }
    public abstract void pay(long amount);
}
class ReviewCardPayment extends ReviewPayment {
    public ReviewCardPayment(long balance) { super(balance); }
    @Override
    public void pay(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        if (amount > getBalance())
            throw new ReviewInsufficientBalanceException("Insufficient balance");
        decreaseBalance(amount);
        System.out.println("Payment Complete");
    }
}
class Parent {
    void work() throws Exception {}
}
class Child extends Parent {
    @Override
    void work() throws IOException {} // narrower checked exception: O
}

public class Day6Review {
    public static void main(String[] args) {
        ReviewPayment payment = new ReviewCardPayment(10_000);
        process(payment, 3_000);
        process(payment, 20_000);
        process(payment, -100);
        System.out.println(payment.getBalance()); // 7000

        propagation();

        System.out.println(testFinally()); // A, then 10
    }

    static void process(ReviewPayment payment, long amount) {
        try {
            payment.pay(amount);
        } catch (ReviewInsufficientBalanceException e) {
            System.out.println("Payment Failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Payment: " + e.getMessage());
        }
    }

    static void c() throws Exception {
        System.out.println("C");
        throw new Exception("Error");
    }
    static void b() throws Exception {
        System.out.println("B");
        c();
        System.out.println("D"); // not executed
    }
    static void propagation() {
        try {
            System.out.println("A");
            b();
            System.out.println("E"); // not executed
        } catch (Exception e) {
            System.out.println("F");
        }
        // A B C F
    }

    static int testFinally() {
        try {
            return 10;
        } finally {
            System.out.println("A");
        }
    }
}

/*
DAY 6 QUICK REVIEW

Throwable
|- Error
`- Exception
   |- IOException -> Checked
   `- RuntimeException -> Unchecked
      |- IllegalArgumentException
      |- NullPointerException
      `- ArithmeticException

Checked:
- Exception 계열 중 RuntimeException 계열이 아닌 것
- catch 또는 throws 강제

Unchecked:
- RuntimeException 및 자식
- catch/throws 강제 X

throw  = 실제 예외 발생
throws = 호출자로 예외가 전달될 수 있음을 선언

Custom:
extends Exception        -> Checked
extends RuntimeException -> Unchecked

RuntimeException도 throws에 선언할 수 있지만 필수는 아니다.

catch 순서:
child exception -> parent exception

Exception propagation:
현재 method에서 catch하지 않으면 call stack 위로 전파.

Overriding + Checked:
Parent throws Exception -> Child throws IOException : O
Parent throws IOException -> Child throws Exception : X
Parent throws IOException -> Child throws nothing   : O

Overriding + Unchecked:
Parent throws nothing -> Child throws IllegalArgumentException : O

finally:
return이 있어도 method 종료 전에 실행.
finally에서 return하면 기존 return을 덮을 수 있으므로 권장하지 않음.

Catch도 다형성:
IllegalArgumentException 객체를 RuntimeException/Exception으로 catch 가능.
*/
