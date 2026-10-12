package Daou.Assignment.MockExam2.Q8;

public class BankAccount {

    private final String accountNumber;
    private long balance;

    public BankAccount(String accountNumber, long balance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid account");
        }

        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(long amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (balance < amount) {
            throw new IllegalStateException("Insufficient balance");
        }

        balance -= amount;
    }

    public long getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
