package Daou.Assignment.Day5;

abstract class Account {
    private long balance;

    public Account(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return this.balance;
    }

    protected void decreaseBalance(long amount) {
        this.balance -= amount;
    }

    public void deposit(long amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public abstract void withdraw(long amount);
}

interface Transfer {
    void transfer(Account target, long amount);

    default void printTransferInfo() {
        System.out.println("Transfer Available");
    }
}

class BankAccount extends Account implements Transfer {
    
    public BankAccount(long balance) {
        super(balance);
    }

    @Override
    public void withdraw(long amount) {
        if (amount > 0 && amount <= getBalance()) {
            decreaseBalance(amount);
        }
    }

    @Override
    public void transfer(Account target, long amount) {
        if (amount > 0 && amount <= getBalance()) {
            withdraw(amount);
            target.deposit(amount);
        }
    }
}


public class Bank {
    public static void main(String[] args) {
        
    }
}
