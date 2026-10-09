package Daou.Assignment.Day14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountJUnitTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // TODO 1: 각 테스트마다 새로운 계좌 생성
        account = new BankAccount();
    }

    @Test
    void depositTest() {
        // TODO 2: 1000원 입금 후 잔액 검증
        account.deposit(1000);

        assertEquals(1000, account.getBalance());
    }

    @Test
    void multipleDepositTest() {
        // TODO 3: 1000원, 2000원 입금 후 잔액 검증
        account.deposit(1000);
        account.deposit(2000);

        assertEquals(3000, account.getBalance());
    }

    @Test
    void invalidDepositTest() {
        // TODO 4: -1000원 입금 시
        // IllegalArgumentException 발생 검증
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> account.deposit(-1000)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 500, 1000})
    void parameterizedDepositTest(int amount) {
        // TODO 5: amount 입금 후 잔액 검증
        account.deposit(amount);
        assertEquals(amount, account.getBalance());
    }

    @Test
    void concurrentDepositTest() throws Exception {

        ExecutorService executor =
                Executors.newFixedThreadPool(5);

        List<Future<?>> futures = new ArrayList<>();

        try {
            // TODO 6: 10개 입금 작업 제출
            // 각 Future를 futures에 저장
            for (int i = 0; i < 10; i++) {
                futures.add(
                        executor.submit(() -> account.deposit(1000))
                );
            }

            // TODO 7: 모든 Future의 get() 호출
            // 제한 시간을 두어 대기
            for (Future<?> future : futures) {
                future.get(5, TimeUnit.SECONDS);
            }

            // TODO 8: 최종 잔액 10000원 검증
            assertEquals(10000, account.getBalance());

        } finally {
            executor.shutdownNow();
        }
    }
}