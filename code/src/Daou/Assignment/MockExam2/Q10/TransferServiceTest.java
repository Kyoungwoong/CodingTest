package Daou.Assignment.MockExam2.Q10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {
    private AccountRepository repository;
    private TransferService service;
    private Account from;
    private Account to;

    @BeforeEach
    void setUp() {
        repository = new InMemoryAccountRepository();
        from = new Account("A001", 100_000);
        to = new Account("A002", 50_000);
        repository.save(from);
        repository.save(to);
        service = new TransferService(repository);
    }

    @Test
    void transferMovesMoneyBetweenAccounts() {
        service.transfer("A001", "A002", 30_000);
        assertAll(
                () -> assertEquals(70_000, from.getBalance()),
                () -> assertEquals(80_000, to.getBalance())
        );
    }

    @Test
    void insufficientBalanceDoesNotChangeEitherAccount() {
        assertThrows(IllegalStateException.class,
                () -> service.transfer("A001", "A002", 100_001));
        assertBalancesUnchanged();
    }

    @Test
    void missingDestinationDoesNotChangeSource() {
        assertThrows(TransferException.class,
                () -> service.transfer("A001", "MISSING", 1_000));
        assertBalancesUnchanged();
    }

    @Test
    void missingSourceDoesNotChangeDestination() {
        assertThrows(TransferException.class,
                () -> service.transfer("MISSING", "A002", 1_000));
        assertBalancesUnchanged();
    }

    @Test
    void depositOverflowDoesNotChangeEitherAccount() {
        Account full = new Account("FULL", Long.MAX_VALUE);
        repository.save(full);
        assertThrows(ArithmeticException.class,
                () -> service.transfer("A001", "FULL", 1_000));
        assertAll(
                () -> assertEquals(100_000, from.getBalance()),
                () -> assertEquals(Long.MAX_VALUE, full.getBalance()),
                () -> assertEquals(50_000, to.getBalance())
        );
    }

    @Test
    void sameAccountTransferIsRejectedWithoutMutation() {
        assertThrows(IllegalArgumentException.class,
                () -> service.transfer("A001", "A001", 1_000));
        assertBalancesUnchanged();
    }

    @Test
    void zeroOrNegativeTransferIsRejectedWithoutMutation() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> service.transfer("A001", "A002", 0)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> service.transfer("A001", "A002", -1))
        );
        assertBalancesUnchanged();
    }

    @Test
    void invalidAccountIdsAreRejected() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> service.transfer(null, "A002", 1)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> service.transfer("A001", " ", 1))
        );
        assertBalancesUnchanged();
    }

    @Test
    void transferOfEntireBalanceIsAllowed() {
        service.transfer("A001", "A002", 100_000);
        assertAll(
                () -> assertEquals(0, from.getBalance()),
                () -> assertEquals(150_000, to.getBalance())
        );
    }

    private void assertBalancesUnchanged() {
        assertAll(
                () -> assertEquals(100_000, from.getBalance()),
                () -> assertEquals(50_000, to.getBalance())
        );
    }
}
