package Daou.Assignment.MockExam2.Q10;

import java.util.Optional;

public interface AccountRepository {

    void save(Account account);

    Optional<Account> findById(String accountId);
}