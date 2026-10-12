package Daou.Assignment.MockExam2.Q10;

public class Account {

    private final String accountId;
    private long balance;
    private static final long ALLOWED_TRANSFER_AMOUNT = 1;

    public Account(String accountId, long initialBalance) {
        validateAccount(accountId, initialBalance);

        this.accountId = accountId;
        this.balance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public long getBalance() {
        return balance;
    }

    /**
     * Long 범위를 초과하면 ArithmeticException을 발생시킨다.
     * @param amount
     */
    public void deposit(long amount) {
        validateAmount(amount);

        balance = Math.addExact(getBalance(), amount);
    }

    public void withdraw(long amount) {
        validateWithdraw(amount);

        balance -= amount;
    }

    /**
     * accountId는 null이거나 공백이 아니다.
     * initialBalnce는 0이상 이어야한다.
     * @param accountId 계좌 ID
     * @param initialBalance 초기 잔액
     */
    private void validateAccount(String accountId, long initialBalance) {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException(
                    "accountId must not be null or blank"
            );
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException(
                    "initialBalance must be zero or positive number"
            );
        }
    }

    /**
     * 입금과 출금 금액은 1이상이어야한다.
     */
    private void validateAmount(long amount) {
        if (amount < ALLOWED_TRANSFER_AMOUNT) {
            throw new IllegalArgumentException(
                    "Transfer amount must be greater than 1"
            );
        }
    }

    /**
     * 출금액은 잔액보다 작아야한다.
     */
    private void validateWithdraw(long amount) {
        validateAmount(amount);

        if (amount > getBalance()) {
            throw new IllegalStateException(
                    "Transfer amount must be less than balance"
            );
        }
    }
}
