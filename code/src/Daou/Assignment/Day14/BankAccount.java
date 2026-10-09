package Daou.Assignment.Day14;

public class BankAccount {

    private int balance = 0;

    public synchronized void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "입금액은 0보다 커야 합니다."
            );
        }

        balance += amount;
    }

    public synchronized int getBalance() {
        return balance;
    }
}
