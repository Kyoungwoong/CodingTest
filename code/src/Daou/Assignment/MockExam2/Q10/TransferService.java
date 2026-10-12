package Daou.Assignment.MockExam2.Q10;

public class TransferService {

    private final AccountRepository repository;
    private static final int ALLOWED_TRANSFER_AMOUNT = 1;

    public TransferService(AccountRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException(
                    "repository must not be null"
            );
        }

        this.repository = repository;
    }

    public void transfer(String fromAccountId,
                         String toAccountId,
                         long amount) {
        validateTransfer(fromAccountId, toAccountId, amount);

        Account fromAccount = repository.findById(fromAccountId)
                .orElseThrow( () -> new TransferException("None match Account: " + fromAccountId));
        Account toAccount = repository.findById(toAccountId)
                .orElseThrow( () -> new TransferException("None match Account: " + toAccountId));

        try {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        } catch (ArithmeticException ae) {
            fromAccount.deposit(amount);
            throw ae;
        } catch (IllegalStateException ilse) {
            throw ilse;
        }
    }

    private void validateTransfer(String fromAccountId,
                                  String toAccountId,
                                  long amount) {
        if (fromAccountId == null || fromAccountId.isBlank()) {
            throw new IllegalArgumentException(
                    "fromAccountId must not be null or blank"
            );
        }

        if (toAccountId == null || toAccountId.isBlank()) {
            throw new IllegalArgumentException(
                    "fromAccountId must not be null or blank"
            );
        }

        if (fromAccountId.equals(toAccountId)) {
            throw new IllegalArgumentException(
                    "these two argument must be different"
            );
        }

        if (amount < ALLOWED_TRANSFER_AMOUNT) {
            throw new IllegalArgumentException(
                    "amount must be greater than " + ALLOWED_TRANSFER_AMOUNT
            );
        }
    }
}