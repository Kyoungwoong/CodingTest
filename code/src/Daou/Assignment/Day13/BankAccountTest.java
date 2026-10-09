package Daou.Assignment.Day13;

import java.util.concurrent.*;

class BankAccount {

    private int balance = 0;

    public synchronized void deposit(int amount) {
        // TODO: 잔액 증가
        balance += amount;
    }

    public synchronized int getBalance() {
        // TODO: 잔액 반환
        return this.balance;
    }
}

public class BankAccountTest {

    public static void main(String[] args)
            throws InterruptedException {

        BankAccount account = new BankAccount();

        ExecutorService executor =
                Executors.newFixedThreadPool(10);

        // TODO 1: 10개의 입금 작업 제출
        // 각 작업은 account.deposit(1000)을 호출
        for (int count = 1; count <= 10; count++) {
            executor.submit(() -> account.deposit(1000));
        }

        executor.shutdown();

        // TODO 2: 모든 작업이 완료될 때까지 대기
        // awaitTermination() 사용
        boolean completed = executor.awaitTermination(
                10, TimeUnit.SECONDS
        );

        if (!completed) {
            System.out.println("일부 작업이 완료되지 않았습니다.");
            return;
        }

        System.out.println(
                "Final Balance: " + account.getBalance()
        );

        System.out.println(
                "Final Balance: " + account.getBalance()
        );
    }
}