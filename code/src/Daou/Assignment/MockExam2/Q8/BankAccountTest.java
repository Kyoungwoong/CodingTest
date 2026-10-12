package Daou.Assignment.MockExam2.Q8;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount normalAccount;

    @BeforeEach
    public void setUp() {
        /*
         * 기본적으로 BeforeEach를 사용하지만
         * 해당 문제에 대해서는 각각 테스트마다 객체를 생성해야하기에,
         * BeforeEach를 하지 않는다.
         */
        normalAccount = new BankAccount("987654321", 100000);
    }

    @Test
    @DisplayName("정상계좌생성")
    public void createAccountWithValidBalance() {
        BankAccount account = new BankAccount("123456789", 100000);

        assertEquals("123456789", account.getAccountNumber());
        assertEquals(100000, account.getBalance());
    }

    @Test
    @DisplayName("음수 초기 잔액 거부")
    void rejectNegativeInitialBalance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new BankAccount("123456789", -111)
        );
    }

    @Test
    @DisplayName("정상 입금")
    public void depositIncreasesBalance() {
        normalAccount.deposit(1000);

        assertEquals(101000, normalAccount.getBalance());
    }

    @Test
    @DisplayName("0원 입금 거부")
    public void rejectZeroDeposit() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> normalAccount.deposit(0)
        );

        assertEquals("Invalid amount", ex.getMessage());
    }

    @Test
    @DisplayName("입금 오버플로 감지")
    public void rejectDepositOverflow() {
        assertThrows(
                ArithmeticException.class,
                () -> normalAccount.deposit(Long.MAX_VALUE)
        );
    }

    @Test
    @DisplayName("정상 출금")
    public void withdrawDecreasesBalance() {
        normalAccount.withdraw(1000);
        assertEquals(999000, normalAccount.getBalance());
    }

    @Test
    @DisplayName("잔액 부족 . 예외 . 상태 유지")
    public void rejectInsufficientBalance() {
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> normalAccount.withdraw(1000000000L)
        );

        assertEquals(100000, normalAccount.getBalance());
    }
}
