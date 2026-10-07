package Daou.Assignment.Day5;

abstract class Account {
    private long balance;

    public Account(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return this.balance;
    }

    public boolean setBalance(long remain) {
        if (remain < 0) {
            return false;
        }
        this.balance = remain;

        return true;
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
        if (amount <= this.getBalance()) {
            this.setBalance(this.getBalance() - amount);
        }
    }

    @Override
    public void transfer(Account target, long amount) {
        if (amount <= this.getBalance()) {
            withdraw(amount);
            target.setBalance(this.getBalance() + amount);
        }
    }
}


public class Bank {
    public static void main(String[] args) {
        
    }
}
