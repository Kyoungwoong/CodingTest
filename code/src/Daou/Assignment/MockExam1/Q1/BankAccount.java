package Daou.Assignment.MockExam1.Q1;

public class BankAccount {

    private long balance;
    private static final int ALLOWED_INIT_BALANCE = 0;
    private static final int ALLOWED_BALANCE = 0;

    public BankAccount(long initialBalance) {
        // TODO 1: 초기 잔액 검증
        if (initialBalance < ALLOWED_INIT_BALANCE) {
            throw new IllegalArgumentException();
        }
        this.balance = initialBalance;
    }

    public void deposit(long amount) {
        // TODO 2: 입금 금액 검증
        if (amount <= ALLOWED_BALANCE) { // 1. 요구사항 확인
            throw new IllegalArgumentException();
        }
        // TODO 3: 오버플로 방지
        if (balance > Long.MAX_VALUE - amount) { // 2. 계산 잘못함.
            throw new IllegalArgumentException();
        }
        // TODO 4: 입금 처리
        balance += amount;
    }

    public void withdraw(long amount) {
        // TODO 5: 출금 금액 검증
        if (amount < ALLOWED_BALANCE) {
            throw new IllegalArgumentException();
        }
        // TODO 6: 잔액 부족 검증
        if (balance < amount) {
            throw new InsufficientBalanceException(balance, amount);
        }
        // TODO 7: 출금 처리
        this.balance -= amount;
    }

    public long getBalance() {
        // TODO 8: 현재 잔액 반환
        return this.balance;
    }

    public static void main(String[] args) {
        // Case A
        BankAccount account = new BankAccount(10000);

        account.deposit(5000);
        account.withdraw(3000);

        System.out.println(account.getBalance());

        // Case B
        account = new BankAccount(5000);

        account.withdraw(8000);

        // Case C
        account = new BankAccount(10000);

        account.deposit(-500);
    }
}