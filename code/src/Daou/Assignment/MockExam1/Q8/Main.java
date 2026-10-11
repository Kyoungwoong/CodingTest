package Daou.Assignment.MockExam1.Q8;

class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}

class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        if (balance < amount) {
            throw new TransferException("Insufficient balance");
        }
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }
}

class TransferService {

    public void transfer(
            Account from,
            Account to,
            int amount
    ) {
        try {
            from.withdraw(amount);

            if (amount > 500) {
                throw new TransferException("Transfer limit exceeded");
            }

            to.deposit(amount);

        } catch (TransferException e) {
            System.out.println("FAILED: " + e.getMessage());

        } finally {
            System.out.println("TRANSFER FINISHED");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Account a = new Account(1000);
        Account b = new Account(500);

        new TransferService().transfer(a, b, 600);

        System.out.println("A: " + a.getBalance());
        System.out.println("B: " + b.getBalance());
    }
}
