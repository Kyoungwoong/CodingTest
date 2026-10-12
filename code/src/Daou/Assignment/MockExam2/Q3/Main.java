package Daou.Assignment.MockExam2.Q3;

class Account {

    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int withdraw(int amount) {
        try {
            balance -= amount;

            if (balance < 0) {
                throw new IllegalStateException(
                    "Insufficient balance"
                );
            }

            return balance;

        } catch (IllegalStateException e) {
            System.out.println("WITHDRAW FAILED");
            return -1;

        } finally {
            balance += 100;
            System.out.println("FINALLY: " + balance);
        }
    }

    public int getBalance() {
        return balance;
    }
}

public class Main {

    public static void main(String[] args) {
        Account account = new Account(500);

        int result = account.withdraw(700);

        System.out.println("RESULT: " + result);
        System.out.println(
            "BALANCE: " + account.getBalance()
        );
    }
}
